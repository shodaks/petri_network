import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
@Suite
@SelectClasses({TestPlace.class, TestTransition.class})
public class AllTests {}
