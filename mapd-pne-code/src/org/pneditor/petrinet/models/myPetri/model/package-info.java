/**
 * Elements of a Petri net: places, transitions and arcs.
 *
 * <p>A {@link model.Place} holds a number of tokens. A {@link model.Transition}
 * owns its incoming and outgoing arcs and decides whether it can be stepped.
 * Arcs derive from the abstract class {@link model.Arc}: incoming arcs
 * ({@link model.InArc}, with its variants {@link model.ArcZero} and
 * {@link model.ArcEmpty}) consume tokens from a place, and outgoing arcs
 * ({@link model.OutArc}) produce tokens into a place.
 *
 * <p>Responsibility: represent the state of a Petri net and the firing rules
 * of its elements. Building and modifying a whole net is the job of the
 * {@code network} package.
 */
package model;
