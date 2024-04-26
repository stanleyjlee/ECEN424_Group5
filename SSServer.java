import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import java.util.zip.DeflaterOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SSServer {
    private List<ClientHandler> clients;
    private boolean fullScreen;

    public SSServer(int port, boolean fullScreen) {
        this.clients = new CopyOnWriteArrayList<>();
        this.fullScreen = fullScreen;
    }

    private void sendFramesList(Socket socket) {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            while (true) {
                BufferedImage image = robot.createScreenCapture(screenRect);
                byte[] compressedImageData = compressImage(image);
                sendToClient(socket, compressedImageData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] compressImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();

        byte[] imageData = baos.toByteArray();
        baos.close();

        ByteArrayOutputStream compressedBaos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(compressedBaos);
        dos.write(imageData);
        dos.finish();
        dos.close();

        return compressedBaos.toByteArray();
    }

    private void sendToClient(Socket socket, byte[] compressedImageData) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(compressedImageData.length);
        dos.write(compressedImageData);
        dos.flush();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started...");

            if (fullScreen) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int screenWidth = screenSize.width;
                int screenHeight = screenSize.height;
            }

            while (true) {
                Socket connectionSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(connectionSocket);
                clients.add(clientHandler);
                new Thread(() -> sendFramesList(connectionSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean fullScreen = false;

        if (args.length == 1 && args[0].equals("1")) {
            fullScreen = true;
        }

        SSServer server = new SSServer(12345, fullScreen);
        server.run();
    }

    class ClientHandler {
        Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
    }
}
