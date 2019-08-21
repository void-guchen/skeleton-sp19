/**
 * @author guchen
 * @date 2019/8/21 - 下午6:32
 */
@SuppressWarnings("all")
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        //Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }
    //TODO : Use Recursion
    public boolean isPalindrome(String word) {
        if (word == null) return false;
        int length = word.length();
        if (length == 0 || length == 1) {
            return true;
        }
        boolean isPalindrome = false;
        Deque wordDeque = wordToDeque(word);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(wordDeque.removeLast());
        }
        String reverseWord = sb.toString();
        if (word.equals(reverseWord)) {
            isPalindrome = true;
        }
        return isPalindrome;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) return false;
        int length = word.length();
        if (length == 0 || length == 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(length - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
