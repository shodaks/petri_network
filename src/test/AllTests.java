import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Regroupe toutes les classes de tests (lancement en un clic depuis IntelliJ).
 */
@Suite
@SelectClasses({TestPlace.class, TestTransition.class})
public class AllTests {}
