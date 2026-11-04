import model.Place;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests of {@link Place}.
 *
 * Adapted from the JUnit tutorial (jroyer) to our API:
 * Place(String, int), getToken(), addToken(int), removeToken(int).
 * The tests on arcs, which belonged to Place in the tutorial,
 * are in TestTransition: in our design transitions are the ones that know their arcs.
 */
public class TestPlace {

	private Place p;

	@BeforeEach
	void setUp() {
		// A fresh place for each test: no dependency on the execution order
		p = new Place("P0", 0);
	}

	@Test
	void testConstructor() {
		Place p3 = new Place("P3", 3);
		assertEquals("P3", p3.getName());
		assertEquals(3, p3.getToken());
	}

	@Test
	void testGetToken() {
		assertEquals(0, p.getToken());
		assertFalse(p.getToken() > 0);
	}

	@Test
	void testGetName() {
		assertEquals("P0", p.getName());
	}

	@Test
	void testSetName() {
		p.setName("P1");
		assertEquals("P1", p.getName());
	}

	@Test
	void testToString() {
		assertEquals("Place (P0) : 0", p.toString());
	}

	@Test
	void testAddToken() {
		p.addToken(2);
		assertEquals(2, p.getToken());
		p.addToken(3);
		assertEquals(5, p.getToken());
	}

	@Test
	void testRemoveToken() {
		p.addToken(3);
		p.removeToken(1);
		assertEquals(2, p.getToken());
		p.removeToken(2);
		assertEquals(0, p.getToken());
	}

	// --- Requirement 7 of the specification: "A place is empty or contains tokens"
	// => the number of tokens is never negative.
	// If an exception is chosen for these cases, use assertThrows(...).

	@Test
	void testConstructorNegativeTokens() {
		assertThrows(IllegalArgumentException.class, () -> new Place("P", -2),
				"Requirement 7: a place cannot be created with a negative number of tokens");
	}

	@Test
	void testRemoveTokenMoreThanAvailable() {
		p.addToken(1);
		assertThrows(IllegalArgumentException.class, () -> p.removeToken(2),
				"Requirement 7: removing more tokens than the place holds must not make it negative");
	}

	@Test
	void testAddTokenNegative() {
		p.addToken(1);
		assertThrows(IllegalArgumentException.class, () -> p.addToken(-3),
				"Requirement 7: addToken with a negative value must not make the place negative");
	}
}
