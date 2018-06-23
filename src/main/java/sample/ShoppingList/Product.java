package sample.ShoppingList;

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

    public String setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return nazwa;
    }
}
