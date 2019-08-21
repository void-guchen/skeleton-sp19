/**
 * @author guchen
 * @date 2019/8/21 - 下午6:33
 */
public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == n || diff == -n) {
            return true;
        }
        return false;
    }
}
