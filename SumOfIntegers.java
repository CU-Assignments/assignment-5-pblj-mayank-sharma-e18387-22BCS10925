// src/SumOfIntegers.java
import java.util.*;

public class SumOfIntegers {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter numbers separated by spaces: ");
            String input = scanner.nextLine();
            String[] numbers = input.split(" ");

            ArrayList<Integer> numberList = new ArrayList<>();
            for (String numStr : numbers) {
                // Autoboxing: converting int to Integer
                numberList.add(Integer.parseInt(numStr));
            }

            int sum = 0;
            for (Integer num : numberList) {
                // Unboxing: converting Integer to int
                sum += num;
            }

            System.out.println("Sum of numbers: " + sum);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter only integers.");
        }
    }
}
