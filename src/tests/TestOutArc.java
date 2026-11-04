import model.OutArc;
import model.Place;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests of {@link OutArc}.
 *
 * Adapted from the JUnit tutorial (jroyer) to our API:
 * Arc(String, int, Place), getName(), getWeight(), getPlace(), setName(), setWeight(), setPlace().
 * The tests on arcs, which belonged to Place in the tutorial,
 * are in TestTransition: in our design transitions are the ones that know their arcs.
 */


public class TestOutArc {
    
    
	private Place p;
    private OutArc a;

	@BeforeEach
	void setUp() {
		// A fresh place for each test: no dependency on the execution order
		p = new Place("P0", 0);
		a = new OutArc("A0", 3, p);
	}

	@Test
	void testConstructorOutArc() {
		assertEquals("A0", a.getName());
        assertEquals(3, a.getWeight());
		assertEquals("P0", a.getPlace().getName());
	}

	@Test
	void testConstructorOutArcNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () -> new OutArc("A0", -1, p),
				"Requirement 7: an arc cannot have a negative weight");
		assertThrows(IllegalArgumentException.class, () -> new OutArc("A0", 0, p),
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
		assertEquals("Outgoing arc (A0) : 3", a.toString());
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
	void testStep() {
		assertEquals(0, p.getToken());
		a.step();
		assertEquals(3, p.getToken());
		a.step();
		assertEquals(6, p.getToken());
	}
}