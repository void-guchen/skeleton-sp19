import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author guchen
 * @date 2019/9/14 - 下午5:51
 */

public class UnionFindTest {

    public static UnionFind uf;

    public UnionFindTest() {
        uf = new UnionFind(16);
        int[] id = {-8, -8, 0, 0, 0, 1, 1, 1, 2, 2, 3, 5, 5, 6, 8, 11};
        uf.resetIdArray(id);
    }

    @Test
    public void sizeOf() {
        assertEquals(8, uf.sizeOf(13));
        assertEquals(8, uf.sizeOf(14));
    }

    @Test
    public void parent() {
        int[] id = {-16, -8, 0, 0, 0, 1, 1, 1, 2, 2, 3, 5, 5, 6, 8, 11};
        uf.resetIdArray(id);
        assertEquals(-8, uf.parent(1));
        assertEquals(3, uf.parent(10));
    }

    @Test
    public void connected() {
        assertTrue(uf.connected(1, 15));
        assertFalse(uf.connected(1, 0));
    }

    @Test
    public void union() {
        uf.union(0, 1);
        assertEquals(16, uf.sizeOf(0));
    }

    @Test
    public void find() {
        assertEquals(1, uf.find(13));
        assertEquals(0, uf.find(10));
    }
}