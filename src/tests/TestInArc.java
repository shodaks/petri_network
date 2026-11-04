import model.ArcEmpty;
import model.ArcZero;
import model.InArc;
import model.Place;
import model.Transition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests of {@link InArc} (and of its subclasses {@link ArcEmpty} and {@link ArcZero}).
 *
 * Adapted from the JUnit tutorial (jroyer) to our API:
 * Arc(String, int, Place), getName(), getWeight(), getPlace(), setName(), setWeight(), setPlace().
 * The tests on arcs, which belonged to Place in the tutorial,
 * are in TestTransition: in our design transitions are the ones that know their arcs.
 */


public class TestInArc {
    
    
	private Place p;
    private InArc a;

	@BeforeEach
	void setUp() {
		// A fresh place for each test: no dependency on the execution order
		p = new Place("P0", 0);
		a = new InArc("A0", 3, p);
	}

	@Test
	void testConstructorInArc() {
		assertEquals("A0", a.getName());
        assertEquals(3, a.getWeight());
		assertEquals("P0", a.getPlace().getName());
	}

	@Test
	void testConstructorInArcNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () -> new InArc("A0", -1, p),
				"Requirement 7: an arc cannot have a negative weight");
		assertThrows(IllegalArgumentException.class, () -> new InArc("A0", 0, p),
				"Requirement 6: an arc weight must be strictly positive");
	}

	@Test
	void testGetWeight() {
		assertEquals(3, a.getWeight());
	}

	@Test
	void testGetName() {
		assertEquals("A0", a.getName());
	}

	@Test
	void testSetName() {
		a.setName("A1");
		assertEquals("A1", a.getName());
	}

	@Test 
	void testGetPlace() {
		assertEquals("P0", a.getPlace().getName());
	}

	@Test 
	void testSetPlace() {
		Place p1 = new Place("P1", 0);
		a.setPlace(p1);
		assertEquals("P1", a.getPlace().getName());
	}

	@Test
	void testToString() {
		assertEquals("Incoming arc (A0) : 3", a.toString());
	}

	@Test
	void testSetWeight() {
		a.setWeight(5);
		assertEquals(5, a.getWeight());
	}

	@Test
	void testSetWeightNegative() {
		assertThrows(IllegalArgumentException.class, () -> a.setWeight(-1),
				"Requirement 7: an arc cannot have a negative weight");
	}

	@Test
	void testIsActive() {
		Transition t = new Transition("T0");
		t.addInArc(a);
		assertFalse(a.isActive());
		p.addToken(3);
		assertTrue(a.isActive());
	}

	@Test
	void testStep() {
		assertThrows(IllegalArgumentException.class, () -> a.step(),
				"Requirement 7: removing more tokens than the place holds must not make it negative");
		p.addToken(5);
		a.step();
		assertEquals(2, p.getToken());
	}

	// --- ArcEmpty ---

	@Test 
	void testConstructorArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertEquals("AE0", ae.getName());
		assertEquals(1, ae.getWeight());
		assertEquals("P0", ae.getPlace().getName());
	}

	@Test
	void testIsActiveArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertFalse(ae.isActive());
		p.addToken(1);
		assertTrue(ae.isActive());
	}

	@Test
	void testStepArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertThrows(IllegalArgumentException.class, () -> ae.step(),
				"Requirement 7: removing more tokens than the place holds must not make it negative");
		p.addToken(5);
		ae.step();
		assertEquals(0, p.getToken());
	}

	@Test 
	void testSetWeightArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertThrows(UnsupportedOperationException.class, () -> ae.setWeight(1),
				"Requirement 7: an emptying arc cannot have a weight");
	}

	@Test
	void testToStringArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertEquals("Emptying arc (AE0)", ae.toString());
	}

	// --- ArcZero ---

	@Test 
	void testConstructorArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertEquals("AZ0", az.getName());
		assertEquals(1, az.getWeight());
		assertEquals("P0", az.getPlace().getName());
	}

	@Test 
	void testIsActiveArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertTrue(az.isActive());
		p.addToken(1);
		assertFalse(az.isActive());
	}

	@Test
	void testStepArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		az.step();
		assertEquals(0, p.getToken());
		p.addToken(5);
		assertThrows(IllegalArgumentException.class, () -> az.step(),
				"Requirement 7: removing more tokens than the place holds must not make it negative");
	}

	@Test
	void testSetWeightArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertThrows(UnsupportedOperationException.class, () -> az.setWeight(1),
				"Requirement 7: a zero arc cannot have a weight");
	}

	@Test
	void testToStringArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertEquals("Zero arc (AZ0)", az.toString());
	}

}
