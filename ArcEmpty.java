public class ArcEmpty extends InArc {

    public ArcEmpty(int weight, Place place) {
        super(weight, place);
    }

    @Override 
    public boolean isActive(){
        return this.getPlace().getToken() > 0;
    }

    @Override
    public void step() {
        int allToken = this.getPlace().getToken();
        this.getPlace().removeToken(allToken);
    }
    
}