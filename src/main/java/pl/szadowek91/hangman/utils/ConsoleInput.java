package pl.szadowek91.hangman.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {

    public static String getInputUserString() {
        boolean inputIsNotValid;
        String input = "";
        do {
            try {
                input = new Scanner(System.in).nextLine();
                inputIsNotValid = input.trim().isEmpty();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input.");
                inputIsNotValid = true;
            }
        } while (inputIsNotValid);
        return input;
    }

    public static Double getInputUserDouble() {

        boolean inputIsNotValid;
        double input = 0.0;
        do {
            try {
                input = new Scanner(System.in).nextDouble();
                inputIsNotValid = false;

            } catch (InputMismatchException e) {
                System.out.println("Wrong input.");
                inputIsNotValid = true;
            }
        } while (inputIsNotValid);
        return input;
    }

    public static Integer getInputUserInteger() {
        boolean inputIsNotValid;
        int result = 0;
        do {
            try {

                result = new Scanner(System.in).nextInt();
                inputIsNotValid = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input.");
                inputIsNotValid = true;
            }
        } while (inputIsNotValid);
        return result;
    }
}