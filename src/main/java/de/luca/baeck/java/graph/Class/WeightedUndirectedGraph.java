package de.luca.baeck.java.graph.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.AbstractClass.AbstractWeightedGraph;
import de.luca.baeck.java.graph.Edge.Interface.WeightedEdge;
import de.luca.baeck.java.graph.Interface.Graph;
import de.luca.baeck.java.graph.Interface.WeightedGraph;

public class WeightedUndirectedGraph<E,T> extends AbstractWeightedGraph<E,T> {

    public WeightedUndirectedGraph() {
        super();
    }

    public WeightedUndirectedGraph(Map<E, Set<WeightedEdge<E,T>>> edges) {
        super(edges);
    }

    @Override
    public void connect(E origin, E target, T weight) {
        link(origin, target, weight);
        link(target, origin, weight);
    }

    @Override
    public boolean remove(E origin, E target, T weight) {
        removeEdge(target, origin, weight);
        return removeEdge(origin, target, weight);
    }

    @Override
    public Graph<E> subgraph(List<E> vertices) {
        Map<E, Set<WeightedEdge<E,T>>> edgesSub = new HashMap<>();
        for(E vertex : edges.keySet()) {
            if(vertices.contains(vertex)) {
                Set<WeightedEdge<E,T>> newEdgesFromVertex = new HashSet<>();
                for(WeightedEdge<E,T> edge : edges.get(vertex)) {
                    if(vertices.contains(edge.getTarget())) {
                        newEdgesFromVertex.add(edge);
                    }
                }
                edgesSub.put(vertex, newEdgesFromVertex);
            }
        }
        return new WeightedUndirectedGraph<E,T>(edgesSub);
    }

    @Override
    public WeightedGraph<E,T> minimalSpanningTree() {
        T weight = null;
        outer: for(E vertex : edges.keySet()) {
            for(WeightedEdge<E,T> edge : edges.get(vertex)){
                weight = edge.getWeight();
                break outer;
            }
        }
        if(!(weight instanceof java.lang.Number)) {
            return this;
        }
        List<WeightedEdge<E,T>> allEdges = new ArrayList<>();
        for(E vertex : edges.keySet()) {
            for(WeightedEdge<E,T> edge : edges.get(vertex)) {
                allEdges.add(edge);
            }
        }
        for (int i = allEdges.size(); i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
              if (((java.lang.Number)allEdges.get(j).getWeight()).doubleValue() > ((java.lang.Number)allEdges.get(j+1).getWeight()).doubleValue()) {
                WeightedEdge<E,T> x = allEdges.get(j);
                allEdges.set(j, allEdges.get(j+1));
                allEdges.set(j+1, x);
              }
            }
        }
        WeightedUndirectedGraph<E,T> result = new WeightedUndirectedGraph<>();
        for(E vertex : edges.keySet()) {
            result.add(vertex);
        }
        for(int i = 0; i < allEdges.size(); i++) {
            WeightedEdge<E,T> currentEdge = allEdges.get(i);
            result.connect(currentEdge.getOrigin(), currentEdge.getTarget(), currentEdge.getWeight());
            if(result.isCyclic()) {
                result.remove(currentEdge.getOrigin(), currentEdge.getTarget(), currentEdge.getWeight());
            }
        }
        return result;
    }

}
