package testclasses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.epam.mentoring.testautomation.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@Execution(ExecutionMode.CONCURRENT)
public class ParameterizedCreditTest {

    private BankAccount bankAccountWithLimitZero;

    @BeforeEach
    public void setUp() {
        bankAccountWithLimitZero = new BankAccount("Peter", 15.56);
    }

    @AfterEach
    public void tearDown() {
        System.out.println(bankAccountWithLimitZero.getAccountDetails());
    }

    @DisplayName("Credit the account should return correct double value balance")
    @ParameterizedTest(name = "credit the account with {0} should return {1}")
    @CsvSource(value = {"2.03;17.59", "86.2;101.76", "876;891.56"}, delimiter = ';')
    public void credit_the_account(double amount, double expected) {
        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expected;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @DisplayName("Credit the account should return correct int value balance")
    @ParameterizedTest(name = "credit the account with {0} should return {1}")
    @CsvSource(value = {"2.44;18", "458.44;474", "222.44;238"}, delimiter = ';')
    public void credit_the_account(double amount, int expected) {
        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "balance is: " + expected;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }

    @DisplayName("Credit the account should not change the limit")
    @ParameterizedTest(name = "credit the account with {0} should not change the limit")
    @ValueSource(doubles = {2.44, 458.44, 222.32})
    public void credit_the_account_should_not_change_the_limit(double amount) {
        int expectedLimit = 0;
        bankAccountWithLimitZero.credit(amount);

        String actualAccountDetail = bankAccountWithLimitZero.getAccountDetails();
        String expectedAccountDetailFragment = "limit is: " + expectedLimit;

        assertTrue(actualAccountDetail.contains(expectedAccountDetailFragment));
    }
}
