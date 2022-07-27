package testclasses;

import static org.junit.jupiter.api.Assertions.*;
import com.epam.mentoring.testautomation.AccountFrozenException;
import com.epam.mentoring.testautomation.BankAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountFrozenExceptionTest {

    private BankAccount bankAccountToFreeze;

    @Before
    public void setUp() {
        bankAccountToFreeze = new BankAccount("Jimmy", 100.00, -50.00);
    }

    @After
    public void tearDown() {
        System.out.println(bankAccountToFreeze.getAccountDetails());
    }

    @Test
    public void debit_account_up_to_the_limit_should_throw_account_frozen_exception() {
        int amount = 150;
        AccountFrozenException exception = assertThrows(AccountFrozenException.class, () -> bankAccountToFreeze.debit(amount));

        String actualAccountDetail = bankAccountToFreeze.getAccountDetails();
        String expectedAccountDetailFragment = "your account is Frozen";

        String expectedExceptionMessage = "Account frozen";
        String actualExceptionMessage = exception.getMessage();

        assertAll(
                () -> assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment)),
                () -> assertEquals(expectedExceptionMessage, actualExceptionMessage));
    }

    @Test
    public void debit_account_over_the_limit_should_throw_account_frozen_exception() {
        AccountFrozenException exception = assertThrows(AccountFrozenException.class, () -> bankAccountToFreeze.debit(200));

        String actualAccountDetail = bankAccountToFreeze.getAccountDetails();
        String expectedAccountDetailFragment = "your account is Frozen";

        String expectedExceptionMessage = "Account frozen";
        String actualExceptionMessage = exception.getMessage();

        assertAll(
                () -> assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment)),
                () -> assertEquals(expectedExceptionMessage, actualExceptionMessage));
    }
}
