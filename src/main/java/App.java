import exception.LandscapeValidationException;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Input landscape as a heights collection, comma separated: ");
            String input = scanner.nextLine();

            if ("q".equals(input)) {
                System.out.println("Exit!");
                break;
            }

            int[] landscape;

            try {
                landscape = Arrays.stream(input.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } catch (NumberFormatException e) {
                System.out.println("Error. Can't read landscape data!\nPlease, input only comma separated numbers!");
                System.out.println("-----------\n");
                continue;
            }

            WaterCalculator waterCalculator = new WaterCalculator();
            long collectedWaterAmount;
            try {
                collectedWaterAmount = waterCalculator.calculateWaterAmount(landscape);
            } catch (LandscapeValidationException e) {
                System.out.println("Error. Landscape is incorrect!\n" + e.getMessage());
                System.out.println("-----------\n");
                continue;
            }

            System.out.println("This landscape can collect " + collectedWaterAmount + " water units.");
            System.out.println("-----------\n");
        }

        scanner.close();
    }
}
