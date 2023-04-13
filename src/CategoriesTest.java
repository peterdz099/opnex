import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriesTest {

    @Test
    public void testSetCategories() throws IOException {
        Categories categories = new Categories();
        HashMap<String, Double> expectedCategories = new HashMap<>();
        expectedCategories.put("electronics", 1994.99);
        expectedCategories.put("women's clothing", 157.72);
        expectedCategories.put("men's clothing", 204.23);
        expectedCategories.put("jewelery", 883.98);
        HashMap<String, Double> actualCategories = categories.getProductCategories();
        assertEquals(expectedCategories, actualCategories);
    }

    private Categories categories;

    @BeforeEach
    public void setUp() throws IOException {
        categories = new Categories();
    }

    @Test
    public void testGetCategory1() {

        Double expectedValue = 1994.99;
        Double actualValue = categories.getCategoryValue("electronics");
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetCategory2() {

        Double expectedValue = 157.72;
        Double actualValue = categories.getCategoryValue("women's clothing");
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetCategory3() {

        Double expectedValue = 204.23;
        Double actualValue = categories.getCategoryValue("men's clothing");
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetCategory4() {

        Double expectedValue = 883.98;
        Double actualValue = categories.getCategoryValue("jewelery");
        Assertions.assertEquals(expectedValue, actualValue);
    }
}