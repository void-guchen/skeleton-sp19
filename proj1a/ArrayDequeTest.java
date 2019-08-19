/**
 * @author guchen
 * @date 2019/8/12 - 下午10:46
 */
public class ArrayDequeTest {
    private static void testGrow() {
        ArrayDeque<Integer> deque = createIntDeque(50);
        deque.printDeque();
        for(int i = 0; i < deque.capacity(); i++) {
            deque.removeFirst();
        }
    }

    public static void main(String[] args) {
//        RArrayDeque<Integer> deque = new RArrayDeque<>();
//        for(int i = 0; i < 100; i++) {
//            deque.addLast(i);
//        }
//        System.out.println("size: " + deque.size() + " length: " + deque.capacity() + " usage factor: " + deque.usageFactor());
//        deque.printDeque();
//        for(int i = 0; i < 50; i++) {
//            deque.removeFirst();
//        }
//        System.out.println("size: " + deque.size() + " length: " + deque.capacity() + " usage factor: " + deque.usageFactor());
//        deque.printDeque();
//
//        for(int i = 0; i < 20; i++) {
//            deque.removeLast();
//        }
//        System.out.println("size: " + deque.size() + " length: " + deque.capacity() + " usage factor: " + deque.usageFactor());
//        deque.printDeque();
//        System.out.println("----------------------------------------------------------------");
//        RArrayDeque<Integer> deque2 = new RArrayDeque<>(deque);
//        deque2.addFirst(-1);
//        System.out.print("deque1: ");
//        deque.printDeque();
//        System.out.print("deque2: ");
//        deque2.printDeque();
        testGrow();
    }

    private static ArrayDeque<Integer> createIntDeque(int size) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < size; i++) {
            deque.addLast(i);
        }
        return deque;
    }
}
