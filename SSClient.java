import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import java.util.zip.InflaterInputStream;
import javax.swing.*;

public class SSClient {
    private static final int REFRESH_RATE = 100; // Delay in milliseconds between screen updates

    public static void main(String[] args) {
        try {
            // Establish connection to the server
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server...");

            // Create and display the client frame
            JFrame frame = createFrame();
            JLabel label = new JLabel();
            frame.getContentPane().add(label);
            frame.setVisible(true);

            // Continuously display the screen from the server
            while (true) {
                displayScreen(socket, label);
                Thread.sleep(REFRESH_RATE); // Add delay between screen updates
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Handle any exceptions
        }
    }

    // Method to create and configure the client frame
    private static JFrame createFrame() {
        JFrame frame = new JFrame("Screen Sharing Client");
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    // Method to display the received screen from the server
    private static void displayScreen(Socket socket, JLabel label) throws IOException {
        // Read compressed image data from the server
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        int compressedImageDataLength = dis.readInt();
        byte[] compressedImageData = new byte[compressedImageDataLength];

        dis.readFully(compressedImageData);

        // Decompress and display the image
        BufferedImage image = decompressImage(compressedImageData);
        label.setIcon(new ImageIcon(image));

        // Refresh the label to display the new image
        label.repaint();

        // Send acknowledgment to the server
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(1);
    }

    // Method to decompress the received image data
    private static BufferedImage decompressImage(byte[] compressedImageData) throws IOException {
        InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(compressedImageData));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;
        while ((length = iis.read(buffer)) > 0) {
            baos.write(buffer, 0, length);
        }

        iis.close();
        baos.close();

        byte[] imageData = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);

        // Read the decompressed image data and return as BufferedImage
        return ImageIO.read(bais);
    }
}
