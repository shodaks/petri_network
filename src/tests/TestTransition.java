import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires de {@link Transition} (et des arcs qu'elle porte).
 *
 * Adaptés du tutoriel JUnit (jroyer) à notre API :
 * PetriLattice, addInArc / addOutArc, isStepable() (ex-isTriggerable), step() (ex-trigger).
 */
public class TestTransition {

	private PetriLattice pl;
	private Place p0, p1;
	private Transition t0, t1;

	/**
	 * Réseau du tutoriel, reconstruit avant chaque test :
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
	void testConstructeur() {
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
	// (dans le tutoriel : Place.addArcEntrant / addArcSortant)

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
	void testAddArcsDirectementSurTransition() {
		Place p = new Place("P", 0);
		Transition t = new Transition("T");
		InArc in = new InArc("in", 1, p);
		OutArc out = new OutArc("out", 1, p);
		t.addInArc(in);
		t.addOutArc(out);
		assertSame(in, t.getInArcs().get(0));
		assertSame(out, t.getOutArcs().get(0));
	}

	// Exigence 3 : « Il ne peut y avoir qu'un arc dans le même sens entre une place et une transition »
	@Test
	void testUnSeulArcEntrantParPlace() {
		assertThrows(IllegalArgumentException.class, () -> {
			pl.addInArc("doublon", 3, p0, t0);
		}, "Exigence 3 : un 2e arc P0 -> T0 ne doit pas être ajouté");
	}

	@Test
	void testUnSeulArcSortantParPlace() {
		assertThrows(IllegalArgumentException.class, () -> {
			pl.addOutArc("doublon", 3, t0, p1);
		}, "Exigence 3 : un 2e arc T0 -> P1 ne doit pas être ajouté");
	}

	// ---------------------------------------------------------------- Tir (arcs classiques)

	@Test
	void testIsStepable() {
		assertTrue(t0.isStepable());   // P0 contient 1 jeton, l'arc demande 1
		assertFalse(t1.isStepable());  // P1 est vide, l'arc demande 1
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
	void testStepNonTirableNeChangeRien() {
		t1.step();
		assertEquals(1, p0.getToken());
		assertEquals(0, p1.getToken());
	}

	@Test
	void testDeuxStepsSuccessifs() {
		// Le tutoriel tirait T0 deux fois sans rien vérifier :
		// le 2e tir doit être sans effet car T0 n'est plus tirable.
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
	void testTransitionSansArcEntrantEstTirable() {
		// Cas du cahier des charges : une transition avec un arc vers une place vide
		Place p = pl.addPlace("P", 0);
		Transition t = pl.addTransition("T");
		pl.addOutArc("a", 1, t, p);
		assertTrue(t.isStepable());
		t.step();
		assertEquals(1, p.getToken());
		assertTrue(t.isStepable());
	}

	// ---------------------------------------------------------------- Arcs zéro et videurs (TD2)

	// « Les arcs zéro ne sont actifs que quand la place source est vide. »
	@Test
	void testArcZeroPlaceVideTirable() {
		Place p = pl.addPlace("P", 0);
		Transition t = pl.addTransition("T");
		pl.addArcZero("z", p, t);
		assertTrue(t.isStepable(),
				"Arc zéro : la transition doit être tirable quand la place source est vide");
	}

	@Test
	void testArcZeroStepNeModifiePasLaPlace() {
		Place p = pl.addPlace("P", 0);
		Place sortie = pl.addPlace("S", 0);
		Transition t = pl.addTransition("T");
		pl.addArcZero("z", p, t);
		pl.addOutArc("o", 1, t, sortie);
		t.step();
		assertEquals(0, p.getToken(), "Arc zéro : le tir ne doit pas retirer de jeton de la place source");
		assertEquals(1, sortie.getToken(), "Arc zéro : la transition doit avoir été tirée");
	}

	@Test
	void testArcZeroPlaceNonVideNonTirable() {
		Place p = pl.addPlace("P", 1);
		Transition t = pl.addTransition("T");
		pl.addArcZero("z", p, t);
		assertFalse(t.isStepable(),
				"Arc zéro : la transition ne doit pas être tirable quand la place source contient des jetons");
	}

	// « Les arcs videurs sont actifs dès qu'il y a un jeton dans la place source
	//   et enlèvent tous les jetons présents lorsqu'ils sont activés. »
	@Test
	void testArcVideurVideLaPlace() {
		Place p = pl.addPlace("P", 3);
		Transition t = pl.addTransition("T");
		pl.addArcEmpty("v", p, t);
		assertTrue(t.isStepable());
		t.step();
		assertEquals(0, p.getToken(), "Arc videur : le tir doit enlever tous les jetons");
	}

	@Test
	void testArcVideurPlaceVideNonTirable() {
		Place p = pl.addPlace("P", 0);
		Transition t = pl.addTransition("T");
		pl.addArcEmpty("v", p, t);
		assertFalse(t.isStepable(),
				"Arc videur : inactif sans jeton => la transition ne doit pas être tirable");
	}
}
