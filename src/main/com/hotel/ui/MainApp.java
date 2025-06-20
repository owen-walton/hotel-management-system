/**
 * @author Owen Walton
 * Login must be edited to include authentication
 * Allows login and calls .run() from the corresponding ui class
 */

package main.com.hotel.ui;

import main.com.hotel.role.Roles;

public class MainApp
{
    public static void main(String[] args)
    {
        Roles userRole = login();
        BaseUI ui;

        switch (userRole) {
            case CONCIERGE -> ui = new ConciergeUI();
            case HEAD_CLEANER   -> ui = new HeadCleanerUI();
            case HEAD_CHEF   -> ui = new HeadChefUI();
            default        -> throw new IllegalStateException("Unexpected role: " + userRole);
        }

        ui.run();
    }

    // currently no password or security to login
    public static Roles login()
    {
        Roles role;
        String input;

        input = InputHelper.inputLetterMultipleChoice(3, """
                What is your role at the company?
                A) Concierge
                B) Head Cleaner
                C) Head Chef
                Enter a letter:\s""");

        role = switch (input)
        {
            case "A" -> Roles.CONCIERGE;
            case "B" -> Roles.HEAD_CLEANER;
            case "C" -> Roles.HEAD_CHEF;
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };

        return role;
    }


}
