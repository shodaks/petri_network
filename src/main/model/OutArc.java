package model;

/**
 * Outgoing arc: goes from a {@link Transition} to a {@link Place}.
 *
 * When the transition is stepped, it adds as many tokens to its place as its weight.
 */
public class OutArc extends Arc {

    /**
     * Creates an outgoing arc.
     *
     * @param name   the name of the arc
     * @param weight the number of tokens added at each step
     * @param place  the destination place
     * @throws IllegalArgumentException if weight is negative
     */
    public OutArc(String name, int weight, Place place) {
        super(name, weight, place);
    }
    
    /**
     * Adds to the place as many tokens as the weight of the arc.
     */
    public void step() {
        this.getPlace().addToken(this.getWeight());
    }

    /**
     * Returns a textual description of the arc.
     *
     * @return the description, e.g. "Outgoing arc (a2) : 3"
     */
    public String toString() {
        return "Outgoing arc (" + this.getName() + ") : " + this.getWeight();
    }
}
