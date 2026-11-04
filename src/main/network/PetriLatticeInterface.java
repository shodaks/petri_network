package network;

import model.Arc;
import model.Place;
import model.Transition;

/**
 * Service interface to build and modify a Petri net.
 */
public interface PetriLatticeInterface {

    // public Place addPlace(int token);

    /**
     * Adds a transition to the net.
     *
     * @param name the name of the transition
     * @return the new transition
     */
    public Transition addTransition(String name);

    /**
     * Adds a place to the net.
     *
     * @param name   the name of the place
     * @param weight the initial number of tokens of the place
     * @return the new place
     */
    public Place addPlace(String name, int weight);

    /**
     * Adds an incoming arc from a place to a transition.
     *
     * @param name       the name of the arc
     * @param weight     the weight of the arc
     * @param place      the source place
     * @param transition the destination transition
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an incoming arc from this place
     */
    public Arc addInArc(String name, int weight, Place place, Transition transition);

    /**
     * Adds a zero arc (active only when the place is empty) from a place to a transition.
     *
     * @param name       the name of the arc
     * @param place      the source place
     * @param transition the destination transition
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an incoming arc from this place
     */
    public Arc addArcZero(String name, Place place, Transition transition);

    /**
     * Adds an emptying arc (removes all the tokens of the place) from a place to a transition.
     *
     * @param name       the name of the arc
     * @param place      the source place
     * @param transition the destination transition
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an incoming arc from this place
     */
    public Arc addArcEmpty(String name, Place place, Transition transition);

    /**
     * Adds an outgoing arc from a transition to a place.
     *
     * @param name       the name of the arc
     * @param weight     the weight of the arc
     * @param transition the source transition
     * @param place      the destination place
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an outgoing arc to this place
     */
    public Arc addOutArc(String name,int weight, Transition transition, Place place);

    /**
     * Removes an arc from the net and from its transition.
     *
     * @param arc        the arc to remove
     * @param transition the transition the arc belongs to
     */
    public void removeArc(Arc arc, Transition transition);

    /**
     * Removes a place from the net, together with the arcs connected to it.
     *
     * @param place the place to remove
     */
    public void removePlace(Place place);

    //public void addToken(int incrementToken, Place place);

    //public void removeToken(int incrementToken, Place place);

    /**
     * Removes a transition from the net.
     *
     * @param transition the transition to remove
     */
    public void removeTransition(Transition transition);

    //public void step(Transition transition);    
}
