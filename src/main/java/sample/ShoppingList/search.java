package sample.ShoppingList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.Workbook;


 public class search {

     public static void main(String[] args) throws IOException, BiffException {

        Workbook fileHolder = Workbook.getWorkbook(new File("przyklad.xls"));
        Sheet sheet = fileHolder.getSheet(0);

        String [][] array = new String [2][140];
        for (int i=0; i<2; i++){
            for (int j = 0; j<140; j++){
                Cell cell = sheet.getCell(i,j);
                String value = cell.getContents();
                array[i][j] = value;
                // System.out.println(array[i][j]);
            }
        }

        fileHolder.close();

        int j;
        String a = null;
        System.out.println("Podaj nazwa produktu, jaki chcesz znaleźć w bazie:");
        Scanner in = new Scanner(System.in);
        String product = in.nextLine();

        for (j=0; j<140; j++){
            String result = array[0][j];
            if (result.equals(product)){
                a = array[0][j];
            }

        }
        //System.out.println(array[0][j]);
        //System.out.println(j);

        System.out.println(a);
    }
}
