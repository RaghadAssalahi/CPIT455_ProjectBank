/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class componenTest {


    private procces process;
    private BankInfo realBank;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        // Use a REAL BankInfo object as the shared component state
        realBank = new BankInfo();
        process = new procces();

        // Plug our realBank into the static field used by procces
        procces.bank1 = realBank;

        // Capture console output for tests that call checkbalance()
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    // ---------------------------  Component Test 1:Raghad--------------------------------------
  
    @Test
public void testInterestThenCheckBalance_Component() {
    // Arrange
    realBank.setName("Test User");
    realBank.setAccno("123456");
    realBank.setAcc_type("Saving");
    realBank.setBalance(2000);

    // apply 5% interest, then display balance
    process.calculateInterest(5);   // 5% interest
    process.checkbalance();         // prints bank info + balance

    //  updated balance
    assertEquals(2100, realBank.getBalance(), 0.001);
}
   // ------------------------------  // Component Test 1:Hadeel-----------------------------------
 /**
 * Component Test 1: Demo Account Flow (PASS)
 */
 @Test
public void componentTest_DemoAccountFlow() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    String input = "0\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    Operation.operation(5);

    String output = out.toString();

    assertTrue(output.contains("Demo user"));
    assertTrue(output.contains("Balance"));
}
}