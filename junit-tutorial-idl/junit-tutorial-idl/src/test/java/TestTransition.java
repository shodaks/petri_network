import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class TestTransition {
	static PetriNetInterface PNL;
	static Place P0, P1, P2;
	static Transition T0, T1, T2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PNL = new PetriNet();
		P0 = PNL.addPlace(1);
		P1 = PNL.addPlace(0);
		T0 = PNL.addTransition();
		T1 = PNL.addTransition();
		PNL.addPlace2TransitionArc(P0, T0, 1);
		PNL.addTransition2PlaceArc(T0, P1, 2);
		PNL.addPlace2TransitionArc(P1, T1, 1);
		PNL.addTransition2PlaceArc(T1, P0, 2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@Order(3) // take care of ordering since side-effect on NB_transition
	// thus I add getNB_TRANSITION() not really nice
	void testTransition() {
		Transition trans = new Transition();
		assertEquals(trans.getName(), "T_" + (Transition.getNB_TRANSITION() -1));
		assertEquals(trans.getEntrants(), new Vector<Arc>());
		assertEquals(trans.getSortants(), new Vector<Arc>());
	}

	@Test
	void testTransitionString() {
		Transition trans = new Transition("XXX");
		assertEquals(trans.getName(), "XXX");
		assertEquals(trans.getEntrants(), new Vector<Arc>());
		assertEquals(trans.getSortants(), new Vector<Arc>());
	}

//	@Test
//	void testAddArcSortant() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAddArcEntrant() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetSortants() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetEntrants() {
//		fail("Not yet implemented");
//	}

	@Test
	void testToString() {
		Transition trans = new Transition("XXX");
		assertEquals(trans.getName(), "XXX");
		assertEquals(trans.toString(), "Transition: XXX\n"); 
	}

	@Test
	@Order(1)
	public void testIsTriggerable() {
		assertTrue(T0.isTriggerable());
		assertFalse(T1.isTriggerable());
	}

	@Test
	@Order(2)
	public void testTrigger() {
		PNL.trigger(T0);
		PNL.trigger(T0);
	}

	@Test
	void testBalance() {
		assertEquals(T0.balance(), 1);
	}

}
