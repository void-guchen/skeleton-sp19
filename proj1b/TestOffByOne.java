import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char char1 = 'a';
        char char2 = 'b';
        char char3 = 'b';
        assertFalse(offByOne.equalChars(char1, char2));
        assertTrue(offByOne.equalChars(char2, char3));
    }

}