package de.luca.baeck.java.graph.Interface;

import java.util.List;
import java.util.Map;

public interface WeightedGraph<E,T> extends Graph<E> {

    public List<E> getPath(E origin, E target);

    public Map<E, List<E>> getPaths(E origin);

    public void connect(E origin, E target, T weight);
    
}
