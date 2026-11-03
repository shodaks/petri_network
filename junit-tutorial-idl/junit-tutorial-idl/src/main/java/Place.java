import java.util.Vector;

/**
 * Place for Petri Net.
 *
 * @author jroyer
 */
public class Place {
	
	/** The nb place. */
	// to count place numbering
	private static int NB_PLACE; 
	
	/** The name. */
	private String name; 
	
	/** The tokens. */
	private int tokens;
	
	/** The sortants. */
	private Vector<Arc> sortants; // direction relative to the place
	
	/** The entrants. */
	private Vector<Arc> entrants;

	/**
	 * few constructors.
	 */
	public Place() {
		this(0);
	}

	/**
	 * Constructor with automatic name.
	 * @param n gets only its absolute value
	 */
	public Place(int n) {
		this("P_" + NB_PLACE, Math.abs(n));
	}

	/**
	 * Instantiates a new place.
	 *
	 * @param name the name
	 * @param n the n
	 */
	public Place(String name, int n) {
		if (name == null) {
			this.name = "PLACE";
		} else {
			this.name = name;
		}
		++NB_PLACE;
		this.tokens = n;
		this.sortants = new Vector<Arc>();
		this.entrants = new Vector<Arc>();
	}

	/**
	 * Add one arc from t to this.
	 *
	 * @param t the t
	 * @param w Think about to test all the conditions
	 */
	public void addArcEntrant(Transition t, int w) {
		if (t != null) {
			// test if exists as incoming edge
			if (! this.exists(t, true)) {
				this.entrants.add(new Arc(t, this, w));
			}
		}
	}

	/**
	 * Add one arc from this to t.
	 *
	 * @param t the t
	 * @param w Think about to test all the conditions
	 */
	public void addArcSortant(Transition t, int w) {
		if (t != null) {
			// test if exists as outgoing edge
			if (! this.exists(t, false)) {			
				this.sortants.add(new Arc(this, t, w));
			}
		}
	}

	/**
	 * Adds the tokens.
	 *
	 * @param n the n
	 */
	public void addTokens(int n) {
		this.tokens += n;
	}

	/**
	 * Remove a number of tokens.
	 *
	 * @param n If n negative or > tokens will be 0
	 */
	public void removeTokens(int n) {
		if (n <= 0) {
			this.tokens = 0;
		} else if (this.tokens >= n) {
			this.tokens -= n;
		} else {
			this.tokens = 0;	
		}
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
	 * Gets the tokens.
	 *
	 * @return the tokens
	 */
	public int getTokens() {
		return tokens;
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
		String res = "Place: " + name + " = " + tokens + "\n"; 
		return res;
	}
	
	
	/**
	 * Auxiliary to help in testing
	 * direction boolean true = entrants
	 * TODO better with stream.
	 *
	 * @param t the t
	 * @param direction the direction
	 * @return true, if successful
	 */
	public boolean exists(Transition t, boolean direction) {
		if (t == null) {
			return false;
		} else {
			Vector<Arc> test;
			if (direction) {
				test = this.entrants;
			} else {
				test = this.sortants;
			}
			boolean res = false;
			for (Arc edge : test) {
				// TODO coverage problem 1/8 due to short-circuit ?
				// more clear with this formating 1/2
				res = res || 
						(direction ? edge.getSource().equals(t) : edge.getTarget().equals(t)); 
				// full coverage example
				//res = res | (direction ? edge.getSource().equals(t) : edge.getTarget().equals(t)); 
			}
			return res;
		}
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	// generated not tested
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
	// generated not tested
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
} // end Place