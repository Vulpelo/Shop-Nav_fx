package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ShoppingList extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    TextField nEnter;
    Stage window;
    TableView<Product> table;


    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("LISTA ZAKUPÓW");

        Button add = new Button("Dodaj");
        add.setOnAction(e -> add());
        Button remove = new Button("Usuń");
        remove.setOnAction(e -> remove());

        TableColumn<Product, String> column = new TableColumn<>("PRODUKTY");
        column.setMinWidth(200);
        column.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        nEnter = new TextField();
        nEnter.setPromptText("Nazwa");
        nEnter.setMinWidth(100);


        HBox cointainer = new HBox();
        cointainer.setPadding(new Insets(10, 10, 10, 10));
        cointainer.setSpacing(10);
        cointainer.getChildren().addAll(nEnter,  add, remove);

        table = new TableView<>();
        table.getColumns().addAll(column);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, cointainer);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    public void add() {
        Product Product = new Product();
        Product.setNazwa(nEnter.getText());
        table.getItems().add(Product);
        nEnter.clear();
    }

    public void remove() {
        ObservableList<Product> SelectedProduct, allProducts;
        allProducts = table.getItems();
        SelectedProduct = table.getSelectionModel().getSelectedItems();
        SelectedProduct.forEach(allProducts::remove);
    }


}