package com.mycompany.bank;
/*CPIT455-TESTING PROHECT
Group:4
Section:VAR
Students Name:
Hadeel Alweldi
Raghad Alssalahi
Shouq Alsubaie
*/

import java.io.*;
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
   

    /**
     * Test of demoaccount method, of class procces.
     */
    //********************Shouq UnitTest*******************//
    
    
    //********************Raghad UnitTest*******************//
    /**
     * Test of withdraw method, of class procces.
     */
    
    @Test
    public void testWithdraw_ValidAmount() {
        
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("200\n".getBytes()));

        new procces().withdraw();

        assertEquals(800, procces.bank1.getBalance());
    }

    @Test
    public void testWithdraw_AmountGreaterThanBalance() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(500);
        System.setIn(new ByteArrayInputStream("700\n".getBytes()));

        new procces().withdraw();

        assertEquals(500, procces.bank1.getBalance()); // balance unchanged
    }

    @Test
    public void testWithdraw_AmountEqualToBalance() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(300);
        System.setIn(new ByteArrayInputStream("300\n".getBytes()));

        new procces().withdraw();

        assertEquals(300, procces.bank1.getBalance()); // unchanged, as condition is <
    }

    @Test
    public void testWithdraw_ZeroAmount() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));

        new procces().withdraw();

        assertEquals(1000, procces.bank1.getBalance());
    }

    @Test
    public void testWithdraw_NegativeAmount() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("-50\n".getBytes()));

        new procces().withdraw();

        // current code adds to balance because subtracting -50 increases it
        assertEquals(1050, procces.bank1.getBalance());
    }
    /**
     * Test of deposite method, of class procces.
     */
    
     @Test
    public void testDeposit_ValidAmount() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("500\n".getBytes()));

        new procces().deposite();

        assertEquals(1500, procces.bank1.getBalance());
    }

    @Test
    public void testDeposit_ZeroAmount() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));

        new procces().deposite();

        assertEquals(1000, procces.bank1.getBalance()); // balance unchanged
    }

    @Test
    public void testDeposit_NegativeAmount() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("-200\n".getBytes()));

        new procces().deposite();

        // current code adds even negative numbers (bug)
        assertEquals(800, procces.bank1.getBalance());
    }

    @Test
    public void testDeposit_LargeAmount() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);
        System.setIn(new ByteArrayInputStream("100000\n".getBytes()));

        new procces().deposite();

        assertEquals(101000, procces.bank1.getBalance());
    }

    @Test
    public void testDeposit_MultipleDeposits() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(500);

        System.setIn(new ByteArrayInputStream("100\n".getBytes()));
        new procces().deposite();

        System.setIn(new ByteArrayInputStream("200\n".getBytes()));
        new procces().deposite();

        assertEquals(800, procces.bank1.getBalance());
    }

    
    /**
     * Test of calculateInterest method, of class procces.
     */

    @Test
    public void testCalculateInterest_ValidRate() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        new procces().calculateInterest(5.0); // 5% of 1000 = 50

        assertEquals(1050, procces.bank1.getBalance());
    }

    @Test
    public void testCalculateInterest_ZeroRate() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(2000);

        new procces().calculateInterest(0);

        // zero interest → balance unchanged
        assertEquals(2000, procces.bank1.getBalance());
    }

    @Test
    public void testCalculateInterest_NegativeRate() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        new procces().calculateInterest(-3); // invalid

        // invalid rate → balance unchanged
        assertEquals(1000, procces.bank1.getBalance());
    }

    @Test
    public void testCalculateInterest_LargeRate() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(500);

        new procces().calculateInterest(100); // 100% interest

        assertEquals(1000, procces.bank1.getBalance());
    }

    @Test
    public void testCalculateInterest_FractionalRate() {
        procces.bank1 = new BankInfo();
        procces.bank1.setBalance(1000);

        new procces().calculateInterest(2.5); // 2.5% of 1000 = 25

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

    //Test 3: Wrong expected name to force FAIL when actual mock value is different.//
    @Test
    public void testCheckBalance_WrongExpectedValue() {
        process.checkbalance();

        // This will FAIL because actual mock value = "Hadeel"
        assertEquals("Nora", mockBank.getName());
    }

    //Test 4: Wrong expected balance to force FAIL when actual mock value is different.//
    @Test
    public void testCheckBalance_WrongBalanceValue() {
        process.checkbalance();

        // This will FAIL because actual = 5000, expected = 8000
        assertEquals(8000L, mockBank.getBalance());
    }
    //**************Hadeel UnitTest**********************//
}


