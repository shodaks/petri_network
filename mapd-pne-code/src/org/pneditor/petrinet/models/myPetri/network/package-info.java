/**
 * The Petri net as a whole and the service used to build and modify it.
 *
 * <p>{@link network.PetriLatticeInterface} defines the operations available to
 * a user: adding and removing places, transitions and arcs.
 * {@link network.PetriLattice} implements it and keeps the lists of places,
 * transitions and arcs of the net.
 *
 * <p>Responsibility: create, link and remove the elements of the {@code model}
 * package while enforcing the rules of the net (for example, only one arc in
 * the same direction between a place and a transition).
 */
package network;
