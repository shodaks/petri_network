import java.util.ArrayList;

public class PetriLattice implements PetriLatticeInterface{

    private ArrayList<Transition> transitions;

    @Override
    /*
    La fonction elle sert à rien si on stocke place nulle part.
    p = new Place(token) fait la même chose
     */
    public Place addPlace(int token) {
        return new Place(token);    
    }

    @Override
    /*
    Add a transition to the transition list and return it
     */
    public Transition addTransition() {
        Transition t;
        t = new Transition();
        transitions.add(t);
        return t;    
    }

    @Override
    /*
    Add a InArc. It is linked to a place and a transition and added to the 
    transition InArcs list.
    It is also returned.
     */
    public Arc addInArc(int weight, Place place, Transition transition) {
        InArc a;
        a = new InArc(weight, place);
        transition.getInArcs().add(a);
        return a;
    }

    @Override
    /*
    Add a OutArc. It is linked to a place and a transition and added to the 
    transition OutArcs list.
    It is also returned.
     */
    public Arc addOutArc(int weight, Place place, Transition transition) {
        OutArc a;
        a = new OutArc(weight, place);
        transition.getOutArcs().add(a);
        return a;
    }

    @Override
    /*
    Add an "ArcZero". It is linked to a place and a transition and added to the 
    transition InArcs list.
    It is also returned.
     */
    public Arc addArcZero(int weight, Place place, Transition transition) {
        ArcZero a;
        a = new ArcZero(weight, place);
        transition.getInArcs().add(a);
        return a;
    }

    @Override
    /*
    Add an "ArcEmpty". It is linked to a place and a transition and added to the 
    transition InArcs list.
    It is also returned.
     */
    public Arc addArcEmpty(int weight, Place place, Transition transition) {
        ArcEmpty a;
        a = new ArcEmpty(weight, place);
        transition.getInArcs().add(a);
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
    }

    @Override
    /*
    place n'est stocké nul part, il faut le remove dans arc.place.
     */
    public void removePlace(Place place, Arc arc) {
        arc.getP.
    }

    @Override
    public void removeTransition(Transition transition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTransition'");
    }
    
}
