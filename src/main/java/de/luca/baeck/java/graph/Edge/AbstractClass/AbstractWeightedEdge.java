package de.luca.baeck.java.graph.Edge.AbstractClass;

import de.luca.baeck.java.graph.Edge.Interface.WeightedEdge;

public abstract class AbstractWeightedEdge<E,T> extends AbstractEdge<E> implements WeightedEdge<E,T> {

    protected T weight;

    protected AbstractWeightedEdge(E target, T weight) {
        super(target);
        this.weight = weight;
    }
    
    @Override
    public T getWeight() {
        return weight;
    }

}
