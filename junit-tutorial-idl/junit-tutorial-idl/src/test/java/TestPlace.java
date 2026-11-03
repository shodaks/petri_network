import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Order;

/**
 * @author jroyer
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestPlace {
	static PetriNetInterface PNL;
	static Place P;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PNL = new PetriNet();
		P = new Place("P0", 0);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link Place#Place()}.
	 */
	@Test
	@Order(1)
	void testPlace() {
		assertEquals(new Place().getTokens(), 0);
		assertEquals(new Place().getSortants().size(), 0); 
		assertEquals(new Place().getEntrants().size(), 0);
	}

	/**
	 * Test method for {@link Place#Place(int)}.
	 */
	@Test
	@Order(2)
	void testPlaceInt() {
		assertEquals(new Place(2).getTokens(), 2);
		assertEquals(new Place(-2).getTokens(), 2); 
		assertEquals(new Place().getSortants().size(), 0);  
		assertEquals(new Place().getEntrants().size(), 0);
	}

	/**
	 * Test method for {@link Place#Place(java.lang.String, int)}.
	 */
	@Test
	@Order(3)	
	void testPlaceStringInt() {
		Place P = new Place("P3", 3);
		assertEquals(P.getName(), "P3");
		assertEquals(P.getTokens(), 3);
		//assertEquals(P.getSortants().size(), 1);  // TODO here not a good choice ?
		//assertEquals(P.getEntrants().size(), 2);
		P = new Place(null, 3);
		assertEquals(P.getName(), "PLACE");
	}
	
	/**
	 * Test method for {@link Place#getTokens()}.
	 */
	@Test
	@Order(7)	
	void testGetTokens() {
		assertEquals(P.getTokens(), 0);
		assertFalse(P.getTokens() > 0);
	}
	
	/**
	 * Test method for {@link Place#toString()}.
	 */
	@Test
	@Order(8)	
	void testToString() {
		assertEquals(P.toString(), "Place: P0 = 0\n");
	}	

	/**
	 * Test method for {@link Place#addTokens(int)}.
	 */
	@Test
	@Order(9)	
	void testAddTokens() {
		P.addTokens(2);
		assertEquals(P.getTokens(), 2);
	}
	

	/**
	 * Test method for {@link Place#removeTokens(int)}.
	 * Depends on the number and structure of the conditions
	 */
	@Test
	@Order(10)	
	void testRemoveTokens() {
		P.removeTokens(1);
		assertEquals(P.getTokens(), 1);
		P.removeTokens(-3);
		assertEquals(P.getTokens(), 0);
		P.removeTokens(2);
		assertEquals(P.getTokens(), 0);
	}

	/**
	 * Test method for {@link Place#getName()}.
	 */
	@Test
	@Order(11)	
	void testGetName() {
		assertEquals(P.getName(), "P0");
	}

	/**
	 * Test method for {@link Place#getSortants()}.
	 */
	@Test
	@Order(12)	
	void testGetSortants() {
		Transition T1 = PNL.addTransition("TT1");
		P.addArcEntrant(T1, 2);
		Arc a = P.getEntrants().get(0);
		assertEquals(a.getWeight(), 2);
		assertSame(a.getTarget(), P);
		assertSame(a.getSource(), T1);		
	}

	/**
	 * Test method for {@link Place#getEntrants()}.
	 */
	@Test
	@Order(13)	
	void testGetEntrants() {
		Transition S1 = PNL.addTransition("ST1");
		P.addArcSortant(S1, 2);
		Arc a = P.getSortants().get(0);
		assertEquals(a.getWeight(), 2);
		assertSame(a.getTarget(), S1);
		assertSame(a.getSource(), P);	
	}

	/**
	 * Test method for {@link Place#toString()}.
	 * TODO short-circuit case
	 */
	@Test
	@Order(14)	
	void testExists() {
		Transition T1 = PNL.addTransition("T1");
		Transition T2 = PNL.addTransition("T1");
		Transition T3 = PNL.addTransition("T3");
		Place P = new Place("P3", 3);
		//P.addArcEntrant(T1, 1); rather a basic expression like these
		P.getEntrants().add(new Arc(T1, 1));
		//P.getEntrants().add(new Arc(T2, 2));
		//P.getEntrants().add(new Arc(T3, 3));
		assertFalse(P.exists(null, true));
		assertTrue(P.exists(T1, true));
		assertTrue(P.exists(T2, true));
		assertFalse(P.exists(T3, true));
	}
	
	/**
	 * Test method for {@link Place#addArcEntrant(Transition, int)}.
	 */
	@Test
	@Order(15)	
	void testAddArcEntrant() {
		Transition T1 = PNL.addTransition("TT1");
		Place P = new Place("P3", 3);
		P.addArcEntrant(T1, 2);
		Arc a = P.getEntrants().get(0);
		assertEquals(a.getWeight(), 2);
		assertSame(a.getTarget(), P);
		assertSame(a.getSource(), T1);
		P.addArcEntrant(null, 3); // nothing done
		assertEquals(P.getEntrants().size(), 1);
		P.addArcEntrant(T1, 3); // nothing done
		assertEquals(P.getEntrants().size(), 1);
		Transition T2 = PNL.addTransition("TT1");
		P.addArcEntrant(T2, 4); // nothing done
		assertEquals(P.getEntrants().size(), 1);
		Transition T3 = PNL.addTransition("TT3");
		P.addArcEntrant(T3, 4); // add done
		assertEquals(P.getEntrants().size(), 2);
	}

	/**
	 * Test method for {@link Place#addArcSortant(Transition, int)}.
	 */
	@Test
	@Order(16)	
	// quite similar to above
	void testAddArcSortant() {
		Transition T1 = PNL.addTransition("TT1");
		Place P = new Place("P3", 3);
		P.addArcSortant(T1, 2);
		Arc a = P.getSortants().get(0);
		assertEquals(a.getWeight(), 2);
		assertSame(a.getTarget(), T1);
		assertSame(a.getSource(), P);
		P.addArcSortant(null, 3); // nothing done
		assertEquals(P.getSortants().size(), 1);
		P.addArcSortant(T1, 3); // nothing done
		assertEquals(P.getSortants().size(), 1);
		Transition T2 = PNL.addTransition("TT1");
		P.addArcSortant(T2, 4); // nothing done
		assertEquals(P.getSortants().size(), 1);
		Transition T3 = PNL.addTransition("TT3");
		P.addArcSortant(T3, 4); // add done
		assertEquals(P.getSortants().size(), 2);		
	}



}
