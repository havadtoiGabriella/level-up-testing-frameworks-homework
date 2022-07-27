package testclasses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.epam.mentoring.testautomation.AccountFrozenException;
import com.epam.mentoring.testautomation.BankAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DebitTest {
    private BankAccount bankAccountWithLimitMinus30;

    @Before
    public void setUp() {
        bankAccountWithLimitMinus30 = new BankAccount("Jenny", 50.45, -30);
    }

    @After
    public void tearDown() {
        System.out.println(bankAccountWithLimitMinus30.getAccountDetails());
    }

    @Test
    public void debit_the_account_with_20_45_should_return_30() throws AccountFrozenException {
        double amount = 20.45;
        int expectedBalance = 30;

        bankAccountWithLimitMinus30.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus30.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expectedBalance;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @Test
    public void debit_the_account_with_80_35_should_return_minus_29_9() throws AccountFrozenException {
        double amount = 80.35;
        double expectedBalance = -29.9;

        bankAccountWithLimitMinus30.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus30.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expectedBalance;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @Test
    public void debit_the_account_with_0_should_not_change_the_balance() throws AccountFrozenException {
        double amount = 0;
        double expectedBalance = 50.45;

        bankAccountWithLimitMinus30.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus30.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expectedBalance;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @Test
    public void debit_the_account_should_not_change_the_limit() throws AccountFrozenException {
        double amount = 50.31;
        int expectedLimit = -30;

        bankAccountWithLimitMinus30.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus30.getAccountDetails();
        String expectedAccountDetailFragment = "limit is: " + expectedLimit;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }
}
