/*CPIT455-TESTING PROHECT
Group:1
Section:VAR
Students Name:
Hadeel Alweldi
Raghad Alssalahi
Shouq Alsubaie
*/
package com.mycompany.bank;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class OperationTest {
    
    public OperationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

/*************** Hadeel Unit Test *******************/
    /**
     * 
     * Test of bankinfo method, of class Operation.
     */
   //Test 1: Valid input (example: 9 = Exit) To Checks if the method works correctly with a valid number.//
     @Test
    public void testBankInfo_ValidChoice() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String input = "9\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);

        Operation.bankinfo();

        String output = out.toString();
        assertTrue(output.contains("THANKS FOR USING"));
    }
 
    //Test 2: Invalid input (text instead of number) to Checks if the method can handle wrong input type.//
    @Test(expected = InputMismatchException.class)
    public void testBankInfo_InvalidChoice_NonNumeric() {

        String input = "hadeel\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);

        Operation.bankinfo(); 
    }
       //Test 3: Out of range input (example: 11) to Checks if the method handles numbers not in the menu.//
    @Test
    public void testBankInfo_OutOfRangeChoice() {

        String input = "11\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Operation.scan = new Scanner(System.in);

        Operation.bankinfo();

        // This SHOULD be printed, but the program does NOT print it.
        // Therefore, this test FAILS intentionally to show the problem.
        fail("Out-of-range input is not handled (Intentional FAIL).");
    }

//**************Hadeel UnitTest**********************/
    
     /**
       * Test of operation method, of class Operation.
       */
    //Test 1 (PASS): key = 2 to checks that checkbalance output is printed correctly//
    @Test
    public void testOperation_CheckBalanceCase() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String input = "1\n9\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);

        Operation.operation(2);  // check balance

        String output = out.toString();
        assertTrue(output.contains("Balance"));
    }

    
    @Test
    public void testOperation_InvalidKey() {

        // User enters invalid menu choice
        System.setIn(new ByteArrayInputStream("\n".getBytes()));
        Operation.scan = new Scanner(System.in);

        Operation.operation(11);

        // The program SHOULD show a warning, but it does NOT.
        // So we FAIL intentionally.
        fail("Invalid key is accepted without any message (Intentional FAIL).");
    }
    
    @Test
public void coverCase1_openAccount() {

    procces.bank1 = new BankInfo();

    // 4 inputs for openAccount
    // + 1 (return to main page)
    // + 9 (exit)
    String input =
            "123\n" +     // acc no
            "Saving\n" +   // type
            "Hadeel\n" +   // name
            "5000\n" +     // balance
            "1\n" +        // go back to main menu
            "9\n";         // exit

    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Operation.scan = new Scanner(System.in);
    procces.sc = Operation.scan;

    Operation.operation(1);
}

    
}