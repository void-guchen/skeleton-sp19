/**
 * @author guchen
 * @date 2019/8/21 - 下午6:32
 */
public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == 1 || diff == -1;
    }
}
