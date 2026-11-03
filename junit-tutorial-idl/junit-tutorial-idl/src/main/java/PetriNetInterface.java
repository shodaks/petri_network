import java.util.Vector;

/**
 * The Interface PetriNetInterface.
 */
public interface PetriNetInterface {
	
	/**
	 * Adds the place.
	 *
	 * @param var1 the var 1
	 * @return the place
	 */
	Place addPlace(int var1);

	/**
	 * Adds the place.
	 *
	 * @param var1 the var 1
	 * @param var2 the var 2
	 * @return the place
	 */
	Place addPlace(String var1, int var2);

	//boolean removePlace(Place var1);

	/**
	 * Adds the transition.
	 *
	 * @return the transition
	 */
	Transition addTransition();

	/**
	 * Adds the transition.
	 *
	 * @param var1 the var 1
	 * @return the transition
	 */
	Transition addTransition(String var1);

	//boolean removeTransition(Transition var1);

	/**
	 * Adds the place 2 transition arc.
	 *
	 * @param var1 the var 1
	 * @param var2 the var 2
	 * @param var3 the var 3
	 */
	void addPlace2TransitionArc(Place var1, Transition var2, int var3);

	/**
	 * Adds the transition 2 place arc.
	 *
	 * @param var1 the var 1
	 * @param var2 the var 2
	 * @param var3 the var 3
	 */
	void addTransition2PlaceArc(Transition var1, Place var2, int var3);

	/**
	 * Trigger.
	 *
	 * @param var1 the var 1
	 */
	void trigger(Transition var1);
	
	/**
	 * Simulate.
	 *
	 * @param cfg the cfg
	 */
	void simulate(Vector<Integer> cfg);
	
}