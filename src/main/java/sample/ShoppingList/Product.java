package sample.ShoppingList;
import sample.ShoppingList.Clonable;


public class Product implements Clonable {
    @Override
    public Clonable Clone()
    {
        Product cloneProduct = new Product(nazwa);
        return cloneProduct;
    }

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
