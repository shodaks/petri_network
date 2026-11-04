package model;

/**
 * Emptying arc: an incoming arc that is active as soon as its place holds
 * at least one token, and that removes all the tokens of the place when stepped.
 *
 * It has no weight (the weight is fixed to 0).
 */
public class ArcEmpty extends InArc {

    /**
     * Creates an emptying arc.
     *
     * @param name  the name of the arc
     * @param place the source place
     */
    public ArcEmpty(String name, Place place) {
        super(name, 1, place);
    }

    /**
     * Tells whether the arc allows its transition to be stepped.
     *
     * @return true if the place holds at least one token
     */
    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() > 0;
    }

    /**
     * Removes all the tokens from the place.
     *
     * @throws IllegalArgumentException if the place is already empty (the arc is inactive)
     */
    @Override
    public void step() {
        if (this.getPlace().getToken() == 0) {
            throw new IllegalArgumentException("An emptying arc cannot be stepped when its place is empty");
        }
        int allToken = this.getPlace().getToken();
        this.getPlace().removeToken(allToken);
    }

    /**
     * Not supported: an emptying arc has no weight.
     *
     * @param newWeight ignored
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setWeight(int newWeight) {
        throw new UnsupportedOperationException("An emptying arc cannot have a weight");
    }

    /**
     * Returns a textual description of the arc.
     *
     * @return the description, e.g. "Arc in vide (v)"
     */
    @Override 
    public String toString() {
        return "Emptying arc (" + this.getName() + ")";
    }
    
}
