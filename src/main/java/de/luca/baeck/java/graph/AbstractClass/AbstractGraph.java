package de.luca.baeck.java.graph.AbstractClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.Edge.Interface.Edge;
import de.luca.baeck.java.graph.Interface.Graph;

public abstract class AbstractGraph<E> implements Graph<E> {

    protected Map<E, Set<Edge<E>>> edges;

    protected AbstractGraph() {
        edges = new HashMap<>();
    }

    protected AbstractGraph(Map<E, Set<Edge<E>>> edges) {
        edges = new HashMap<>();
        this.edges = edges;
    }

    @Override
    public boolean add(E vertex) {
        if(edges.keySet().contains(vertex)) {
            return false;
        }
        edges.put(vertex, new HashSet<Edge<E>>());
        return true;
    }
    
    @Override
    public boolean contains(E vertex) {
        return edges.keySet().contains(vertex);
    }

    @Override
    public boolean remove(E vertex) {
        return edges.remove(vertex) != null;
    }

    @Override
    public int size() {
        return edges.keySet().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isConnected'");
    }

    @Override
    public boolean isCyclic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCyclic'");
    }

}
