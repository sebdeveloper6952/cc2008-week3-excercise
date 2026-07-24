public class Computer {
    private String brand;
    private int storage;
    private float price;

    Computer() {}

    Computer(String brand, int storage) {
        this.brand = brand;
        this.storage = storage;
    }

    // getter
    public String getBrand() { return brand; }

    // setter
    public void setBrand(String brand) {
        // validar si brand esta dentro de una lista aceptada
        if (brand == null || brand == "") {
            return;
        }

        this.brand = brand;
    }

    public float getPrice() { return price; }

    public void setPrice(float price) {
        this.price = price;
    }

    public void printStorage() {
        System.out.println("This computer has " + this.storage + " GB");
    }

    public void printBrand() {
        System.out.println("This computer is a " + this.brand);
    }
}
