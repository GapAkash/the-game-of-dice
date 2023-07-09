/**
 * 
 */
package org.gameofdice.util;

import java.util.Scanner;

/**
 * @author Akash.Gupta
 *
 */
public class GameUtil {

	public static final Scanner SCANNER = new Scanner(System.in);
    public static final String WELCOME_MESSAGE = "********** Welcome to The Game Of Dice **********";
    public static final String END_MESSAGE = "********** Thank You For Playing **********";
    public static final String MAIN_LINE_BREAK = "========================================";
    public static final String LINE_BREAK_1 = "********************";
    public static final String LINE_BREAK_2 = "**********";


    public static <T> T inputValidation(String message, Class<T> type, Scanner scanner) {
        System.out.print(message);
        while (true) {
            String input = scanner.nextLine();
            try {
                if (type == Integer.class) {
                    int number = Integer.parseInt(input);
                    if (number > 0) {
                        return type.cast(number);
                    } else {
                        throw new IllegalArgumentException("Zero or negative number not allowed");
                    }
                } else if (type == String.class) {
                    if (input.equals("r")) {
                        return type.cast(input);
                    } else {
                        throw new IllegalArgumentException("Input must be 'r'");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid input. " + e.getMessage() + ". Please try again: ");
            }
        }
    }
}
