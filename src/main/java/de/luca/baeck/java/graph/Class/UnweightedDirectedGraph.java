package de.luca.baeck.java.graph.Class;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.AbstractClass.AbstractUnweightedGraph;
import de.luca.baeck.java.graph.Edge.Interface.Edge;
import de.luca.baeck.java.graph.Interface.Graph;

public class UnweightedDirectedGraph<E> extends AbstractUnweightedGraph<E> {

    public UnweightedDirectedGraph() {
        super();
    }

    public UnweightedDirectedGraph(Map<E, Set<Edge<E>>> edges) {
        super(edges);
    }

    @Override
    public void connect(E origin, E target) {
        link(origin, target);
    }

    @Override
    public boolean remove(E origin, E target) {
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
        return new UnweightedDirectedGraph<E>(edgesSub);
    }

}
