package testclasses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.epam.mentoring.testautomation.BankAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreditTest {

    private BankAccount bankAccountWithLimitZero;

    @Before
    public void setUp() {
        bankAccountWithLimitZero = new BankAccount("Jamie", 15.56);
    }

    @After
    public void tearDown() {
        System.out.println(bankAccountWithLimitZero.getAccountDetails());
    }

    @Test
    public void credit_the_account_with_2_03_should_return_17_59() {
        double amount = 2.03;
        double expectedBalance = 17.59;

        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expectedBalance;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @Test
    public void credit_the_account_with_2_44_should_return_18() {
        double amount = 2.44;
        int expectedBalance = 18;

        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expectedBalance;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @Test
    public void credit_the_account_with_0_should_not_change_the_balance() {
        double amount = 0;
        double expectedBalance = 15.56;

        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expectedBalance;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @Test
    public void credit_the_account_should_not_change_the_limit() {
        double amount = 530.31;
        int expectedLimit = 0;

        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "limit is: " + expectedLimit;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }
}
