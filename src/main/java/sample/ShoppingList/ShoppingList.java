package sample.ShoppingList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;



public class ShoppingList extends Application {


    TextField nEnter;
    Stage window;
    TableView<Product> table;

    String a = null;
    String nowa_wart;

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {

        Workbook fileHolder = Workbook.getWorkbook(new File("przyklad.xls"));
        Sheet sheet = fileHolder.getSheet(0);

        String[][] array = new String[2][140];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 140; j++) {
                Cell cell = sheet.getCell(i, j);
                String value = cell.getContents();
                array[i][j] = value;
                }
        }
        fileHolder.close();

        window = primaryStage;
        window.setTitle("LISTA ZAKUPÓW");

        Label label = new Label();

        Button add = new Button("Dodaj");
        add.setOnAction(event -> add());

        Button search = new Button("Wyszukaj");
        search.setOnAction(event -> {
            nowa_wart = nEnter.getText();
            for (int j = 0; j < 140; j++) {
                String result = array[0][j];
                if (result.equals(nowa_wart))
                    a = array[0][j];
            }
            if (nowa_wart.equals(a)) {
                label.setText("Produkt znajduje się w sklepie. \nDodano automatycznie do listy zakupów");
                add();
            } else
                label.setText("Brak produktu w sklepie. Spróbój jeszcze raz");
        });

        Button remove = new Button("Usuń");
        remove.setOnAction(event -> remove());

        TableColumn<Product, String> column = new TableColumn<>("PRODUKTY");
        column.setMinWidth(200);
        column.setCellValueFactory(new PropertyValueFactory<>("Nazwa"));
        nEnter = new TextField();
        nEnter.setPromptText("Nazwa");
        nEnter.setMinWidth(100);

        HBox cointainer = new HBox();
        cointainer.setPadding(new Insets(20, 20, 30, 20));
        cointainer.setSpacing(10);
        cointainer.getChildren().addAll(nEnter, search, remove);

        HBox cointainer2 = new HBox();
        cointainer2.setPadding(new Insets(5, 50, 20, 5));
        cointainer2.setSpacing(10);
        cointainer2.getChildren().addAll(label);

        table = new TableView<>();
        table.getColumns().addAll(column);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, cointainer, cointainer2);

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
