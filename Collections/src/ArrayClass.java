import java.util.ArrayList;
import java.util.Arrays;

public class ArrayClass {
    private int[] values;
    private int[] frequency;

    public ArrayClass(int[] values, int[] frequency) {
        this.values = values;
        this.frequency = frequency;
    }

    public int showRandomResult() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < frequency[i]; j++) {
                temp.add(values[i]);
            }
        }
        int randomNum = (int) (Math.random() * temp.size());
        System.out.println("Random result number= " + randomNum);

        return temp.get(randomNum);
    }

    @Override
    public String toString() {
        return "ArrayClass{" +
                "values=" + Arrays.toString(values) +
                ", frequency=" + Arrays.toString(frequency) +
                '}';
    }
}
