import java.util.ArrayList;

public class PetriLattice implements PetriLatticeInterface{

    private ArrayList<Transition> transitions;

    @Override
    public PetriLatticeInterface.Place addPlace(int token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPlace'");
    }

    @Override
    public PetriLatticeInterface.Transition addTransition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public PetriLatticeInterface.Arc addInArc(int weight, PetriLatticeInterface.Place place) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addInArc'");
    }

    @Override
    public PetriLatticeInterface.Arc addArcZero(int weight, PetriLatticeInterface.Place place) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addArcZero'");
    }

    @Override
    public PetriLatticeInterface.Arc addArcEmpty(int weight, PetriLatticeInterface.Place place) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addArcEmpty'");
    }

    @Override
    public PetriLatticeInterface.Arc addOutArc(int weight, PetriLatticeInterface.Place place) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOutArc'");
    }

    @Override
    public void removeArc(PetriLatticeInterface.Arc arc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeArc'");
    }

    @Override
    public void removePlace(PetriLatticeInterface.Place place) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePlace'");
    }

    @Override
    public void removeTransition(PetriLatticeInterface.Transition transition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTransition'");
    }
    
}
