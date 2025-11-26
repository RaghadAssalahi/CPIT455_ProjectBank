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
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class proccesTest {

    private procces process;       // object we are testing
    private BankInfo mockBank;     // fake bank object

    public proccesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //create mock of Bankinfo
        mockBank = mock(BankInfo.class);

        // Define fake values
        when(mockBank.getName()).thenReturn("Hadeel");
        when(mockBank.getAccno()).thenReturn("123456");
        when(mockBank.getAcc_type()).thenReturn("Saving");
        when(mockBank.getBalance()).thenReturn(5000L);

        //create procces object and inject mock
        process = new procces();
        process.bank1 = mockBank;
    }

    @After
    public void tearDown() {
    }

    //********************Shouq UnitTest*******************//
    /**
     * Test of openAccount method, of class procces.
     */
    // Test 1: Normal case – verify all inputs are set correctly
    @Test
    public void testOpenAccount_NormalCase() {

        procces.bank1 = new BankInfo();

        String input = "12345\nSaving\nShouq\n5000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // IMPORTANT: create fresh scanner for Operation
        Operation.scan = new Scanner(System.in);

        // FIX: force procces.sc to use the NEW Scanner
        procces.sc = Operation.scan;

        new procces().openAccount();

        assertEquals("12345", procces.bank1.getAccno());
        assertEquals("Saving", procces.bank1.getAcc_type());
        assertEquals("Shouq", procces.bank1.getName());
        assertEquals(5000L, procces.bank1.getBalance());
    }



    @Test(expected = NoSuchElementException.class)
    public void testOpenAccount_EmptyStrings() {
        String input = "\n\n\n1000\n"; // empty acc no, type, name, balance 1000
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        process.openAccount();

        verify(mockBank).setAccno("");
        verify(mockBank).setAcc_type("");
        verify(mockBank).setName("");
        verify(mockBank).setBalance(1000L);
    }

    @Test
    public void testOpenAccount_WrongExpectedName() {

        procces.bank1 = new BankInfo();

        String input = "123\nSaving\nShouq\n5000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().openAccount();

        assertEquals("Rahaf", procces.bank1.getName());
    }


    @Test
    public void testOpenAccount_NegativeBalance() {

        procces.bank1 = new BankInfo();

        String input = "123\nSaving\nShouq\n-500\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().openAccount();

        assertEquals(-500L, procces.bank1.getBalance());
    }


    /**
     * Test of demoaccount method, of class procces.
     */ 
    // Test 1: Check full output contains all expected lines
    @Test
    public void testDemoAccount_Output() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        process.demoaccount();

        String sep = System.lineSeparator();  // platform-independent newline
        String expectedOutput = ""
                + "Name of account holder :: Demo user" + sep
                + "Account no             :: 8529637412" + sep
                + "Account type           :: demo" + sep
                + "Balance                :: 50000" + sep;

        assertEquals(expectedOutput, outContent.toString());
    }
    
    // Test 2: Checks that the output contains the expected name.
    @Test
    public void testDemoAccount_ContainsName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        process.demoaccount();

        assertTrue(outContent.toString().contains("Demo user"));
    }

    // Test 3: Ensures the method prints exactly 4 lines.
    @Test
    public void testDemoAccount_LinesCount() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        process.demoaccount();

        String output = outContent.toString();
        // Split lines and check count
        String[] lines = output.split(System.lineSeparator());
        assertEquals(4, lines.length);
    }
    
    // Test 4: Verifies the order of printed lines.
    // Useful to detect if someone accidentally rearranges println statements.

    @Test
    public void testDemoAccount_OutputOrder() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        process.demoaccount();

        String[] lines = outContent.toString().split(System.lineSeparator());

        assertEquals("Name of account holder :: Demo user", lines[0]);
        assertEquals("Account no             :: 8529637412", lines[1]);
        assertEquals("Account type           :: demo", lines[2]);
        assertEquals("Balance                :: 50000", lines[3]);
    }
    
    /**
     * Test of RequestLoan method, of class procces.
     */ 
    // Test 1 : Invalid input (amount, months, or salary <= 0)
    @Test
    public void testRequestLoan_InvalidInput_Amount() {
        String result = process.requestLoan(0, 12, 5000);
        assertEquals("Failed_invalid_input", result);

    }

    @Test
    public void testRequestLoan_InvalidInput_Months() {
        String result = process.requestLoan(1000, 0, 5000);
        assertEquals("Failed_invalid_input", result);

    }

    @Test
    public void testRequestLoan_InvalidInput_Salary() {
        String result = process.requestLoan(1000, 12, 0);
        assertEquals("Failed_invalid_input", result);

    }

    //  Amount too high (> salary * 20)
    @Test
    public void testRequestLoan_AmountTooHigh() {
        when(mockBank.getBalance()).thenReturn(500000L);

        String result = process.requestLoan(250000, 12, 10000); // 250000 > 10000*20
        assertEquals("Failed_amount_too_high", result);
    }

    // Invalid duration (<6 or >60)
    @Test
    public void testRequestLoan_DurationTooShort() {
        String result = process.requestLoan(10000, 5, 10000);
        assertEquals("Failed_invalid_duration", result);
    }

    @Test
    public void testRequestLoan_DurationTooLong() {
        String result = process.requestLoan(10000, 61, 10000);
        assertEquals("Failed_invalid_duration", result);
    }

    // Low balance for security (< 5% of amount)
    @Test
    public void testRequestLoan_LowBalanceForSecurity() {
        when(mockBank.getBalance()).thenReturn(400L); // less than 5% of 10_000
        String result = process.requestLoan(10000, 12, 10000);
        assertEquals("Failed_low_balance_for_security", result);
    }

    // Success (passes all checks)
    @Test
    public void testRequestLoan_Success() {
        when(mockBank.getBalance()).thenReturn(10000L);
        String result = process.requestLoan(10000, 12, 10000);
        assertEquals("Success", result);
    }

    //********************Shouq UnitTest*******************//
    
    
    
    

    //********************Raghad UnitTest*******************/
    
    /**
     * Tests withdrawing a valid amount that is less than the balance.
     */
    @Test
    public void testWithdraw_ValidAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().withdraw();

        assertEquals(800, procces.bank1.getBalance());
    }


    /**
     * Tests withdrawing an amount larger than the balance (should not change
     * balance).
     */
    @Test
    public void testWithdraw_AmountGreaterThanBalance() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(500);

        String input = "700\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().withdraw();

        assertEquals(500, procces.bank1.getBalance());
    }


    /**
     * Tests withdrawing an amount equal to the balance (withdrawal not
     * allowed).
     */
    @Test
    public void testWithdraw_AmountEqualToBalance() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(300);

        String input = "300\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().withdraw();

        assertEquals(300, procces.bank1.getBalance());
    }


    /**
     * Tests withdrawing zero (balance should stay the same).
     */
    @Test
    public void testWithdraw_ZeroAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().withdraw();

        assertEquals(1000, procces.bank1.getBalance());
    }


    /**
     * Tests withdrawing a negative amount (should fail because code adds money
     * but it doesn so its a BUG).
     */
    @Test
    public void testWithdraw_NegativeAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "-50\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().withdraw();

        assertEquals(1000, procces.bank1.getBalance());
    }


//----------------- Deposit Tests -----------------//
    /**
     * Tests depositing a valid positive amount.
     */
    @Test
    public void testDeposit_ValidAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "500\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().deposite();

        assertEquals(1500, procces.bank1.getBalance());
    }


    /**
     * Tests depositing zero (balance should remain unchanged).
     */
    @Test
    public void testDeposit_ZeroAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().deposite();

        assertEquals(1000, procces.bank1.getBalance());
    }


    /**
     * Tests depositing a negative amount (current code incorrectly reduces
     * balance should be a BUG).
     */
    @Test
    public void testDeposit_NegativeAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "-200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().deposite();

        assertEquals(800, procces.bank1.getBalance());
    }


    /**
     * Tests depositing a very large amount.
     */
    @Test
    public void testDeposit_LargeAmount() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        String input = "100000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;

        new procces().deposite();

        assertEquals(101000, procces.bank1.getBalance());
    }


    /**
     * Tests multiple deposits done one after another.
     */
    @Test
    public void testDeposit_MultipleDeposits() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(500);

        // Deposit 100
        System.setIn(new ByteArrayInputStream("100\n".getBytes()));
        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;
        new procces().deposite();

        // Deposit 200
        System.setIn(new ByteArrayInputStream("200\n".getBytes()));
        Operation.scan = new Scanner(System.in);
        procces.sc = Operation.scan;
        new procces().deposite();

        assertEquals(800, procces.bank1.getBalance());
    }


//----------------- Interest Tests -----------------//
    @Test
    public void testCalculateInterest_ValidRate() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        new procces().calculateInterest(5.0);

        // 5% of 1000 = 50 → 1000 + 50 = 1050
        assertEquals(1050, procces.bank1.getBalance());
    }


    /**
     * Tests interest calculation when rate is zero (balance unchanged).
     */
    @Test
    public void testCalculateInterest_ZeroRate() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(2000);

        new procces().calculateInterest(0);

        assertEquals(2000, procces.bank1.getBalance());
    }


// Rule for exception testing
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Tests that a negative interest rate should throw an exception
     */
    @Test
    public void testCalculateInterest_NegativeRate_ThrowsException() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        thrown.expect(IllegalArgumentException.class);

        new procces().calculateInterest(-3);
    }


    /**
     * Tests interest calculation with a large 100% rate.
     */
    @Test
    public void testCalculateInterest_LargeRate() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(500);

        new procces().calculateInterest(100);

        // 100% interest = 500 → new balance = 1000
        assertEquals(1000, procces.bank1.getBalance());
    }


    /**
     * Tests interest calculation using a fractional interest rate.
     */
    @Test
    public void testCalculateInterest_FractionalRate() {

        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        new procces().calculateInterest(2.5);

        // 2.5% = 25 → 1000 + 25 = 1025
        assertEquals(1025, procces.bank1.getBalance());
    }

//********************Raghad UnitTest*******************///*
        
    
    
    
//**************Hadeel UnitTest**********************//
    /**
     * Test of checkbalance method, of class procces.
     */
    //Test 1: Normal case to verify all getters are called once.//
    @Test
    public void testCheckBalance_VerifyCalls() {
        process.checkbalance();

        verify(mockBank, times(1)).getName();
        verify(mockBank, times(1)).getAccno();
        verify(mockBank, times(1)).getAcc_type();
        verify(mockBank, times(1)).getBalance();

        assertTrue(true);
    }

    //Test 2: After changing values to verify updated mock data is used.//
    @Test
    public void testCheckBalance_AfterChangingValues() {
        when(mockBank.getName()).thenReturn("Nora");
        when(mockBank.getAcc_type()).thenReturn("Current");
        when(mockBank.getBalance()).thenReturn(7000L);

        process.checkbalance();

        verify(mockBank).getName();
        verify(mockBank).getAcc_type();
        verify(mockBank).getBalance();

        assertEquals("Nora", mockBank.getName());
        assertEquals("Current", mockBank.getAcc_type());
        assertEquals(7000L, mockBank.getBalance());
    }

    //Test 3: Wrong expected balance to force FAIL when actual mock value is different.//
    @Test
    public void testCheckBalance_WrongBalanceValue() {
        process.checkbalance();

        // This will FAIL because actual = 5000, expected = 8000
        assertEquals(8000L, mockBank.getBalance());
    }
    
    /**
     * Test of IsBalanceLow method, of class procces.
     */ 
    @Test
    public void testIsBalanceLow_True() {
        when(mockBank.getBalance()).thenReturn(300L);   // mocked balance

        assertTrue(process.isBalanceLow(500));          // 300 < 500 → TRUE
    }

    @Test
    public void testIsBalanceLow_False() {
        when(mockBank.getBalance()).thenReturn(1500L);  // mocked balance

        assertFalse(process.isBalanceLow(500));         // 1500 < 500 → FALSE
    }
    
    @Test
    public void testIsBalanceLow_Equal() {
        when(mockBank.getBalance()).thenReturn(500L);   // mocked balance

        assertTrue(process.isBalanceLow(500));         // 500 < 500 → FALSE
    }    
    //**************Hadeel UnitTest**********************//

}
