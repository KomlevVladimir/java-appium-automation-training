import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTestClass {
    private MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int actualResult = mainClass.getLocalNumber();

        assertEquals(
                actualResult + " is not equal to 14",
                14,
                actualResult
        );
    }

    @Test
    public void testGetClassNumber() {
        int actualResult = mainClass.getClassNumber();

        assertTrue(
                actualResult + " is not more than 45",
                actualResult > 45
        );
    }

    @Test
    public void testGetClassString() {
        String actualResult = mainClass.getClassString();

        assertTrue(
                actualResult + " does not contain the substring Hello or hello",
                actualResult.matches(".*[Hh]ello.*")
        );
    }
}
