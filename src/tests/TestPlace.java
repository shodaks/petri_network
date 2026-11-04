import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires de {@link Place}.
 *
 * Adaptés du tutoriel JUnit (jroyer) à notre API :
 * Place(String, int), getToken(), addToken(int), removeToken(int).
 * Les tests sur les arcs, qui étaient portés par Place dans le tutoriel,
 * sont dans TestTransition : chez nous ce sont les transitions qui connaissent leurs arcs.
 */
public class TestPlace {

	private Place p;

	@BeforeEach
	void setUp() {
		// Une place neuve pour chaque test : pas de dépendance à l'ordre d'exécution
		p = new Place("P0", 0);
	}

	@Test
	void testConstructeur() {
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

	// --- Exigence 7 du cahier des charges : « Une place est vide ou contient des jetons »
	// => le nombre de jetons n'est jamais négatif.
	// Si vous choisissez de lever une exception sur ces cas, remplacez par assertThrows(...).

	@Test
	void testConstructeurJetonsNegatifs() {
		assertThrows(IllegalArgumentException.class, () -> new Place("P", -2),
				"Exigence 7 : une place ne peut pas être créée avec un nombre de jetons négatif");
	}

	@Test
	void testRemoveTokenPlusQueDisponible() {
		p.addToken(1);
		assertThrows(IllegalArgumentException.class, () -> p.removeToken(2),
				"Exigence 7 : retirer plus de jetons que la place n'en contient ne doit pas la rendre négative");
	}

	@Test
	void testAddTokenNegatif() {
		p.addToken(1);
		assertThrows(IllegalArgumentException.class, () -> p.addToken(-3),
				"Exigence 7 : addToken avec une valeur négative ne doit pas rendre la place négative");
	}
}
