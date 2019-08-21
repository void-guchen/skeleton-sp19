import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String[] pales = {"", "a", "racecar", "noon"};
        String[] npales = {"horse", "rancor", "aaaab"};
        for (String pale : pales) {
            assertTrue(palindrome.isPalindrome(pale));

        }
        for (String npale : npales) {
            assertFalse(palindrome.isPalindrome(npale));

        }
        String[] palesBy1 = {"", "a", "flake"};
        String[] npalesBy1 = {"horse", "rancor", "aaaab"};
        for (String paleBy1 : palesBy1) {
            assertTrue(palindrome.isPalindrome(paleBy1, new OffByOne()));

        }
        for (String npaleBy1 : npalesBy1) {
            assertFalse(palindrome.isPalindrome(npaleBy1, new OffByOne()));

        }
    }
}