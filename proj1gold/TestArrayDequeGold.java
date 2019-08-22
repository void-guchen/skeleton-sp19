/**
 * @author guchen
 * @date 2019/8/22 - 下午3:09
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> studentArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();
        ArrayDequeSolution<String> operationDeque = new ArrayDequeSolution<>();
        for (int i = 0; i < 50; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                studentArrayDeque.addFirst(i);
                arrayDequeSolution.addFirst(i);
                operationDeque.addLast("addFirst(" + i + ")");
                //assertEquals(arrayDequeSolution.get(0), studentArrayDeque.get(0));
            } else if (numberBetweenZeroAndOne < 0.5) {
                studentArrayDeque.addLast(i);
                arrayDequeSolution.addLast(i);
                operationDeque.addLast("addLast(" + i + ")");
                //assertEquals(arrayDequeSolution.get(arrayDequeSolution.size() - 1), studentArrayDeque.get(studentArrayDeque.size() - 1));
            } else if (numberBetweenZeroAndOne < 0.75) {
                if (studentArrayDeque.isEmpty() || arrayDequeSolution.isEmpty()) {
                    continue;
                }
                Integer sres = studentArrayDeque.removeFirst();
                Integer eres = arrayDequeSolution.removeFirst();
                operationDeque.addLast("removeFirst()");
                assertEquals(errorMsg(operationDeque), eres, sres);
            } else {
                if (studentArrayDeque.isEmpty() || arrayDequeSolution.isEmpty()) {
                    continue;
                }
                Integer sres = studentArrayDeque.removeLast();
                Integer eres = arrayDequeSolution.removeLast();
                operationDeque.addLast("removeLast()");
                assertEquals(errorMsg(operationDeque), eres, sres);
            }
        }
        System.out.println("Your finally deque: ");
        studentArrayDeque.printDeque();
        System.out.println("Expected deque: ");
        System.out.println(arrayDequeSolution.toString());

    }

    private String errorMsg(ArrayDequeSolution operationDeque) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < operationDeque.size(); i++) {
            sb.append(operationDeque.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
