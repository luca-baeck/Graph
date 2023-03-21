package de.luca.baeck.java.graph.Class;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.AbstractClass.AbstractUnweightedGraph;
import de.luca.baeck.java.graph.Edge.Interface.Edge;
import de.luca.baeck.java.graph.Interface.Graph;

public class UnweightedUndirectedGraph<E> extends AbstractUnweightedGraph<E> {

    public UnweightedUndirectedGraph() {
        super();
    }

    public UnweightedUndirectedGraph(Map<E, Set<Edge<E>>> edges) {
        super(edges);
    }

    @Override
    public void connect(E origin, E target) {
        link(origin, target);
        link(target, origin);
    }

    @Override
    public boolean remove(E origin, E target) {
        removeEdge(target, origin);
        return removeEdge(origin, target);
    }

    @Override
    public Graph<E> subgraph(List<E> vertices) {
        Map<E, Set<Edge<E>>> edgesSub = new HashMap<>();
        for(E vertex : edges.keySet()) {
            if(vertices.contains(vertex)) {
                Set<Edge<E>> newEdgesFromVertex = new HashSet<>();
                for(Edge<E> edge : edges.get(vertex)) {
                    if(vertices.contains(edge.getTarget())) {
                        newEdgesFromVertex.add(edge);
                    }
                }
                edgesSub.put(vertex, newEdgesFromVertex);
            }
        }
        return new UnweightedUndirectedGraph<E>(edgesSub);
    }
    
}
