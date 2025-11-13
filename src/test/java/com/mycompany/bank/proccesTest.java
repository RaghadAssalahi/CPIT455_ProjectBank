package com.mycompany.bank;

import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class proccesTest {

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

    
    // ------------------ calculateInterest() ------------------

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
}


