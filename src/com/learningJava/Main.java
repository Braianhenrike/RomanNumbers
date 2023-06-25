package com.learningJava;

import java.util.Scanner;

public class Main {

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option = "";

        while (!option.equalsIgnoreCase("3")) {
            try {
                Thread.sleep(100);
                clearScreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println();
            System.out.println("-----------------DIGITE UMA DAS OPÇÕES.----------------");
            System.out.println();
            System.out.println("Para converter números Romanos para Naturais, digite 1.");
            System.out.println("Para converter números Naturais para Romanos, digite 2.");
            System.out.println("Para Sair, digite 3.");
            System.out.println();
            System.out.print(": ");

            option = scanner.nextLine();

            if (option.equalsIgnoreCase("3")) {
                break;
            }

            if (option.equals("1")) {
                convertRomanToDecimal(scanner);
            } else if (option.equals("2")) {
                convertDecimalToRoman(scanner);
            } else {
                System.out.println();
                System.out.println("Opção inválida. Tente novamente.");
                System.out.println();
                System.out.println("-------------------------------------------------------");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void convertRomanToDecimal(Scanner scanner) {
        clearScreen();
        System.out.println();
        System.out.println("Digite um número romano de I a C:");
        System.out.println();
        String romanNumeral = scanner.nextLine().toUpperCase();

        int decimalNumber = convertToDecimal(romanNumeral);

        System.out.println();
        System.out.println("O número romano " + romanNumeral + " equivale a " + decimalNumber + ".");
        System.out.println();
        System.out.println("Digite enter para Reiniciar o programa");
        scanner.nextLine();
    }

    private static void convertDecimalToRoman(Scanner scanner) {
        clearScreen();
        System.out.println();
        System.out.println("Digite um número natural de 1 a 100:");
        System.out.println();
        int decimalNumber = Integer.parseInt(scanner.nextLine());

        String romanNumeral = convertToRoman(decimalNumber);

        System.out.println();
        System.out.println("O número " + decimalNumber + " equivale a " + romanNumeral + " em romano.");
        System.out.println();
        System.out.println("Digite enter para Reiniciar o programa");
        System.out.println();
        System.out.println();
        scanner.nextLine();
    }

    private static int convertToDecimal(String romanNumeral) {
        int decimalNumber = 0;
        int previousValue = 0;

        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            char currentChar = romanNumeral.charAt(i);
            int currentValue = convertToDecimal(currentChar);

            if (currentValue < previousValue) {
                decimalNumber -= currentValue;
            } else {
                decimalNumber += currentValue;
                previousValue = currentValue;
            }
        }

        return decimalNumber;
    }

    private static String convertToRoman(int decimalNumber) {
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] decimalValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder romanNumeral = new StringBuilder();

        for (int i = 0; i < decimalValues.length; i++) {
            while (decimalNumber >= decimalValues[i]) {
                romanNumeral.append(romanNumerals[i]);
                decimalNumber -= decimalValues[i];
            }
        }

        return romanNumeral.toString();
    }

    private static int convertToDecimal(char romanDigit) {
        switch (romanDigit) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            default:
                return 0;
        }
        
    }
}