package de.luca.baeck.java.graph.Interface;

import java.util.List;

public interface Graph<E> {
    
    public boolean add(E vertex);

    public void connect(E origin, E target);

    public boolean contains(E vertex);

    public boolean remove(E vertex);

    public int size();

    public boolean isEmpty();

    public Graph<E> subgraph(List<E> vertices);

    public boolean isConnected();

    public boolean isCyclic();

}
