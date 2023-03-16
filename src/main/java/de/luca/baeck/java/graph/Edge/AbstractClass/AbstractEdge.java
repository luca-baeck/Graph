package de.luca.baeck.java.graph.Edge.AbstractClass;

import de.luca.baeck.java.graph.Edge.Interface.Edge;

public abstract class AbstractEdge<E> implements Edge<E> {
    
    protected E target;

    protected AbstractEdge(E target) {
        this.target = target;
    }

    @Override
    public E getTarget() {
        return target;
    }

}
