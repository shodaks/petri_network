import java.util.Iterator;
import java.util.Vector;

/**
 * Class for Transition.
 *
 * @author jroyer lazy boy
 */
public class Transition {
	
	/** The nb transition. */
	private static int NB_TRANSITION;
	
	public static int getNB_TRANSITION() {
		return NB_TRANSITION;
	}

	/** The name. */
	private String name;
	
	/** The sortants. */
	private Vector<Arc> sortants;
	
	/** The entrants. */
	private Vector<Arc> entrants;

	/**
	 * Constructor with automatic name.
	 */
	public Transition() {
		this("T_" + NB_TRANSITION);
	}

	/**
	 * Instantiates a new transition.
	 *
	 * @param n the n
	 */
	public Transition(String n) {
		this.name = n;
		++NB_TRANSITION;
		this.sortants = new Vector<Arc>();
		this.entrants = new Vector<Arc>();
	}

	/**
	 * Add one edge from this to p.
	 *
	 * @param a the a
	 */
	public void addArcSortant(Arc a) {
		this.sortants.add(a);
	}

	/**
	 * Add one edge from p to this.
	 *
	 * @param a the a
	 */
	void addArcEntrant(Arc a) {
		this.entrants.add(a);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the sortants.
	 *
	 * @return the sortants
	 */
	public Vector<Arc> getSortants() {
		return sortants;
	}

	/**
	 * Gets the entrants.
	 *
	 * @return the entrants
	 */
	public Vector<Arc> getEntrants() {
		return entrants;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Transition: " + name + "\n"; 
	}
	
	/**
	 * Check if a transition is triggerable.
	 *
	 * @return true or false
	 */
	public boolean isTriggerable() {
		Iterator<Arc> var2 = this.entrants.iterator();
		boolean res = true;
		// TODO 1/4 missed
		while (var2.hasNext() && res) {
		// full coverage example
		//while (var2.hasNext() & res) {
			Arc e = (Arc) var2.next();
			if (!e.isEnable()) {  
				res = false;
			}
		}
		return res;		
	}
	
	/**
	 * Trigger a transition
	 * check all the entrants
	 * update sortants.
	 */
	public void trigger() {
		if (this.isTriggerable()) {
			// activate incoming edges
			Iterator<Arc> var2 = this.entrants.iterator();
			while (var2.hasNext()) {
				Arc e = (Arc) var2.next();
				e.fire();
			}
			// activate outgoing edges
			var2 = this.sortants.iterator();
			while (var2.hasNext()) {
				Arc e =  (Arc) var2.next(); 
				e.fire();
			}
		}		
	}
	
	/**
	 * Compute the tokens balance.
	 * That is the difference between tokens consumed and tokens produced
	 * return an int
	 *
	 * @return the int
	 */
	public int balance() {
		int consumed = 0;
		for (Arc A: this.entrants) {
			consumed += A.getWeight();
		}
		int produced = 0;
		for (Arc A: this.sortants) {
			produced += A.getWeight();
		}
		return produced - consumed;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	// generated
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	// generated
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}