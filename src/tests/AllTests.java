import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Groups all the test classes (one-click launch from IntelliJ).
 */
@Suite
@SelectClasses({TestPlace.class, TestTransition.class, TestInArc.class, TestOutArc.class})
public class AllTests {}
