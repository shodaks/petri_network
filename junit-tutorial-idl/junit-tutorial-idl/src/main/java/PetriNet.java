import java.util.Vector;

/**
 * A Petri Net implementation.
 * But a very bad one
 * @author jroyer
 *
 */
public class PetriNet implements PetriNetInterface {
	
	/** The places. */
	private Vector<Place> places = new Vector<Place>();
	
	/** The transitions. */
	private Vector<Transition> transitions = new Vector<Transition>();
	
	/** The arcs. */
	private Vector<Arc> arcs = new Vector<Arc>();

	/**
	 * Adds the place.
	 *
	 * @param n the n
	 * @return the place
	 */
	public Place addPlace(int n) {
		Place place = new Place(n);
		this.places.add(place);
		return place;
	}

	/**
	 * Adds the place.
	 *
	 * @param name the name
	 * @param n the n
	 * @return the place
	 */
	public Place addPlace(String name, int n) {
		Place place = new Place(name, n);
		this.places.add(place);
		return place;
	}

	/**
	 * Adds the transition.
	 *
	 * @return the transition
	 */
	public Transition addTransition() {
		Transition transition = new Transition();
		this.transitions.add(transition);
		return transition;
	}

	/**
	 * Adds the transition.
	 *
	 * @param name the name
	 * @return the transition
	 */
	public Transition addTransition(String name) {
		Transition transition = new Transition(name);
		this.transitions.add(transition);
		return transition;
	}

	/**
	 * Add an arc  transition -> place
	 * TODO test existence p and t and arc too
	 * * redundancy not a good design option.
	 *
	 * @param p the p
	 * @param t the t
	 * @param w the w
	 */
	public void addPlace2TransitionArc(Place p, Transition t, int w) {
		Arc a = new Arc(p, t, w);
		this.arcs.add(a);
		t.addArcEntrant(a);
	}

	/**
	 * Add an arc  place -> transition
	 * 	 
	 * TODO test existence p and t and arc too.
	 *
	 * @param t the t
	 * @param p the p
	 * @param w the w
	 */
	public void addTransition2PlaceArc(Transition t, Place p, int w) {
		Arc a = new Arc(t, p, w);
		this.arcs.add(a);
		t.addArcSortant(a);
	}

	/**
	 * Trigger a selected transition.
	 *
	 * @param t the t
	 */
	public void trigger(Transition t) {
		t.trigger();
	}

	/**
	 * Gets the transitions.
	 *
	 * @return the transitions
	 */
	public Vector<Transition> getTransitions() {
		return this.transitions;
	}

	/**
	 * Gets the places.
	 *
	 * @return the places
	 */
	public Vector<Place> getPlaces() {
		return this.places;
	}

	/**
	 * Tokens.
	 *
	 * @param place the place
	 * @return the int
	 */
	public int tokens(Place place) {
		return place.getTokens();
	}

	/**
	 * Adds the tokens.
	 *
	 * @param place the place
	 * @param n the n
	 */
	public void addTokens(Place place, int n) {
		place.addTokens(n);
	}

	/**
	 * Removes the tokens.
	 *
	 * @param place the place
	 * @param n the n
	 */
	public void removeTokens(Place place, int n) {
		place.removeTokens(n);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String res = "****************************\n -------------- Places \n";
		for (Place  P : this.places) {
			res += P.toString();
		}
		res += "----------------- Transitions \n";
		for (Transition  T : this.transitions) {
			res += T.toString();
		}
		res += "----------------- Arcs \n";
		for (Arc  A : this.arcs) {
			res += A.toString();
		}
		return res + "************\n";
	}
	
	/**
	 * check a configuration.
	 * cfg: a vector of integer describing the configuration. 
	 * return True if the configuration is compliant with the contains of the places
	 *
	 * @param cfg the cfg
	 * @return true, if successful
	 */
	public boolean reached(Vector<Integer> cfg) {
		boolean res = true;
		for(int i=0; i < this.places.size(); i++) {
			//System.out.println("reached " + this.places.get(i).getTokens());
			res = res && (this.places.get(i).getTokens() == cfg.get(i));
		}
		return res;
	}
	
	/**
	 * Simulate the Petri Net until a given configuration is reached.
	 * if not reached select a transition to trigger and continue
	 *
	 * @param cfg the cfg
	 */
	public void simulate(Vector<Integer> cfg) {
		if (this.reached(cfg)) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
			int maxbal = 0;
			Transition selected = null; // Could be a problem to test if no T?
			for(Transition T: this.transitions) {
				if (T.isTriggerable()) {
					int balance = T.balance();
					if (balance > maxbal) { 
						selected = T;
						maxbal = balance;
					}
				}
			}
			System.out.println("selected " + selected.getName() + " balance " + maxbal);
			this.trigger(selected);
			this.simulate(cfg);
		}
	}
	
}