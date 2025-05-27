/**
 * @author Owen Walton
 * Allows login and calls .run() from the corresponding ui class
 * as rest of program is coded, more functionality may become required after ui.run();
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

        input = InputHelper.inputLetterMultipleChoice(3, "What is your role at the company?\n" +
                "A) Concierge\n" +
                "B) Head Cleaner\n" +
                "C) Head Chef\n" +
                "Enter a letter: ");

        role = switch (input) {
            case "A" -> Roles.CONCIERGE;
            case "B" -> Roles.HEAD_CLEANER;
            case "C" -> Roles.HEAD_CHEF;
            default -> null;
        };

        return role;
    }


}
