import java.util.Objects;

@SuppressWarnings("all")
public class IntList {
    public int first;
    public IntList rest;
    
    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    public static IntList of(Integer... args) {
        IntList list = null;
        if (args.length == 0) {
            return null;
        }
        for (int i = args.length - 1; i >= 0; i--) {
            list = new IntList(args[i], list);
        }
        return list;
    }

    /** return size of list using recursion */
    public int size() {
        if (this.rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** return size of list not using  recursion */
    public int iterationSize() {
        int size = 0;
        IntList p = this;
        while (p != null) {
            size++;
            p = p.rest;
        }
        return size;
    }
    /** return the ith item of the IntList using recursion */
    public int get(int i) {
        if (i == 0) {
            return this.first;
        }
        return this.rest.get(--i);
    }

    /** return the ith item of the IntList not using recursion */
    public int iterationGet(int i) {
        IntList p = this;
        while (i > 0) {
            p = p.rest;
            i--;
        }
        return p.first;
    }

    /** returns an IntList identical to L, but with all values incremented by x.
     * Values in L cannot be changed
     */
    public static IntList incrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
        int index = L.size() - 1;
        IntList q = null;
        while (index >= 0) {
            q = new IntList(L.get(index) + x, q);  //效率不高 可以优化
            index--;
        }
        return q;
        // if(p != null) {
        //     q = new IntList(p.first + x, null);
        //     p = p.rest;
        //     q = q.rest;
        // }else {
        //     return q;
        // }
        // while(p != null) {
        //     q.rest = new IntList(p.first + x, null);
        //     q = q.rest;
        //     p = p.rest;
        // }
        // return q;
    }

    /** returns an IntList identical to L, but with all values incremented by x.
     * Not allowed to use 'new' in order to save memory
     */
    public static IntList dincrList(IntList L, int x) {
        IntList p = L;
        while (L != null) {
            L.first = L.first + x;
            L = L.rest;
        }
        return p;        
    }
    /** return an IntList with its ingeter values all squared, non-destructively with recursion */
    // public static IntList square(IntList L) {
    //     if(L == null) return L;
    //     return new IntList(L.first*L.first, square(L.rest));
    // }

    /** return an IntList with its ingeter values all squared, non-destructively with iteration */
    public static IntList square(IntList L) {
        if (L == null) {
            return null;
        }
        IntList p = null;
        int index = L.size() - 1;
        while (index >= 0) {
            int nfirst = L.get(index) * L.get(index); //效率不高，可以优化
            p = new IntList(nfirst, p);
            index--;
        }
        return p;
    }

    /** return the IntList with its ingeter values all squared, destructively with iteration */
    // public static IntList squareDestructive(IntList L) {
    //     IntList p = L;
    //     while(L != null) {
    //         L.first = L.first * L.first;
    //         L = L.rest;
    //     } 
    //     return p;    
    // }

    /** return the IntList with its ingeter values all squared, destructively with recursion */
    public static IntList squareDestructive(IntList L) {
        if (L == null) {
            return null;
        }
        L.first = L.first * L.first;
        squareDestructive(L.rest);
        return L;
    }
    /** print item value of IntList */
    public void print() {
        IntList p = this;
        while (p != null) {
            System.out.println(p.first);
            p = p.rest;
        }
    }

    /**Lists2 Study Guide A Level 1 (MT1 -5)
     * if 2 numbers in a row are the same ,add them together and make one large node. 
     * e.g. 1-1-2-3 become 2-2-3 become 4-3 1-1-2-2-3-3-6-7-7   4-2-12-14 
     */
    public void addAdjacent() {
        IntList n = this;
        while (n.rest != null) {
            if (n.first == n.rest.first) {
                n.first = n.first * 2;
                n.rest = n.rest.rest;
                continue;
            }
            n = n.rest;
        }
    }

    /** Lists2 Study Guide A Level 2 
     * add 5, from 1-2 to 1-1-2-4-5
     * add 7, from 1-1-2-4-5 to 1-1-1-1-2-4-4-16-5-25-7
    */
    public void add(int x) {
        int count = size();
        IntList p = this;
        while (count > 0) {
            p.rest = new IntList(p.first * p.first, p.rest);
            if (count != 1) {
                p = p.rest.rest;
            } else {
                p = p.rest;
            }
            count--;
        }
        p.rest = new IntList(x, null); 
    }

    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     * @param list
     * @return
     */
    public static IntList reverse(IntList list) {
        if (list == null) {
            return null;
        }
        int length = list.size();
        int[] nums = new int[length];
        IntList p = list;
        int count = 0;
        while (p != null) {
            nums[count] = p.first;
            p = p.rest;
            count++;
        }
        int n = length / 2;
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            nums[i] = nums[length - 1 - i];
            nums[length - 1 - i] = temp;
        }
        p = list;
        count = 0;
        while (p != null) {
            p.first = nums[count];
            p = p.rest;
            count++;
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntList intList = (IntList) o;
        return first == intList.first &&
                Objects.equals(rest, intList.rest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, rest);
    }

    public static IntList dcatenate(IntList a, IntList b) {
        return null;
    }

    public static IntList squareListRecursive(IntList l) {
        return null;
    }

    public static IntList catenate(IntList a, IntList b) {
        return null;
    }
    public static IntList dSquareList(IntList l) {
        return null;
    }

    public static void main(String[] args) {
        // IntList list = new IntList(15, null);
        // list = new IntList(10, list);
        // list = new IntList(5, list);
        // System.out.println(list.size());
        // System.out.println(list.iterationSize());
        // System.out.println(list.iterationGet(1));
        // System.out.println(list.get(2));
        // System.out.println("-----------test dincrList()-----------");
        // IntList nList= IntList.dincrList(list, 15);
        // list.print();
        // nList.print();
        // //System.out.println("-------test square()----------");
        // //square(list).print();
        // System.out.println("-------test squareDestructive()---------------");;
        // squareDestructive(list).print();
        
        // System.out.println("---------test addAdjacent()--------------");
        // IntList list = new IntList(7, null);
        // list = new IntList(7, list);
        // list = new IntList(6, list);
        // list = new IntList(3, list);
        // list = new IntList(3, list);
        // list = new IntList(2, list);
        // list = new IntList(2, list);
        // list = new IntList(1, list);
        // list = new IntList(1, list);
        // list.addAdjacent();
        // list.print();

//        System.out.println("----------test add()-------------------");
//        IntList list = new IntList(2, null);
//        list = new IntList(1, list);
//        list.add(5);
//        list.add(7);
//        list.print();
        IntList list = IntList.of(1, 2, 3, 4, 5);

    }
}
