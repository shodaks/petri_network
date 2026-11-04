import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires de {@link OutArc}.
 *
 * Adaptés du tutoriel JUnit (jroyer) à notre API :
 * Arc(String, int, Place), getName(), getWeight(), getPlace(), setName(), setWeight(), setPlace().
 * Les tests sur les arcs, qui étaient portés par Place dans le tutoriel,
 * sont dans TestTransition : chez nous ce sont les transitions qui connaissent leurs arcs.
 */


public class TestOutArc {
    
    
	private Place p;
    private OutArc a;

	@BeforeEach
	void setUp() {
		// Une place neuve pour chaque test : pas de dépendance à l'ordre d'exécution
		p = new Place("P0", 0);
		a = new OutArc("A0", 3, p);
	}

	@Test
	void testConstructeurOutArc() {
		assertEquals("A0", a.getName());
        assertEquals(3, a.getWeight());
		assertEquals("P0", a.getPlace().getName());
	}

	@Test
	void testConstructeurOutArcPoidsNegatif() {
		assertThrows(IllegalArgumentException.class, () -> new OutArc("A0", -1, p),
				"Exigence 7 : un arc ne peut pas être créé avec un poids négatif");
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
		assertEquals("Arc sortant (A0) : 3", a.toString());
	}

	@Test
	void testSetWeight() {
		a.setWeight(5);
		assertEquals(5, a.getWeight());
	}

	@Test
	void testSetWeightNegatif() {
		assertThrows(IllegalArgumentException.class, () -> a.setWeight(-1),
				"Exigence 7 : un arc ne peut pas être créé avec un poids négatif");
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