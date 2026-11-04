import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires de {@link InArc}.
 *
 * Adaptés du tutoriel JUnit (jroyer) à notre API :
 * Arc(String, int, Place), getName(), getWeight(), getPlace(), setName(), setWeight(), setPlace().
 * Les tests sur les arcs, qui étaient portés par Place dans le tutoriel,
 * sont dans TestTransition : chez nous ce sont les transitions qui connaissent leurs arcs.
 */


public class TestInArc {
    
    
	private Place p;
    private InArc a;

	@BeforeEach
	void setUp() {
		// Une place neuve pour chaque test : pas de dépendance à l'ordre d'exécution
		p = new Place("P0", 0);
		a = new InArc("A0", 3, p);
	}

	@Test
	void testConstructeurInArc() {
		assertEquals("A0", a.getName());
        assertEquals(3, a.getWeight());
		assertEquals("P0", a.getPlace().getName());
	}

	@Test
	void testConstructeurInArcPoidsNegatif() {
		assertThrows(IllegalArgumentException.class, () -> new InArc("A0", -1, p),
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
		assertEquals("Arc entrant (A0) : 3", a.toString());
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
				"Exigence 7 : retirer plus de jetons que la place n'en contient ne doit pas la rendre négative");
		p.addToken(5);
		a.step();
		assertEquals(2, p.getToken());
	}

	// --- ArcEmpty ---

	@Test 
	void testConstructeurArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertEquals("AE0", ae.getName());
		assertEquals(0, ae.getWeight());
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
				"Exigence 7 : retirer plus de jetons que la place n'en contient ne doit pas la rendre négative");
		p.addToken(5);
		ae.step();
		assertEquals(0, p.getToken());
	}

	@Test 
	void testSetWeightArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertThrows(UnsupportedOperationException.class, () -> ae.setWeight(1),
				"Exigence 7 : un arc vide ne peut pas avoir de poids");
	}

	@Test
	void testToStringArcEmpty() {
		ArcEmpty ae = new ArcEmpty("AE0", p);
		assertEquals("Arc entrant vide (AE0)", ae.toString());
	}

	// --- ArcZero ---

	@Test 
	void testConstructeurArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertEquals("AZ0", az.getName());
		assertEquals(0, az.getWeight());
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
				"Exigence 7 : retirer plus de jetons que la place n'en contient ne doit pas la rendre négative");
	}

	@Test
	void testSetWeightArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertThrows(UnsupportedOperationException.class, () -> az.setWeight(1),
				"Exigence 7 : un arc zéro ne peut pas avoir de poids");
	}

	@Test
	void testToStringArcZero() {
		ArcZero az = new ArcZero("AZ0", p);
		assertEquals("Arc entrant zéro (AZ0)", az.toString());
	}

}
