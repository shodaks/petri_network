package model;

/**
 * Incoming arc: goes from a {@link Place} to a {@link Transition}.
 *
 * It is active when its place holds at least as many tokens as its weight,
 * and it removes that many tokens when the transition is stepped.
 */
public class InArc extends Arc{

    /**
     * Creates an incoming arc.
     *
     * @param name   the name of the arc
     * @param weight the number of tokens required and removed at each step
     * @param place  the source place
     * @throws IllegalArgumentException if weight is negative
     */
    public InArc(String name, int weight, Place place) {
        super(name, weight, place);
    }

    /**
     * Tells whether the arc allows its transition to be stepped.
     *
     * @return true if the place holds at least as many tokens as the weight
     */
    public boolean isActive(){
        return this.getPlace().getToken() >= this.getWeight();
    }
    
    /**
     * Removes from the place as many tokens as the weight of the arc.
     *
     * @throws IllegalArgumentException if the place holds fewer tokens than the weight
     */
    public void step() {
        if (this.getPlace().getToken() < this.getWeight()) {
            throw new IllegalArgumentException("Requirement 7: removing more tokens than the place holds must not make it negative");
        }
        this.getPlace().removeToken(this.getWeight());
    }
    
    /**
     * Returns a textual description of the arc.
     *
     * @return the description, e.g. "Incoming arc (a1) : 2"
     */
    public String toString() {
        return "Incoming arc (" + this.getName() + ") : " + this.getWeight();
    }
}
