import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class RetrieverTest {

    private static Retriever retriever;

    @BeforeAll
    static void setup() throws IOException {
        retriever = new Retriever();
    }

    @Test
    void testGetHighestCartValue1() throws JSONException, IOException {
        String result = retriever.getHighestCartValue();
        assertNotNull(result);
        assertTrue(result.startsWith("The highest cart value is:"));
    }

    @Test
    void testGetHighestCartValue2() throws JSONException, IOException {
        String expected = "The highest cart value is: 2578.7, this cart belongs to: johnd";
        String actual = retriever.getHighestCartValue();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testFindMaxDistanceBetweenUsers1() throws JSONException {
        String result = retriever.findMaxDistanceBetweenUsers();
        assertNotNull(result);
        assertTrue(result.startsWith("The furthest distance between users:"));
    }

    @Test
    void testFindMaxDistanceBetweenUsers2() throws JSONException{
        String expected = "The furthest distance between users: 144.01549699639966. The distance separates the users: johnd, mor_2314 and derek";
        String actual = retriever.findMaxDistanceBetweenUsers();
        Assertions.assertEquals(expected, actual);
    }

}