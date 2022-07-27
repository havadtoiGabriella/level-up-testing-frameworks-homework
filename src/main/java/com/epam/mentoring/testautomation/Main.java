package com.epam.mentoring.testautomation;

/**
 * Example for the usage of BankAccount class.
 *
 * @see BankAccount
 */
public class Main {
    public static void main(String[] args) {
        BankAccount ba1 = new BankAccount("John Doe", 11.99);
        BankAccount ba2 = new BankAccount("Jessica Doe", 100.00, -50.00);

        try {
            ba1.credit(5.77);
            ba1.debit(11.22);

            ba2.debit(100);
            ba2.debit(50);
            ba2.debit(1);
        } catch (AccountFrozenException e) {
            System.out.println("An exception has occurred. Details: " + e.getMessage());
        }

        System.out.println(ba1.getAccountDetails());
        System.out.println(ba2.getAccountDetails());
    }
}
