public class TestPetriLattice {

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

    public TestPetriLattice(){
        plat = new PetriLattice();
        tr1 = plat.addTransition();
        pl1 = plat.addPlace(5);
        pl2 = plat.addPlace(3);
        pl3 = plat.addPlace(1);
        pl4 = plat.addPlace(1);
        pl5 = plat.addPlace(1);
        pl6 = plat.addPlace(1);
        a1 = plat.addInArc(2, pl1, tr1); 
        a2 = plat.addInArc(3, pl2, tr1); 
        a3 = plat.addOutArc(4, tr1, pl3); 
        a4 = plat.addOutArc(1, tr1, pl4); 
        a0 = plat.addArcZero(1, pl5, tr1); 
        ae = plat.addArcEmpty(1, pl6, tr1);
    }
    public static void main(String[] args){
        TestPetriLattice test = new TestPetriLattice();

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
