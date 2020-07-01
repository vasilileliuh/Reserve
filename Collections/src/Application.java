public class Application {
    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        int[] frequency = {1, 2, 10};
        ArrayClass first = new ArrayClass(values, frequency);
        for (int i = 0; i < 13; i++) {
            System.out.println(first.showRandomResult());
        }
    }
}
