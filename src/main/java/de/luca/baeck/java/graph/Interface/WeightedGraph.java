package de.luca.baeck.java.graph.Interface;

import java.util.List;
import java.util.Map;

public interface WeightedGraph<E,T> extends Graph<E> {

    public void connect(E origin, E target, T weight);

    public boolean remove(E origin, E target, T weight);

    public List<E> getPath(E origin, E target);

    public Map<E, List<E>> getPaths(E origin);

    public WeightedGraph<E,T> minimalSpanningTree();
    
}
