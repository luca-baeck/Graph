package de.luca.baeck.java.graph.Interface;

public interface UnweightedGraph<E> extends Graph<E> {
    
    public void connect(E origin, E target);

    public boolean remove(E origin, E target);

}
