package de.luca.baeck.java.graph.Edge.Class;

import de.luca.baeck.java.graph.Edge.AbstractClass.AbstractWeightedEdge;

public class WeightedEdgeClass<E,T> extends AbstractWeightedEdge<E,T> {

    public WeightedEdgeClass(E origin, E target, T weight) {
        super(origin, target, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof WeightedEdgeClass<?,?>) {
            @SuppressWarnings("unchecked")
            WeightedEdgeClass<E,T> edge = (WeightedEdgeClass<E,T>)obj;
            return (this.target.equals(edge.getTarget()) && this.weight.equals(edge.getWeight()));
        }
        return false;
    }
    
}
