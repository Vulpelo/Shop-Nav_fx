package nav_shop;

import sample.ShoppingList.Product;
import sample.ShoppingList.ShoppingList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFacttory_product()
    {
        Product testPepsi = new Product();
        testPepsi.nazwa = "Pepsi";

        ShoppingList test = new ShoppingList();
        if(test.ProductDrink().nazwa == testPepsi.nazwa)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }
}

