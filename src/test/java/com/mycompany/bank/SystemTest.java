/*CPIT455-TESTING PROHECT
Group:4
Section:VAR
Students Name:
Hadeel Alweldi
Raghad Alssalahi
Shouq Alsubaie
*/
package com.mycompany.bank;


import java.io.*;
import java.util.Scanner;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SystemTest {
       /**
     * System Test: Full User Journey
     * Simulates a full real-world scenario:
     * - Check Balance
     * - Deposit
     * - Withdraw
     * - Demo Account
     * - Interest Calculation
     * - Loan Request
     * - Check if balance is low
     * - Exit
     */
    @Test
    public void systemTest_FullUserJourney() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // full scenario (all choices)
        String input =
                "2\n1\n" +       // check balance, back
                "3\n500\n1\n" +  // deposit, back
                "4\n200\n1\n" +  // withdraw, back
                "5\n1\n" +       // demo account, back
                "6\n5\n1\n" +    // interest, back
                "7\n5000\n12\n6000\n1\n" + // loan, back
                "8\n500\n1\n" +            // check balance low, back
                "9\n";           // exit

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // initial balance
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        // start full system
        Operation.bankinfo();

        String output = out.toString();

        // SYSTEM TEST assertions
        assertTrue(output.contains("Balance"));
        assertTrue(output.contains("Demo user"));
        assertTrue(output.contains("WELCOME TO INTEREST"));
        assertTrue(output.contains("WELCOME TO LOAN REQUEST"));
        assertTrue(output.contains("LOW") || output.contains("GOOD"));
        assertTrue(output.contains("THANKS FOR USING"));
    }
}
