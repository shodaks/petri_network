public class Place {

    private int token;
    private String name;

    public Place(String name, int token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Place (" + this.getName() + ") : " + this.getToken();
    }
    
}
