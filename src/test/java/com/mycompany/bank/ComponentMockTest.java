/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bank;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ta-up
 */
public class ComponentMockTest {

    private procces process;
    private BankInfo mockBank;

    @Before
    public void setUp() {
        // Create the mock
        mockBank = mock(BankInfo.class);

        // Define initial behavior
        when(mockBank.getBalance()).thenReturn(1000L);
        when(mockBank.getName()).thenReturn("Shouq");
        when(mockBank.getAccno()).thenReturn("123456");
        when(mockBank.getAcc_type()).thenReturn("Saving");

        // Create process object and inject mock
        process = new procces();
        procces.bank1 = mockBank;
    }

    @Test
    public void componentTest_CalculateInterestAndCheckBalance() {
        // 1. Call calculateInterest (this should update balance)
        process.calculateInterest(10); // 10% interest

        // 2. Verify that getBalance() was called once
        verify(mockBank, times(2)).getBalance();

        // 3. Verify that setBalance() was called with the correct new balance
        verify(mockBank).setBalance(1100L);

        // 4. Call checkbalance() (this should call getters)
        process.checkbalance();

        // 5. Verify that all getters are called at least once
        verify(mockBank).getName();
        verify(mockBank).getAccno();
        verify(mockBank).getAcc_type();
        verify(mockBank, atLeastOnce()).getBalance();
    }
}
