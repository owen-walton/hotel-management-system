package main.java.ui;

import main.java.dao.CustomerDAO;

public class Main {
    public static void main(String[] args)
    {
        CustomerDAO cust = new CustomerDAO();

        System.out.println(cust.getByPK(1).getSzFirstName());
    }
}
