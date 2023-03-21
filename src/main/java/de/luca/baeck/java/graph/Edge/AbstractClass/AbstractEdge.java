package de.luca.baeck.java.graph.Edge.AbstractClass;

import de.luca.baeck.java.graph.Edge.Interface.Edge;

public abstract class AbstractEdge<E> implements Edge<E> {
    
    protected E origin;
    protected E target;

    protected AbstractEdge(E origin, E target) {
        this.origin = origin;
        this.target = target;
    }

    @Override
    public E getOrigin() {
        return origin;
    }

    @Override
    public E getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "(Origin: " + origin + ", Target: "  + target + ")";
    }

}
