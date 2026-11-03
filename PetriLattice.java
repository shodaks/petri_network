import java.util.ArrayList;

public class PetriLattice implements PetriLatticeInterface{

    private ArrayList<Transition> transitions;
    private ArrayList<Place> places;
    private ArrayList<Arc> arcs;

    public PetriLattice(){
        this.transitions = new ArrayList<>();
        this.places = new ArrayList<>();
        this.arcs = new ArrayList<>();

    }

    public ArrayList<Transition> getTransitions(){
        return this.transitions;
    }

    public ArrayList<Place> getPlaces(){
        return this.places;
    }

    public ArrayList<Arc> getArcs(){
        return this.arcs;
    }

    /*
    Add a place to the Perti lattice and return it
     */
    public Place addPlace(int weight){
        Place p;
        p = new Place(weight);
        this.places.add(p);
        return p;
    }

    @Override
    /*
    Add a transition to the transition list and return it
     */
    public Transition addTransition(String name) {
        Transition t;
        t = new Transition(name);
        this.transitions.add(t);
        return t;    
    }

    @Override
    /*
    Add a InArc. It is linked to a place and a transition and added to the 
    transition InArcs list. Also added in the petri lattice.
    It is also returned.
     */
    public InArc addInArc(String name, int weight, Place place, Transition transition) {
        InArc a;
        a = new InArc(name, weight, place);
        transition.getInArcs().add(a);
        this.arcs.add(a);
        return a;
    }

    @Override
    /*
    Add a OutArc. It is linked to a place and a transition and added to the 
    transition OutArcs list.
    It is also returned.
     */
    public OutArc addOutArc(String name, int weight, Transition transition, Place place) {
        OutArc a;
        a = new OutArc(name , weight, place);
        transition.getOutArcs().add(a);
        this.arcs.add(a);
        return a;
    }

    @Override
    /*
    Add an "ArcZero". It is linked to a place and a transition and added to the 
    transition InArcs list.
    It is also returned.
     */
    public ArcZero addArcZero(String name, int weight, Place place, Transition transition) {
        ArcZero a;
        a = new ArcZero(name, weight, place);
        transition.getInArcs().add(a);
        this.arcs.add(a);
        return a;
    }

    @Override
    /*
    Add an "ArcEmpty". It is linked to a place and a transition and added to the 
    transition InArcs list.
    It is also returned.
     */
    public ArcEmpty addArcEmpty(String name, int weight, Place place, Transition transition) {
        ArcEmpty a;
        a = new ArcEmpty(name, weight, place);
        transition.getInArcs().add(a);
        this.arcs.add(a);
        return a;    
    }

    @Override
    /*
    remove an arc from the latice. Remove it from its transition's list.
     */
    public void removeArc(Arc arc, Transition transition) {
        if (arc instanceof OutArc){
            transition.getOutArcs().remove(arc);
        }
        else {
            transition.getInArcs().remove(arc);
        }
        this.arcs.remove(arc);
    }

    @Override
    /*
    Remove a place from the petri lattice. Also remove all its connected arcs.
     */
    public void removePlace(Place place) {
        for (Transition t : transitions){
            for (Arc a : t.getInArcs()){
                if (a.getPlace().equals(place)){
                    t.getInArcs().remove(a);
                    break;
                }
            }
            for (Arc a : t.getOutArcs()){
                if (a.getPlace().equals(place)){
                    t.getOutArcs().remove(a);
                    break;
                }
            }
        }
        places.remove(place);
    }

    @Override
    public void removeTransition(Transition transition) {
        transitions.remove(transition);
    }

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

    public void main() {
        PetriLattice PL1 = new PetriLattice();
        Transition t1 = PL1.addTransition("t1");
        Place p1 = new Place(5);
        Place p2 = new Place(2);
        InArc arcin = new InArc("a1", 1, p1);
        t1.addInArc(arcin);
        OutArc arcout = new OutArc("a2", 10, p2);
        t1.addOutArc(arcout);
        System.out.println(PL1.toString());
        t1.step();
        System.out.println(PL1.toString());

        
    }
}
