/**
 * Methods in this class are static and callable to simplify input into one function
 * Class does not close scanners do to multiple functions utilising it and not wanting to close System.in
 */

package main.com.hotel.ui;

import main.com.hotel.model.criteria.RoomDetails;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
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

            // check that there is no semicolon for sql injection precaution
            if (input != null && !input.contains(";") && !input.isEmpty())
            {
                return input; // Return the valid input
            }
            else
            {
                System.out.println("Please enter a valid string.");
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

    public static Boolean inputYNNullable(String prompt) {
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
            else if (input.isEmpty())
            {
                return null; // no preference
            }
            else
            {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
    }

    public static Date inputDate(LocalDate lowerBoundIcl, LocalDate upperBoundIncl, String prompt)
    {
        Date date = null;
        boolean isDateInvalid = true;

        while (isDateInvalid)
        {
            System.out.println(prompt);
            isDateInvalid = false;
            System.out.println("Enter in the form: DD/MM/YYYY");

            int day = InputHelper.inputInteger(1, 31, "Enter DD: ");
            int month = InputHelper.inputInteger(1, 12, "Enter MM: ");
            int year = InputHelper.inputInteger(1, 4000, "Enter YYYY: ");

            try {
                LocalDate inputDate = LocalDate.of(year, month, day);

                if (inputDate.isBefore(lowerBoundIcl) || inputDate.isAfter(upperBoundIncl))
                {
                    System.out.println("The date must be between " + lowerBoundIcl + " and " + upperBoundIncl);
                    isDateInvalid = true;
                }
                else
                {
                    date = Date.valueOf(inputDate);
                }

            } catch (DateTimeException e) {
                System.out.println("The date you entered is invalid.");
                isDateInvalid = true;
            }
        }
        return date;
    }

    public static RoomDetails inputRoomDetails()
    {
        String roomType = switch (InputHelper.inputLetterMultipleChoice(6, """
                Enter the room type they would like:
                A) Any
                B) Single
                C) Double
                D) Single/Double
                E) Double/Twin
                F) Twin
                G) Triple
                H) Queen
                Enter a letter: \s"""))
        {
            case "A" -> null;
            case "B" -> "Single";
            case "C" -> "Double";
            case "D" -> "Single/Double";
            case "E" -> "Double/Twin";
            case "F" -> "Twin";
            case "G" -> "Triple";
            case "H" -> "Queen";
            default -> throw new IllegalStateException("Unexpected value");
        };
        Boolean hasShower = InputHelper.inputYNNullable("Does the room have a shower? (Y/N or press Enter for any): ");
        Boolean hasJacuzzi = InputHelper.inputYNNullable("Does the room have a jacuzzi? (Y/N or press Enter for any): ");
        Boolean hasSeaView = InputHelper.inputYNNullable("Does the room have a sea view? (Y/N or press Enter for any): ");
        Boolean isFamilyRoom = InputHelper.inputYNNullable("Is the room a family room? (Y/N or press Enter for any): ");
        Boolean isHoneymoonRoom = InputHelper.inputYNNullable("Is the room a honeymoon room? (Y/N or press Enter for any): ");

        // Create and return the RoomDetails object
        return new RoomDetails(roomType, hasShower, hasJacuzzi, hasSeaView, isFamilyRoom, isHoneymoonRoom);
    }
}
