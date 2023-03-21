package de.luca.baeck.java.graph.Edge.AbstractClass;

import de.luca.baeck.java.graph.Edge.Interface.WeightedEdge;

public abstract class AbstractWeightedEdge<E,T> extends AbstractEdge<E> implements WeightedEdge<E,T> {

    protected T weight;

    protected AbstractWeightedEdge(E origin, E target, T weight) {
        super(origin, target);
        this.weight = weight;
    }
    
    @Override
    public T getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(Origin: " + origin + ", Target: " + target + ", Weight: " + weight + ")";
    }

}
