package de.luca.baeck.java.graph.AbstractClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import de.luca.baeck.java.graph.Edge.Class.EdgeClass;
import de.luca.baeck.java.graph.Edge.Interface.Edge;
import de.luca.baeck.java.graph.Interface.UnweightedGraph;

public abstract class AbstractUnweightedGraph<E> implements UnweightedGraph<E> {

    protected Map<E, Set<Edge<E>>> edges;
    
    private Map<E, Boolean> visited;
    private Map<E, Boolean> beingVisited;

    protected AbstractUnweightedGraph() {
        edges = new HashMap<>();
    }

    protected AbstractUnweightedGraph(Map<E, Set<Edge<E>>> edges) {
        this.edges = edges;
    }

    protected void link(E origin, E target) {
        Edge<E> newEdge = new EdgeClass<E>(origin, target);
        for(Edge<E> edge : edges.get(origin)) {
            if(edge.equals(newEdge)) {
                return;
            }
        }
        edges.get(origin).add(newEdge);
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

    protected boolean removeEdge(E origin, E target) {
        Set<Edge<E>> edgesFromOrigin = edges.get(origin);
        Edge<E> remove = null;
        for(Edge<E> edge : edgesFromOrigin) {
            if(edge.getTarget().equals(target)){
                remove = edge;
            }
        }
        if(remove == null) {
            return false;
        }
        edgesFromOrigin.remove(remove);
        return true;
    }

    @Override
    public boolean remove(E vertex) {
        for(E vert: edges.keySet()) {
            List<Edge<E>> remove = new ArrayList<>();
            for(Edge<E> edge : edges.get(vert)) {
                if(edge.getTarget().equals(vertex)) {
                    remove.add(edge);
                }
            }
            for(Edge<E> edge : remove) {
                edges.get(vert).remove(edge);
            }
        }
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
        Set<E> visited = new HashSet<>();
        Stack<E> toDo = new Stack<>();
        @SuppressWarnings("unchecked")
        E start = (E) edges.keySet().toArray()[0];
        visited.add(start);
        toDo.push(start);
        while(!toDo.isEmpty()) {
            Set<Edge<E>> edgesFromVertex = edges.get(toDo.pop());
            for (Edge<E> weightedEdge : edgesFromVertex) {
                if(!visited.contains(weightedEdge.getTarget())) {
                    toDo.push(weightedEdge.getTarget());
                    visited.add(weightedEdge.getTarget());
                }
            }
        }
        return visited.size() == edges.keySet().size();
    }

    @Override
    public boolean isCyclic() {
        visited = new HashMap<>();
        beingVisited = new HashMap<>();
        for(E vertex : edges.keySet()) {
            visited.put(vertex, false);
            beingVisited.put(vertex, false);
        }

        for(E vertex : edges.keySet()) {
            if(!visited.get(vertex) && isCyclic(vertex, vertex)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclic(E origin, E previous){
        beingVisited.put(origin, true);
        for(Edge<E> edge : edges.get(origin)) {
            E neighbour = edge.getTarget();
            if(!neighbour.equals(previous)) {
                if (beingVisited.get(neighbour)) {
                    // backward edge exists
                    return true;
                } else if (!visited.get(neighbour) && isCyclic(neighbour, origin)) {
                    return true;
                }
            }
        }
        beingVisited.put(origin, false);
        visited.put(origin, true);
        return false;
    }

    @Override
    public String toString() {
        return edges.toString();
    }

}
