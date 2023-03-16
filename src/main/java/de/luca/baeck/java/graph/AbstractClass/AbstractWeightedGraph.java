package de.luca.baeck.java.graph.AbstractClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.Edge.Interface.WeightedEdge;
import de.luca.baeck.java.graph.Interface.WeightedGraph;

public abstract class AbstractWeightedGraph<E,T> extends AbstractGraph<E> implements WeightedGraph<E,T> {

    protected Map<E, Set<WeightedEdge<E,T>>> edges;

    protected AbstractWeightedGraph() {
        edges = new HashMap<>();
    }

    protected AbstractWeightedGraph(Map<E, Set<WeightedEdge<E,T>>> edges) {
        edges = new HashMap<>();
        this.edges = edges;
    }

    @Override
    public void connect(E origin, E target) {
        return;
    }

    @Override
    public List<E> getPath(E origin, E target) {
        return getPaths(origin).get(target);
    }

    @Override
    public Map<E, List<E>> getPaths(E origin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaths'");
    }
    
}
