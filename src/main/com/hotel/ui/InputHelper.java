/**
 * Methods in this class are static and callable to simplify input into one function
 * Class does not close scanners do to multiple functions utilising it and not wanting to close System.in
 */

package main.com.hotel.ui;

import java.util.Scanner;

// make class package-private as methods are only needed in ui package
final class InputHelper {

    private InputHelper()
    {
        // private so no instantiation occurs and methods are always referred to statically
    }

    public static String inputLetterMultipleChoice(int numOfOptions, String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String szOut;

        while(true)
        {
            System.out.print(prompt);
            szOut = sc.nextLine();

            if (szOut.length() == 1)
            {
                for (int i = 0; i < numOfOptions; i++)
                {
                    if(szOut.toLowerCase().charAt(0) == (char)(i + 97))
                    {
                        return szOut.toUpperCase();
                    }
                }
            }

            System.out.println("Please enter a valid letter.");
        }

    }

    public static int inputInteger(int lowerBoundIncl, int upperBoundIncl, String prompt)
    {
        Scanner sc = new Scanner(System.in);
        int iOut;

        while (true)
        {
            System.out.print(prompt);

            if (sc.hasNextInt())
            {
                iOut = sc.nextInt();
                sc.nextLine();

                if (iOut >= lowerBoundIncl && iOut <= upperBoundIncl)
                {
                    return iOut;
                }
            }
            else
            {
                sc.nextLine(); // flush out sc
            }

            System.out.println("Please enter a valid number between " + (lowerBoundIncl) + " and " + (upperBoundIncl) + ".");
        }
    }

    public static String inputString(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String input;

        while (true)
        {
            System.out.print(prompt);

            input = sc.nextLine();

            // check input is not empty and that there is no semicolon for sql injection precaution
            if (input != null && !input.trim().isEmpty() && !input.contains(";"))
            {
                return input; // Return the valid input
            }
            else
            {
                System.out.println("Please enter a valid string (not empty).");
            }
        }
    }

    public static boolean inputYN(String prompt) {
        Scanner sc = new Scanner(System.in);
        String input;

        while (true)
        {
            System.out.print(prompt);
            input = sc.nextLine().trim().toUpperCase();

            if (input.equals("Y"))
            {
                return true; // User answered 'Yes'
            }
            else if (input.equals("N"))
            {
                return false; // User answered 'No'
            }
            else
            {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
    }

}
