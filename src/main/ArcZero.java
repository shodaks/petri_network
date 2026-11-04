public class ArcZero extends InArc {

    public ArcZero(String name, Place place) {
        super(name, 0, place);
    }

    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() == 0;
    }

    @Override 
    public void step() {
        if (this.getPlace().getToken() != 0) {
            throw new IllegalArgumentException("Exigence 7 : retirer plus de jetons que la place n'en contient ne doit pas la rendre négative");
        }
        return ;
    }
    
    
    @Override
    public void setWeight(int newWeight) {
        throw new UnsupportedOperationException("Exigence 7 : un arc zéro ne peut pas avoir de poids");
    }

    @Override 
    public String toString() {
        return "Arc entrant zéro (" + this.getName() + ")";
    }
    

}
