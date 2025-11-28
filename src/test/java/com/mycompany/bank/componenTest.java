/*CPIT455-TESTING PROHECT
Group:1
Section:VAR
Students Name:
Hadeel Alweldi
Raghad Alssalahi
Shouq Alsubaie
*/
package com.mycompany.bank;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class componenTest {

    private procces process;
    private BankInfo realBank;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        // Use a REAL BankInfo object to represent the shared component state
        realBank = new BankInfo();

        // Create a process object (the component under test)
        process = new procces();

        // Inject realBank into static field used by procces
        procces.bank1 = realBank;

        // Capture printed output for methods like checkbalance()
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    // ---------------------------  Component Test 1: Raghad  --------------------------------------
    /**
     * Component Test:
     * This test validates the interaction between calculateInterest() and checkbalance().
     * 1. Setup account details
     * 2. Apply 5% interest
     * 3. Call checkbalance() to confirm updated balance is displayed
     */
    @Test
    public void testInterestThenCheckBalance_Component() {

        // Arrange 
        realBank.setName("Test User");
        realBank.setAccno("123456");
        realBank.setAcc_type("Saving");
        realBank.setBalance(2000);

        process.calculateInterest(5);     // apply 5% interest
        process.checkbalance();           // output the new state

        // Assert 
        assertEquals(2100, realBank.getBalance(), 0.001);
    }

    // ---------------------------  Component Test 2: Hadeel  --------------------------------------
    /**
     * Component Test:
     * This test validates the Demo Account flow inside the system.
     * Steps:
     * 1. User selects "Demo Account" (case 5)
     * 2. System displays demo account information
     * 3. User returns to main menu (1), then exits (9)
     */
    @Test
    public void componentTest_DemoAccountFlow() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String input = "1\n9\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Inject Scanner into Operation
        Operation.scan = new Scanner(System.in);

        // Execute component interaction via Operation
        Operation.operation(5);

        String output = out.toString();

        // Assert
        assertTrue(output.contains("Demo user"));
        assertTrue(output.contains("Balance"));
    }  
    
    // ---------------------------  Component Test 3: Shouq  --------------------------------------
/**
 * Component Test:
 * Validates the interaction between deposit → calculateInterest → checkbalance.
 * Flow:
 * 1. Initial balance = 1000
 * 2. Deposit 500 → new balance = 1500
 * 3. Apply 10% interest → new balance = 1650
 * 4. checkbalance() prints updated values
 */
@Test
public void componentTest_DepositThenInterest_Component() {

    // Arrange
    realBank.setName("User A");
    realBank.setAccno("ABC123");
    realBank.setAcc_type("Saving");
    realBank.setBalance(1000);

    // deposit 500 (simulate logic without scanner)
    int depositAmount = 500;
    realBank.setBalance(realBank.getBalance() + depositAmount);  // now 1500

    // apply 10% interest
    process.calculateInterest(10); // now 1650

    // call checkbalance() to output the new state
    process.checkbalance();

    // Assert balance after deposit + interest
    assertEquals(1650, realBank.getBalance());
}

}
