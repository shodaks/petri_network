import model.ArcEmpty;
import model.ArcZero;
import model.InArc;
import model.OutArc;
import model.Place;
import model.Transition;
import network.PetriLattice;

public class PetriLatticeManualTest {

    private PetriLattice plat;
    private Transition tr1;
    private Place pl1;
    private Place pl2;
    private Place pl3;
    private Place pl4;
    private Place pl5;
    private Place pl6;
    private InArc a1;
    private InArc a2;
    private OutArc a3;
    private OutArc a4;
    private ArcEmpty ae;
    private ArcZero a0;

    public PetriLatticeManualTest(){
        plat = new PetriLattice("petri1");
        tr1 = plat.addTransition("tr1");
        pl1 = plat.addPlace("pl1",5);
        pl2 = plat.addPlace("pl2",3);
        pl3 = plat.addPlace("pl3",1);
        pl4 = plat.addPlace("pl4",1);
        pl5 = plat.addPlace("pl5",1);
        pl6 = plat.addPlace("pl6",1);
        a1 = plat.addInArc("a1",2, pl1, tr1); 
        a2 = plat.addInArc("a2",3, pl2, tr1); 
        a3 = plat.addOutArc("a3",4, tr1, pl3); 
        a4 = plat.addOutArc("a4",1, tr1, pl4); 
        a0 = plat.addArcZero("a0", pl5, tr1); 
        ae = plat.addArcEmpty("ae",pl6, tr1);
    }
    public static void main(String[] args){
        PetriLatticeManualTest test = new PetriLatticeManualTest();

        // Displaying places before and after a step
        System.out.println(test.plat.getPlaces());

        System.out.println(test.tr1.isStepable());
        test.tr1.step();

        System.out.println(test.plat.getPlaces());

        // transition is no longer stepable
        System.out.println(test.tr1.isStepable());

        // Removing the place (and the empty arc it is linked to)
        test.plat.removePlace(test.pl6);
        System.out.println(test.plat.getPlaces());
        System.out.println(test.plat.getArcs());

    }
}
