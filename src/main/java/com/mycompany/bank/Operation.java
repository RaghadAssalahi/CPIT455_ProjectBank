package com.mycompany.bank;

import java.util.Scanner;

public class Operation {
    public static Scanner scan = new Scanner(System.in);
    public static void bankinfo() {
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" ***Banking System Application***");
        System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println(" 1. Create a new account  \n 2. Check Balance\n 3. Deposit the amount \n 4. Withdraw the amount  \n 5. Watch demo account \n 6. Calculate the interset \n 7. Request loan \n 8.Check if balance is low \n 9. Exit  \n\nENTER YOUR CHOISE :: ");
        int key = scan.nextInt();
        operation(key);

    }

    public static void operation(int key) {
        BankInfo bank = new BankInfo();
       // Scanner scan = new Scanner(System.in);
        procces bankprocess = new procces();
        switch (key) {
            case 1:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankprocess.openAccount();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;
            case 2:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");

                bankprocess.checkbalance();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;
            case 3:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("---------WELCOME TO DEPOSITE PAGE---------------- ");
                bankprocess.deposite();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;
            case 4:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankprocess.withdraw();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;
            case 5:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                bankprocess.demoaccount();
                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;
            case 6:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("WELCOME TO INTEREST CALCULATION PAGE");
                System.out.println("Please enter the interest rate (%): ");

                double rate = scan.nextDouble();   // user enters interest rate
                bankprocess.calculateInterest(rate);

                System.out.println();
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");

                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;
                
            case 7:
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("WELCOME TO LOAN REQUEST PAGE");

                System.out.print("Enter loan amount: ");
                int amount = scan.nextInt();

                System.out.print("Enter duration in months: ");
                int months = scan.nextInt();

                System.out.print("Enter your monthly salary: ");
                double salary = scan.nextDouble();

                // Call your loan method
                String result = bankprocess.requestLoan(amount, months, salary);
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                 break;
            case 8:
                System.out.println("Enter threshold amount:");
                long threshold = scan.nextLong();

                if (procces.bank1.getBalance() < threshold) {
                    System.out.println("Your balance is LOW.");
                } else {
                    System.out.println("Your balance is GOOD.");
                }
                System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("MAIN PAGE_:: PRESS 1 ::");
                if (scan.nextInt() == 1) {
                    bankinfo();
                }
                break;    
            case 9:
                System.out.println("THANKS FOR USING OUT BANK APPLICATION");
                break;

        }
    }

}
