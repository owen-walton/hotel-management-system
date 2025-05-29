/**
 * Methods in this class are static and callable to simplify input into one function
 * Class does not close scanners do to multiple functions utilising it and not wanting to close System.in
 */

package main.com.hotel.ui;

import main.com.hotel.model.criteria.RoomDetails;

import java.sql.Date;
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
            if (input != null && !input.contains(";"))
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
        boolean isDateInvalid = true;
        Date date = null;
        while(isDateInvalid)
        {
            System.out.println(prompt);
            isDateInvalid = false;
            System.out.println("Enter in the form. DD/MM/YYYY");
            int day = InputHelper.inputInteger(lowerBoundIcl.getDayOfMonth(), upperBoundIncl.getDayOfMonth(), "Enter DD: ");
            int month = InputHelper.inputInteger(lowerBoundIcl.getMonthValue(), upperBoundIncl.getMonthValue(), "Enter MM: ");
            int year = InputHelper.inputInteger(lowerBoundIcl.getYear(), upperBoundIncl.getYear(), "Enter YYYY: ");
            try {
                date = Date.valueOf(year + "-" + month + "-" + day);
            } catch (IllegalArgumentException e) {
                // catches dates like feb 30th which don't exist
                isDateInvalid = true;
                System.out.println("The date you entered is invalid.");
            }
        }
        return date;
    }
    public static RoomDetails inputRoomDetails()
    {
        String roomType = InputHelper.inputString("Enter the room type (e.g., Single, Double, Suite) or press Enter for any: ");
        Boolean hasShower = InputHelper.inputYNNullable("Does the room have a shower? (Y/N or press Enter for any): ");
        Boolean hasJacuzzi = InputHelper.inputYNNullable("Does the room have a jacuzzi? (Y/N or press Enter for any): ");
        Boolean hasSeaView = InputHelper.inputYNNullable("Does the room have a sea view? (Y/N or press Enter for any): ");
        Boolean isFamilyRoom = InputHelper.inputYNNullable("Is the room a family room? (Y/N or press Enter for any): ");
        Boolean isHoneymoonRoom = InputHelper.inputYNNullable("Is the room a honeymoon room? (Y/N or press Enter for any): ");

        // Create and return the RoomDetails object
        return new RoomDetails(roomType, hasShower, hasJacuzzi, hasSeaView, isFamilyRoom, isHoneymoonRoom);
    }
}
