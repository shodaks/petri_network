package model;

/**
 * Abstract base class of all arcs of a Petri net.
 *
 * An arc links a {@link Place} to a {@link Transition} (see {@link InArc}) or
 * a {@link Transition} to a {@link Place} (see {@link OutArc}). It has a name,
 * a weight and the place it is attached to.
 */
public abstract class Arc{

    private String name;
    private int weight;
    private Place place;

    /**
     * Creates an arc.
     *
     * @param name   the name of the arc
     * @param weight the weight of the arc
     * @param place  the place the arc is attached to
     * @throws IllegalArgumentException if weight is not positive
     */
    public Arc(String name, int weight, Place place) {
        this.name = name;
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be positive"); // Added following the other group's review remarks
        }
        this.weight = weight;
        this.place = place;
    }

    /**
     * Returns the name of the arc.
     *
     * @return the name of the arc
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes the name of the arc.
     *
     * @param newName the new name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns the weight of the arc.
     *
     * @return the weight of the arc
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Attaches the arc to another place.
     *
     * @param newPlace the new place
     */
    public void setPlace(Place newPlace) {
        this.place = newPlace;
    }

    /**
     * Returns the place the arc is attached to.
     *
     * @return the place of the arc
     */
    public Place getPlace() {
        return this.place;
    }

    /**
     * Changes the weight of the arc.
     *
     * @param newWeight the new weight
     * @throws IllegalArgumentException if newWeight not positive
     */
    public void setWeight(int newWeight) {
        if (newWeight <= 0) {
            throw new IllegalArgumentException("weight must be positive"); // Added following the other group's review remarks
        }
        this.weight = newWeight;
    }
    
}
