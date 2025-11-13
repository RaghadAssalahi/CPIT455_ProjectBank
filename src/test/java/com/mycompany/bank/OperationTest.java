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
//**************Hadeel UnitTest**********************//
    /**
     * 
     * Test of bankinfo method, of class Operation.
     */
   //Test 1: Valid input (example: 6 = Exit) To Checks if the method works correctly with a valid number.//
    @Test
    public void testBankInfo_ValidChoice() {
        // Simulate user typing "6" then Enter
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the method
        Operation.bankinfo();

        // Pass if no error happens
        assertTrue(true);
    }

    //Test 2: Invalid input (text instead of number) to Checks if the method can handle wrong input type.//
   /* @Test
    public void testBankInfo_InvalidChoice() {
        // Simulate user typing "hadeel" then Enter
        String input = "hadeel\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the method
        Operation.bankinfo();

        // Pass if no error happens
        assertTrue(true);
    }
*/
    //Test 3: Out of range input (example: 9) to Checks if the method handles numbers not in the menu.//
    @Test
    public void testBankInfo_OutOfRangeChoice() {
        // Simulate user typing "9" then Enter
        String input = "9\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            Operation.bankinfo();
            // If method ignores wrong number, test fails logically
            fail("Program did not handle wrong number.");
        } catch (Exception e) {
            // Fail if unexpected exception happens
            assertTrue(false);
        }
    }
     /**
       * Test of operation method, of class Operation.
       */
    //Test 1 (PASS): key = 2 to checks that checkbalance output is printed correctly//
@Test
public void testOperation_CheckBalanceCase() {

    // capture system output
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    // simulate TWO clean inputs (no spaces!)
    String input = "2\n1\n1\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    // call method
    Operation.operation(2);

    // get printed output
    String output = out.toString();

    // expected keyword printed by checkbalance()
    String expected = "Balance";

    // PASS if the output contains expected keyword
    assertTrue(output.contains(expected));
}


    //Test 2 (FAIL): key = 9 to checks that invalid key does not match expected output//
    @Test
    public void testOperation_InvalidKey() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // no input
        System.setIn(new ByteArrayInputStream("".getBytes()));

        // call with invalid key
        Operation.operation(9);

        String output = out.toString();

        // this will FAIL because invalid key does not print any 'Balance' keyword
        assertTrue("Expected output not found", output.contains("Balance"));
    }
//**************Hadeel UnitTest**********************//
    
}
