package de.luca.baeck.java.graph.Class;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.AbstractClass.AbstractGraph;
import de.luca.baeck.java.graph.Edge.Class.EdgeClass;
import de.luca.baeck.java.graph.Edge.Interface.Edge;
import de.luca.baeck.java.graph.Interface.Graph;

public class UndirectedUnweightedGraph<E> extends AbstractGraph<E> {

    UndirectedUnweightedGraph() {
        super();
    }

    UndirectedUnweightedGraph(Map<E, Set<Edge<E>>> edges) {
        super(edges);
    }

    @Override
    public void connect(E origin, E target) {
        Edge<E> newEdge = new EdgeClass<E>(target);
        for(Edge<E> edge : edges.get(origin)) {
            if(edge.equals(newEdge)) {
                return;
            }
        }
        edges.get(origin).add(newEdge);
        newEdge = new EdgeClass<E>(origin);
        edges.get(target).add(newEdge);
    }

    @Override
    public Graph<E> subgraph(List<E> vertices) {
        Map<E, Set<Edge<E>>> edgesSub = new HashMap<>();
        for(E vertex : edges.keySet()) {
            if(vertices.contains(vertex)) {
                for(Edge<E> edge : edges.get(vertex)) {
                    if(!vertices.contains(edge.getTarget())) {
                        edges.get(vertex).remove(edge);
                    }
                }
                edgesSub.put(vertex, edges.get(vertex));
            }
        }
        return new UndirectedUnweightedGraph<E>(edgesSub);
    }
    
}
