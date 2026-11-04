import model.InArc;
import model.OutArc;
import model.Place;
import model.Transition;
import network.PetriLattice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests of {@link Transition} (and of the arcs it holds).
 *
 * Adapted from the JUnit tutorial (jroyer) to our API:
 * PetriLattice, addInArc / addOutArc, isStepable() (ex-isTriggerable), step() (ex-trigger).
 */
public class TestTransition {

	private PetriLattice pl;
	private Place p0, p1;
	private Transition t0, t1;

	/**
	 * Network of the tutorial, rebuilt before each test:
	 * P0(1) -1-> T0 -2-> P1(0) -1-> T1 -2-> P0
	 */
	@BeforeEach
	void setUp() {
		pl = new PetriLattice();
		p0 = pl.addPlace("P0", 1);
		p1 = pl.addPlace("P1", 0);
		t0 = pl.addTransition("T0");
		t1 = pl.addTransition("T1");
		pl.addInArc("a0", 1, p0, t0);
		pl.addOutArc("a1", 2, t0, p1);
		pl.addInArc("a2", 1, p1, t1);
		pl.addOutArc("a3", 2, t1, p0);
	}

	// ---------------------------------------------------------------- Construction

	@Test
	void testConstructor() {
		Transition t = new Transition("XXX");
		assertEquals("XXX", t.getName());
		assertTrue(t.getInArcs().isEmpty());
		assertTrue(t.getOutArcs().isEmpty());
	}

	@Test
	void testSetName() {
		Transition t = new Transition("XXX");
		t.setName("YYY");
		assertEquals("YYY", t.getName());
	}

	// ---------------------------------------------------------------- Arcs
	// (in the tutorial: Place.addArcEntrant / addArcSortant)

	@Test
	void testAddInArc() {
		Place p = pl.addPlace("P3", 3);
		Transition t = pl.addTransition("TT1");
		InArc a = pl.addInArc("a", 2, p, t);
		assertEquals(1, t.getInArcs().size());
		assertSame(a, t.getInArcs().get(0));
		assertEquals(2, a.getWeight());
		assertSame(p, a.getPlace());
		assertTrue(pl.getArcs().contains(a));
		assertTrue(t.getOutArcs().isEmpty());
	}

	@Test
	void testAddOutArc() {
		Place p = pl.addPlace("P3", 3);
		Transition t = pl.addTransition("TT1");
		OutArc a = pl.addOutArc("a", 2, t, p);
		assertEquals(1, t.getOutArcs().size());
		assertSame(a, t.getOutArcs().get(0));
		assertEquals(2, a.getWeight());
		assertSame(p, a.getPlace());
		assertTrue(pl.getArcs().contains(a));
		assertTrue(t.getInArcs().isEmpty());
	}

	@Test
	void testAddArcsDirectlyOnTransition() {
		Place p = new Place("P", 0);
		Transition t = new Transition("T");
		InArc in = new InArc("in", 1, p);
		OutArc out = new OutArc("out", 1, p);
		t.addInArc(in);
		t.addOutArc(out);
		assertSame(in, t.getInArcs().get(0));
		assertSame(out, t.getOutArcs().get(0));
	}

	// Requirement 3: "There can be only one arc in the same direction between a place and a transition"
	@Test
	void testOnlyOneInArcPerPlace() {
		assertThrows(IllegalArgumentException.class, () -> {
			pl.addInArc("duplicate", 3, p0, t0);
		}, "Requirement 3: a 2nd arc P0 -> T0 must not be added");
	}

	@Test
	void testOnlyOneOutArcPerPlace() {
		assertThrows(IllegalArgumentException.class, () -> {
			pl.addOutArc("duplicate", 3, t0, p1);
		}, "Requirement 3: a 2nd arc T0 -> P1 must not be added");
	}

	// ---------------------------------------------------------------- Step (classic arcs)

	@Test
	void testIsStepable() {
		assertTrue(t0.isStepable());   // P0 holds 1 token, the arc requires 1
		assertFalse(t1.isStepable());  // P1 is empty, the arc requires 1
	}

	@Test
	void testStep() {
		t0.step();
		assertEquals(0, p0.getToken());
		assertEquals(2, p1.getToken());
		assertFalse(t0.isStepable());
		assertTrue(t1.isStepable());
	}

	@Test
	void testStepNotStepableChangesNothing() {
		t1.step();
		assertEquals(1, p0.getToken());
		assertEquals(0, p1.getToken());
	}

	@Test
	void testTwoConsecutiveSteps() {
		// The tutorial stepped T0 twice without checking anything:
		// the 2nd step must have no effect because T0 is no longer stepable.
		t0.step();
		t0.step();
		assertEquals(0, p0.getToken());
		assertEquals(2, p1.getToken());
	}

	@Test
	void testCycle() {
		t0.step(); // P0=0, P1=2
		t1.step(); // P0=2, P1=1
		assertEquals(2, p0.getToken());
		assertEquals(1, p1.getToken());
	}

	@Test
	void testTransitionWithoutInArcIsStepable() {
		// Case of the specification: a transition with an arc to an empty place
		Place p = pl.addPlace("P", 0);
		Transition t = pl.addTransition("T");
		pl.addOutArc("a", 1, t, p);
		assertTrue(t.isStepable());
		t.step();
		assertEquals(1, p.getToken());
		assertTrue(t.isStepable());
	}

	// ---------------------------------------------------------------- Zero arcs and emptying arcs (TD2)

	// "Zero arcs are active only when the source place is empty."
	@Test
	void testArcZeroEmptyPlaceStepable() {
		Place p = pl.addPlace("P", 0);
		Transition t = pl.addTransition("T");
		pl.addArcZero("z", p, t);
		assertTrue(t.isStepable(),
				"Zero arc: the transition must be stepable when the source place is empty");
	}

	@Test
	void testArcZeroStepDoesNotModifyPlace() {
		Place p = pl.addPlace("P", 0);
		Place output = pl.addPlace("S", 0);
		Transition t = pl.addTransition("T");
		pl.addArcZero("z", p, t);
		pl.addOutArc("o", 1, t, output);
		t.step();
		assertEquals(0, p.getToken(), "Zero arc: the step must not remove any token from the source place");
		assertEquals(1, output.getToken(), "Zero arc: the transition must have been stepped");
	}

	@Test
	void testArcZeroNonEmptyPlaceNotStepable() {
		Place p = pl.addPlace("P", 1);
		Transition t = pl.addTransition("T");
		pl.addArcZero("z", p, t);
		assertFalse(t.isStepable(),
				"Zero arc: the transition must not be stepable when the source place holds tokens");
	}

	// "Emptying arcs are active as soon as the source place holds a token
	//   and remove all the tokens present when they are activated."
	@Test
	void testArcEmptyEmptiesPlace() {
		Place p = pl.addPlace("P", 3);
		Transition t = pl.addTransition("T");
		pl.addArcEmpty("v", p, t);
		assertTrue(t.isStepable());
		t.step();
		assertEquals(0, p.getToken(), "Emptying arc: the step must remove all the tokens");
	}

	@Test
	void testArcEmptyEmptyPlaceNotStepable() {
		Place p = pl.addPlace("P", 0);
		Transition t = pl.addTransition("T");
		pl.addArcEmpty("v", p, t);
		assertFalse(t.isStepable(),
				"Emptying arc: inactive without a token => the transition must not be stepable");
	}
}
