import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ElectionApp {
    private final static double ONE_HUNDRED = 100.0;
    private final static double ROUNDED_OUTPUT_CORRECTOR_VALUE = 0.00005;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter votes for candidate A and B: ");
        int votesForFirstCandidate;
        int votesForSecondCandidate;
        try {
            String[] result = br.readLine().split(" ");
            votesForFirstCandidate = Integer.parseInt(result[0]);
            votesForSecondCandidate = Integer.parseInt(result[1]);
            if (votesForFirstCandidate == votesForSecondCandidate) {
                System.out.println("draw");
            } else {
                int winner = Math.max(votesForFirstCandidate, votesForSecondCandidate);
                int loser = Math.min(votesForFirstCandidate, votesForSecondCandidate);
                double percentage = ONE_HUNDRED / (votesForFirstCandidate + votesForSecondCandidate);
                double winnerPercentage = (winner - loser) * percentage;
                System.out.printf("%s %.4f", winner == votesForFirstCandidate ? "A " : "B ", winnerPercentage - ROUNDED_OUTPUT_CORRECTOR_VALUE);
            }
        } catch (IOException e) {
            System.out.println("Ups, something went wrong!\nPlease try again and enter only integer numbers.");
        } catch (NumberFormatException er) {
            System.out.println("Please enter integer numbers only. Two numbers separated with one space.");
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("Please enter two integer numbers separated with one space.");
        }
    }

}

