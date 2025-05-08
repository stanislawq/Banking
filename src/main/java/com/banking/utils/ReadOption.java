package com.banking.utils;

import java.util.Scanner;

public class ReadOption {
    private static final Scanner INPUT = new Scanner(System.in);
    public int readOption(int nOptions) {
        while (true) {
            System.out.println("Enter Option (1 - " + nOptions + "): ");
            try {
                return Integer.parseInt(INPUT.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nEnter digit number please");
            }
        }
    }
}
