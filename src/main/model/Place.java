package model;

/**
 * A place of a Petri net. It has a name and holds a non-negative number of tokens.
 */
public class Place {

    private int token;
    private String name;

    /**
     * Creates a place.
     *
     * @param name  the name of the place
     * @param token the initial number of tokens
     * @throws IllegalArgumentException if token is negative
     */
    public Place(String name, int token) {
        this.name = name;
        if (token < 0) {
            throw new IllegalArgumentException("token must be non-negative");
        }
        this.token = token;
    }

    /**
     * Returns the name of the place.
     *
     * @return the name of the place
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes the name of the place.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of tokens of the place.
     *
     * @return the number of tokens
     */
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

    /**
     * Returns a textual description of the place.
     *
     * @return the description, e.g. "Place (P0) : 2"
     */
    public String toString() {
        return "Place (" + this.getName() + ") : " + this.getToken();
    }
    
}
