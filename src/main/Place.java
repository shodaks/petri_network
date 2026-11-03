public class Place {

    private int token;
    private String name;

    public Place(String name, int token) {
        this.name = name;
        if (token < 0) {
            throw new IllegalArgumentException("token must be non-negative");
        }
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

    /**
     * Adds a specified number of tokens to the place.
     *
     * @param incrementToken the number of tokens to add
     * @throws IllegalArgumentException if incrementToken is negative
     */
    public void addToken(int incrementToken) {
        if (incrementToken < 0) {
            throw new IllegalArgumentException("incrementToken must be non-negative");
        }
        this.token += incrementToken;
    }

    /**
     * Removes a specified number of tokens from the place.
     *
     * @param incrementToken the number of tokens to remove
     * @throws IllegalArgumentException if incrementToken is negative or if there are not enough tokens to remove
     */
    public void removeToken(int incrementToken) {
        if (incrementToken < 0) {
            throw new IllegalArgumentException("incrementToken must be non-negative");
        }
        if (this.token < incrementToken) {
            throw new IllegalArgumentException("Not enough tokens to remove");
        }
        this.token -= incrementToken;
    }

    public String toString() {
        return "Place (" + this.getName() + ") : " + this.getToken();
    }
    
}
