public class Place {

    private int token;

    public Place(int token) {
        this.token = token;
    }

    public int getToken() {
        return this.token;
    }

    public void addToken(int incrementToken) {
        this.token += incrementToken;
    }

    public void removeToken(int incrementToken) {
        this.token -= incrementToken;
    }

    public String toString() {
        return "Place : " + this.getToken();
    }
    
}
