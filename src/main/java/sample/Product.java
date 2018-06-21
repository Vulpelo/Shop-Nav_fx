package sample;

public class Product {

    private String nazwa;

    public Product() {
        this.nazwa = "";
    }

    public Product(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
