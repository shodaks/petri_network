package model;

import java.util.ArrayList;

/**
 * A transition of a Petri net. It owns its incoming arcs (from places) and
 * its outgoing arcs (to places).
 */
public class Transition {

    private ArrayList<OutArc> outArcs;
    private ArrayList<InArc> inArcs;
    private String name;

    /**
     * Creates a transition without any arc.
     *
     * @param name the name of the transition
     */
    public Transition(String name) {
        this.name = name;
        this.outArcs = new ArrayList<>();
        this.inArcs = new ArrayList<>();
    }

    /**
     * Returns the name of the transition.
     *
     * @return the name of the transition
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the transition.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Tells whether the transition can be stepped.
     *
     * @return true if all its incoming arcs are active (true when it has none)
     */
    public boolean isStepable() {
        for (InArc inarc : this.inArcs) {
            if (!inarc.isActive()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Steps the transition: every incoming arc removes its tokens, then every
     * outgoing arc adds its tokens. Does nothing if the transition is not stepable.
     */
    public void step() {
        if (this.isStepable()) {
            for (InArc inarc : this.inArcs) {
                inarc.step();
            }
            for (OutArc outarc : this.outArcs) {
                outarc.step();
            }
        }
    }

    /**
     * Returns the incoming arcs of the transition.
     *
     * @return the list of incoming arcs
     */
    public ArrayList<InArc> getInArcs() {
        return inArcs;
    }

    /**
     * Returns the outgoing arcs of the transition.
     *
     * @return the list of outgoing arcs
     */
    public ArrayList<OutArc> getOutArcs() {
        return outArcs;
    }

    /**
     * Adds an incoming arc to the transition.
     *
     * @param inarc the arc to add
     */
    public void addInArc(InArc inarc) {
        this.inArcs.add(inarc);
    }
    
    /**
     * Adds an outgoing arc to the transition.
     *
     * @param outarc the arc to add
     */
    public void addOutArc(OutArc outarc) {
        this.outArcs.add(outarc);
    }
}
