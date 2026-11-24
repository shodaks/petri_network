package model;

/**
 * Zero arc: an incoming arc that is active only when its place is empty.
 *
 * It has no weight (the weight is fixed to 0) and never removes tokens.
 */
public class ArcZero extends InArc {

    /**
     * Creates a zero arc.
     *
     * @param name  the name of the arc
     * @param place the source place
     */
    public ArcZero(String name, Place place) {
        super(name, 1, place);
    }

    /**
     * Tells whether the arc allows its transition to be stepped.
     *
     * @return true if the place is empty
     */
    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() == 0;
    }

    /**
     * Does nothing: a zero arc never removes tokens.
     *
     * @throws IllegalArgumentException if the place is not empty (the arc is inactive)
     */
    @Override 
    public void step() {
        if (this.getPlace().getToken() != 0) {
            throw new IllegalArgumentException("A zero arc cannot be stepped when its place is not empty");
        }
        return ;
    }
    
    /**
     * Not supported: a zero arc has no weight.
     *
     * @param newWeight ignored
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setWeight(int newWeight) {
        throw new UnsupportedOperationException("A zero arc cannot have a weight");
    }

    /**
     * Returns a textual description of the arc.
     *
     * @return the description, e.g. "Zero arc (z)"
     */
    @Override 
    public String toString() {
        return "Zero arc (" + this.getName() + ")";
    }
    

}
