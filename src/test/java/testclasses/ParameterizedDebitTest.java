package testclasses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.epam.mentoring.testautomation.AccountFrozenException;
import com.epam.mentoring.testautomation.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedDebitTest {

    private BankAccount bankAccountWithLimitMinus500;

    @BeforeEach
    public void setUp() {
        bankAccountWithLimitMinus500 = new BankAccount("Danny", 124.3, -500);
    }

    @AfterEach
    public void tearDown() {
        System.out.println(bankAccountWithLimitMinus500.getAccountDetails());
    }

    @DisplayName("Debit the account should return correct int value balance")
    @ParameterizedTest(name = "debit the account with {0} should return {1}")
    @CsvSource(value = {"52.3;72", "1.3;123", "124.3;0"}, delimiter = ';')
    public void debit_the_account(double amount, int expected) throws AccountFrozenException {
        bankAccountWithLimitMinus500.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus500.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expected;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @DisplayName("Debit the account should return correct double value balance")
    @ParameterizedTest(name = "debit the account with {0} should return {1}")
    @CsvSource(value = {"26.6;97.7", "99.9;24.4", "124.2;0.1"}, delimiter = ';')
    public void debit_the_account(double amount, double expected) throws AccountFrozenException {
        bankAccountWithLimitMinus500.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus500.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expected;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @DisplayName("Debit the account below zero should return minus value balance")
    @ParameterizedTest(name = "debit the account with {0} should return {1}")
    @CsvFileSource(resources = "resources/debit_data.csv", delimiter = ';')
    public void debit_the_account_below_zero(double amount, double expected) throws AccountFrozenException {
        bankAccountWithLimitMinus500.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus500.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expected;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @DisplayName("Debit the account should not change the limit")
    @ParameterizedTest(name = "debit the account with {0} should not change the limit")
    @ValueSource(doubles = {2.44, 458.44, 222.32})
    public void debit_the_account_should_not_change_the_limit(double amount) throws AccountFrozenException {
        int expectedLimit = -500;
        bankAccountWithLimitMinus500.debit(amount);

        String actualAccountDetail = bankAccountWithLimitMinus500.getAccountDetails();
        String expectedAccountDetailFragment = "limit is: " + expectedLimit;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }
}
