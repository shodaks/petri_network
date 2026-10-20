public class Place implements PetriLatticeInterface.Place{

    private int token;

    public Place(int token) {
        this.token = token;
    }

    @Override
    public int getToken() {
        return this.token;
    }

    @Override
    public void addToken(int incrementToken) {
        this.token += incrementToken;
    }

    @Override
    public void removeToken(int incrementToken) {
        this.token -= incrementToken;
    }
    
}
