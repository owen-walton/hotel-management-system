package main.com.hotel.model.criteria;

import java.sql.Date;

public record CustomerSearchDetails(String szSurname, Date DOB, String szEmail, String szPhoneNumber)
{
    @Override
    public String toString()
    {
        String out = "";

        if (szSurname != null)
        {
            out += "Surname: " + szSurname + "\n";
        }
        if (DOB != null)
        {
            out += "DOB: " + DOB + "\n";
        }
        if (szEmail != null)
        {
            out += "Email: " + szEmail + "\n";
        }
        if (szPhoneNumber != null)
        {
            out += "Phone Number: " + szPhoneNumber + "\n";
        }

        return out;
    }
}
