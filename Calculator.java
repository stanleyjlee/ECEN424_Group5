public class Calculator{
    private String name;
    public Float addition(Float A, Float B) {
        return A + B;
    }
    public Float subtraction(Float A, Float B) {
        return A - B;
    }
    public Float multiplication(Float A, Float B) {
        return A * B;
    }
    public void setName(String N){
        this.name = N;
    }
    public String getName() {
        return this.name;
    }

}