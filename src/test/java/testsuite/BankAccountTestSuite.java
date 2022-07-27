package testsuite;

import testclasses.AccountFrozenExceptionTest;
import testclasses.CreditTest;
import testclasses.DebitTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AccountFrozenExceptionTest.class,
        CreditTest.class,
        DebitTest.class,
})

@Execution(ExecutionMode.CONCURRENT)
public class BankAccountTestSuite {
}
