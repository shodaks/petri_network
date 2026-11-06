import network.PetriLattice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Transition;
import model.Place;
import model.InArc;
import model.OutArc;

/**
 * Unit tests of {@link PetriLattice}.
 *
 * Adapted from the JUnit tutorial (jroyer) to our API:
 * PetriLattice(), getName(), getPlaces(), getTransitions(), addPlace(), addTransition().
 * The tests on arcs, which belonged to Place in the tutorial,
 * are in TestTransition: in our design transitions are the ones that know their arcs.
 */

public class TestPetriLattice {
    
    private PetriLattice pl;

    /**
	 * PetriLattice rebuilt before each test:
	 */
	@BeforeEach
	void setUp() {
		pl = new PetriLattice("Petri1");

    }

    // ---------------------------------------------------------------- Construction

	@Test
	void testConstructor() {
		PetriLattice p = new PetriLattice("Petri");
		assertEquals("Petri", p.getName());
        assertTrue(p.getTransitions().isEmpty());
        assertTrue(p.getPlaces().isEmpty());
        assertTrue(p.getArcs().isEmpty());
	}

    @Test
    void testGetName() {
        assertEquals("Petri1", pl.getName());
    }

    @Test 
    void testSetName() {
        pl.setName("Petri2");
        assertEquals("Petri2", pl.getName());
    }

    // ---------------------------------------------------------------- Places

    @Test 
    void testAddPlace() {
        Place p0 = pl.addPlace("P0", 1);
        assertEquals(1, pl.getPlaces().size());
        assertEquals(p0, pl.getPlaces().get(0));
    }

    @Test
    void testAddPlaceNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            pl.addPlace("P0", -1);
        });
    }

    @Test
    void testGetPlaces() {
        Place p0 = pl.addPlace("P0", 1);
        Place p1 = pl.addPlace("P1", 0);
        assertEquals(2, pl.getPlaces().size());
        assertEquals(p0, pl.getPlaces().get(0));
        assertEquals(p1, pl.getPlaces().get(1));
    }

    @Test 
    void testGetPlacesEmpty() {
        assertTrue(pl.getPlaces().isEmpty());
    }

    @Test 
    void testRemovePlace() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        pl.addInArc("a0", 1, p0, t0);
        pl.removePlace(p0);
        assertEquals(0, pl.getPlaces().size());
        assertEquals(0, pl.getArcs().size());
    }

    // ---------------------------------------------------------------- Transitions

    @Test
    void testAddTransition() {
        Transition t0 = pl.addTransition("T0");
        assertEquals(1, pl.getTransitions().size());
        assertEquals(t0, pl.getTransitions().get(0));
    }

    @Test
    void testGetTransitions() {
        Transition t0 = pl.addTransition("T0");
        Transition t1 = pl.addTransition("T1");
        assertEquals(2, pl.getTransitions().size());
        assertEquals(t0, pl.getTransitions().get(0));
        assertEquals(t1, pl.getTransitions().get(1));
    }

    @Test 
    void testGetTransitionsEmpty() {
        assertTrue(pl.getTransitions().isEmpty());
    }

    @Test 
    void testRemoveTransition() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        pl.addInArc("a0", 1, p0, t0);
        pl.removeTransition(t0);
        assertEquals(0, pl.getTransitions().size());
        assertEquals(0, pl.getArcs().size());
    }

    // ---------------------------------------------------------------- Arc

    @Test
    void testRemoveArc() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        InArc a0 = pl.addInArc("a0", 1, p0, t0);
        pl.removeArc(a0);
        assertEquals(0, pl.getArcs().size());
        assertFalse(t0.getInArcs().contains(a0));
    }

    @Test
    void testGetArcs() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        InArc a0 = pl.addInArc("a0", 1, p0, t0);
        assertEquals(1, pl.getArcs().size());
        assertEquals(a0, pl.getArcs().get(0));
    }

    @Test 
    void testGetArcsEmpty() {
        assertTrue(pl.getArcs().isEmpty());

    }

    // ---------------------------------------------------------------- inArcs

    @Test 
    void testAddInArc() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        InArc a0 = pl.addInArc("a0", 1, p0, t0);
        assertEquals(1, pl.getArcs().size());
        assertEquals(a0, pl.getArcs().get(0));
    }

    @Test 
    void testAddInArcNegativeWeight() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        assertThrows(IllegalArgumentException.class, () -> {
            pl.addInArc("a0", -1, p0, t0);
        });
    }

    @Test 
    void testAddInArcDuplicate() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        pl.addInArc("a0", 1, p0, t0);
        assertThrows(IllegalArgumentException.class, () -> {
            pl.addInArc("a1", 1, p0, t0);
        });
    }

    // ---------------------------------------------------------------- outArcs

    @Test
    void testAddOutArc() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        OutArc a0 = pl.addOutArc("a0", 1, t0, p0);
        assertEquals(1, pl.getArcs().size());
        assertEquals(a0, pl.getArcs().get(0));
    }

    @Test 
    void testAddOutArcNegativeWeight() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        assertThrows(IllegalArgumentException.class, () -> {
            pl.addOutArc("a0", -1, t0, p0);
        });
    }

    @Test 
    void testAddOutArcDuplicate() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        pl.addOutArc("a0", 1, t0, p0);
        assertThrows(IllegalArgumentException.class, () -> {
            pl.addOutArc("a1", 1, t0, p0);
        });
    }


    // ---------------------------------------------------------------- emptyArc

    @Test 
    void testAddArcEmpty() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        InArc a0 = pl.addArcEmpty("a0", p0, t0);
        assertEquals(1, pl.getArcs().size());
        assertEquals(a0, pl.getArcs().get(0));
    }

    // ---------------------------------------------------------------- ZeroArc

    @Test 
    void testAddArcZero() {
        Place p0 = pl.addPlace("P0", 1);
        Transition t0 = pl.addTransition("T0");
        InArc a01 = pl.addArcZero("a01", p0, t0);
        assertEquals(1, pl.getArcs().size());
        assertEquals(a01, pl.getArcs().get(0));
    }

}
