package network;

import java.util.ArrayList;
import model.*;

/**
 * A Petri net: it holds its places, its transitions and its arcs.
 */
public class PetriLattice implements PetriLatticeInterface{

    private String name;
    private ArrayList<Transition> transitions;
    private ArrayList<Place> places;
    private ArrayList<Arc> arcs;

    /**
     * Creates an empty Petri net.
     */
    public PetriLattice(String name){
        this.name = name;
        this.transitions = new ArrayList<>();
        this.places = new ArrayList<>();
        this.arcs = new ArrayList<>();

    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns the transitions of the net.
     *
     * @return the list of transitions
     */
    public ArrayList<Transition> getTransitions(){
        return this.transitions;
    }

    /**
     * Returns the places of the net.
     *
     * @return the list of places
     */
    public ArrayList<Place> getPlaces(){
        return this.places;
    }

    /**
     * Returns the arcs of the net.
     *
     * @return the list of arcs
     */
    public ArrayList<Arc> getArcs(){
        return this.arcs;
    }

    /**
     * Adds a place to the Petri net and returns it.
     *
     * @param name   the name of the place
     * @param weight the initial number of tokens of the place
     * @return the new place
     * @throws IllegalArgumentException if weight is negative
     */
    public Place addPlace(String name, int weight){
        Place p;
        p = new Place(name, weight);
        this.places.add(p);
        return p;
    }

    /**
     * Adds a transition to the transition list and returns it.
     *
     * @param name the name of the transition
     * @return the new transition
     */
    @Override
    public Transition addTransition(String name) {
        Transition t;
        t = new Transition(name);
        this.transitions.add(t);
        return t;    
    }

    /**
     * Adds an incoming arc. It is linked to a place and a transition, added to
     * the InArcs list of the transition and to the Petri net, then returned.
     *
     * @param name       the name of the arc
     * @param weight     the weight of the arc
     * @param place      the source place
     * @param transition the destination transition
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an incoming arc from this place
     */
    @Override
    public InArc addInArc(String name, int weight, Place place, Transition transition) {
        InArc a;
        for (Arc arc : transition.getInArcs()){
            if (arc.getPlace().equals(place)){
                throw new IllegalArgumentException("Transition " + transition.getName() + " already has an incoming arc from place " + place.getName());
            }
        }
        a = new InArc(name, weight, place);
        transition.getInArcs().add(a);
        this.arcs.add(a);
        return a;
    }

    /**
     * Adds an outgoing arc. It is linked to a transition and a place, added to
     * the OutArcs list of the transition and to the Petri net, then returned.
     *
     * @param name       the name of the arc
     * @param weight     the weight of the arc
     * @param transition the source transition
     * @param place      the destination place
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an outgoing arc to this place
     */
    @Override
    public OutArc addOutArc(String name, int weight, Transition transition, Place place) {
        OutArc a;
        for (Arc arc : transition.getOutArcs()){
            if (arc.getPlace().equals(place)){
                throw new IllegalArgumentException("Transition " + transition.getName() + " already has an outgoing arc to place " + place.getName());
            }
        }
        a = new OutArc(name , weight, place);
        transition.getOutArcs().add(a);
        this.arcs.add(a);
        return a;
    }

    /**
     * Adds a zero arc. It is linked to a place and a transition, added to the
     * InArcs list of the transition and to the Petri net, then returned.
     *
     * @param name       the name of the arc
     * @param place      the source place
     * @param transition the destination transition
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an incoming arc from this place
     */
    @Override
    public ArcZero addArcZero(String name, Place place, Transition transition) {
        ArcZero a;
        for (Arc arc : transition.getInArcs()){
            if (arc.getPlace().equals(place)){
                throw new IllegalArgumentException("Transition " + transition.getName() + " already has an incoming arc from place " + place.getName());
            }
        }
        a = new ArcZero(name, place);
        transition.getInArcs().add(a);
        this.arcs.add(a);
        return a;
    }

    /**
     * Adds an emptying arc. It is linked to a place and a transition, added to
     * the InArcs list of the transition and to the Petri net, then returned.
     *
     * @param name       the name of the arc
     * @param place      the source place
     * @param transition the destination transition
     * @return the new arc
     * @throws IllegalArgumentException if the transition already has an incoming arc from this place
     */
    @Override
    public ArcEmpty addArcEmpty(String name, Place place, Transition transition) {
        ArcEmpty a;
        for (Arc arc : transition.getInArcs()){
            if (arc.getPlace().equals(place)){
                throw new IllegalArgumentException("Transition " + transition.getName() + " already has an incoming arc from place " + place.getName());
            }
        }
        a = new ArcEmpty(name, place);
        transition.getInArcs().add(a);
        this.arcs.add(a);
        return a;    
    }

    /**
     * Removes an arc from the net and from the list of its transition.
     *
     * @param arc        the arc to remove
     * @param transition the transition the arc belongs to
     */
    @Override
    public void removeArc(Arc arc, Transition transition) {
        if (arc instanceof OutArc){
            transition.getOutArcs().remove(arc);
        }
        else {
            transition.getInArcs().remove(arc);
        }
        this.arcs.remove(arc);
    }

    /**
     * Removes a place from the Petri net, together with all the arcs connected to it.
     *
     * @param place the place to remove
     */
    @Override
    public void removePlace(Place place) {
        for (Transition t : transitions){
            for (Arc a : t.getInArcs()){
                if (a.getPlace().equals(place)){
                    t.getInArcs().remove(a);
                    this.removeArc(a, t);
                    break;
                }
            }
            for (Arc a : t.getOutArcs()){
                if (a.getPlace().equals(place)){
                    t.getOutArcs().remove(a);
                    this.removeArc(a, t);
                    break;
                }
            }
        }
        places.remove(place);
    }

    /**
     * Removes a transition from the Petri net.
     *
     * @param transition the transition to remove
     */
    @Override
    public void removeTransition(Transition transition) {
        transitions.remove(transition);
        for (Arc a : transition.getInArcs()){
            this.places.remove(a.getPlace());
            this.arcs.remove(a);
        }
        for (Arc a : transition.getOutArcs()){
            this.places.remove(a.getPlace());
            this.arcs.remove(a);
        }
    }

    /**
     * Removes an Arc from the Petri net.
     *
     * @param Arc the transition to remove
     */
    @Override
    public void removeArc(Arc Arc) {
        arcs.remove(Arc);
        for (Transition t : this.transitions) {
            if (t.getInArcs().contains(Arc)) {
                t.getInArcs().remove(Arc);
                break;
            }
            if (t.getOutArcs().contains(Arc)) {
                t.getOutArcs().remove(Arc);
                break;
            }
        }

    }

    /**
     * Returns a textual description of the net: one line per arc.
     *
     * @return the description of the net
     */
    public String toString() {
        String out = "";
        for (Transition t : transitions) {
            for (InArc inarc : t.getInArcs()) {
                out += (inarc.getPlace().toString() + " -> " + inarc.toString() + "\n");
            }
            for (OutArc outarc : t.getOutArcs()) {
                out += (outarc.toString() + " -> " + outarc.getPlace().toString() + "\n");
            }
        }
        return out;
    }

    /**
     * Small demonstration: builds a net with one transition, then prints it
     * before and after a step. It is an instance method, not a program entry point.
     */
    public void main() {
        PetriLattice PL1 = new PetriLattice("Petri1");
        Transition t1 = PL1.addTransition("t1");
        Place p1 = PL1.addPlace("p1", 5);
        Place p2 = PL1.addPlace("p2", 2);
        InArc arcin = new InArc("a1", 1, p1);
        t1.addInArc(arcin);
        OutArc arcout = new OutArc("a2", 10, p2);
        t1.addOutArc(arcout);
        System.out.println(PL1.toString());
        t1.step();
        System.out.println(PL1.toString());
    }
}
