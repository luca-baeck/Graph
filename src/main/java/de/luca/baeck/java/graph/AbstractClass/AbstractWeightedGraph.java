package de.luca.baeck.java.graph.AbstractClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import de.luca.baeck.java.graph.Edge.Class.WeightedEdgeClass;
import de.luca.baeck.java.graph.Edge.Interface.WeightedEdge;
import de.luca.baeck.java.graph.Interface.WeightedGraph;

public abstract class AbstractWeightedGraph<E,T> implements WeightedGraph<E,T> {

    protected Map<E, Set<WeightedEdge<E,T>>> edges;

    private Map<E, Boolean> visited;
    private Map<E, Boolean> beingVisited;

    protected AbstractWeightedGraph() {
        edges = new HashMap<E, Set<WeightedEdge<E,T>>>();
    }

    protected AbstractWeightedGraph(Map<E, Set<WeightedEdge<E,T>>> edges) {
        this.edges = edges;
    }

    protected void link(E origin, E target, T weight) {
        WeightedEdge<E,T> newEdge = new WeightedEdgeClass<E,T>(origin, target, weight);
        for(WeightedEdge<E,T> edge : edges.get(origin)) {
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
        edges.put(vertex, new HashSet<WeightedEdge<E,T>>());
        return true;
    }
    
    @Override
    public boolean contains(E vertex) {
        return edges.keySet().contains(vertex);
    }

    protected boolean removeEdge(E origin, E target, T weight) {
        Set<WeightedEdge<E,T>> edgesFromOrigin = edges.get(origin);
        WeightedEdge<E,T> remove = null;
        for(WeightedEdge<E,T> edge : edgesFromOrigin) {
            if(edge.getTarget().equals(target) && edge.getWeight().equals(weight)){
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
            List<WeightedEdge<E,T>> remove = new ArrayList<>();
            for(WeightedEdge<E,T> edge : edges.get(vert)) {
                if(edge.getTarget().equals(vertex)) {
                    remove.add(edge);
                }
            }
            for(WeightedEdge<E,T> edge : remove) {
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
            Set<WeightedEdge<E,T>> edgesFromVertex = edges.get(toDo.pop());
            for (WeightedEdge<E,T> weightedEdge : edgesFromVertex) {
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
        for(WeightedEdge<E,T> edge : edges.get(origin)) {
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
    public List<E> getPath(E origin, E target) {
        T weight = null;
        outer: for(E vertex : edges.keySet()) {
            for(WeightedEdge<E,T> edge : edges.get(vertex)){
                weight = edge.getWeight();
                break outer;
            }
        }
        if(!(weight instanceof java.lang.Number)) {
            return new ArrayList<E>();
        }
        return getPaths(origin).get(target);
    }

    @Override
    public Map<E, List<E>> getPaths(E origin) {
        T weight = null;
        outer: for(E vertex : edges.keySet()) {
            for(WeightedEdge<E,T> edge : edges.get(vertex)){
                weight = edge.getWeight();
                break outer;
            }
        }
        if(!(weight instanceof java.lang.Number)) {
            return new HashMap<E, List<E>>();
        }
        
        //with dijkstra algorithm
        Map<E, Double> distances = new HashMap<>();
        Map<E, E> previous = new HashMap<>();
        Set<E> visited = new HashSet<>();

        List<E> PQueue = new ArrayList<>();

        for(E vertex : edges.keySet()) {
            distances.put(vertex, Double.MAX_VALUE-1);
            previous.put(vertex, null);
        }
        distances.put(origin, 0.0);
        visited.add(origin);
        PQueue.add(origin);


        while(!PQueue.isEmpty()) {
            Double shortestDistance = Double.MAX_VALUE;
            E shortestVertex = null;
            for(E vertex : PQueue) {
                if(distances.get(vertex) < shortestDistance) {
                    shortestDistance = distances.get(vertex);
                    shortestVertex = vertex;
                }
            }
            visited.add(shortestVertex);
            PQueue.remove(shortestVertex);

            Set<WeightedEdge<E,T>> edgesFromVertex = edges.get(shortestVertex);
            for(WeightedEdge<E,T> edge : edgesFromVertex) {
                if(!visited.contains(edge.getTarget())) {
                    Double tempDistance = Double.valueOf(distances.get(shortestVertex)) + ((java.lang.Number)edge.getWeight()).doubleValue();
                    if(tempDistance < distances.get(edge.getTarget())) {
                        distances.put(edge.getTarget(), tempDistance);
                        previous.put(edge.getTarget(), shortestVertex);
                        PQueue.add(edge.getTarget());
                    }
                }
            }
        }

        Map<E, List<E>> results = new HashMap<>();
        for(E vertex : edges.keySet()) {
            List<E> path = new ArrayList<>();
            E currentVertex = vertex;
            while(currentVertex != null) {
                path.add(0, currentVertex);
                currentVertex = previous.get(currentVertex);
            }
            if(path.size() <= 1) {
                path = null;
            }
            results.put(vertex, path);
        }
        return results;
    }

    @Override
    public String toString() {
        return edges.toString();
    }
    
}
