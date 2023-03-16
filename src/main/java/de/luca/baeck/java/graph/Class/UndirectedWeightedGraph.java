package de.luca.baeck.java.graph.Class;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.luca.baeck.java.graph.AbstractClass.AbstractWeightedGraph;
import de.luca.baeck.java.graph.Edge.Class.WeightedEdgeClass;
import de.luca.baeck.java.graph.Edge.Interface.WeightedEdge;
import de.luca.baeck.java.graph.Interface.Graph;

public class UndirectedWeightedGraph<E,T> extends AbstractWeightedGraph<E,T> {

    UndirectedWeightedGraph() {
        super();
    }

    UndirectedWeightedGraph(Map<E, Set<WeightedEdge<E,T>>> edges) {
        super(edges);
    }

    @Override
    public void connect(E origin, E target, T weight) {
        WeightedEdge<E,T> newEdge = new WeightedEdgeClass<E,T>(target, weight);
        for(WeightedEdge<E,T> edge : edges.get(origin)) {
            if(edge.equals(newEdge)) {
                return;
            }
        }
        edges.get(origin).add(newEdge);
        newEdge = new WeightedEdgeClass<E,T>(origin, weight);
        edges.get(target).add(newEdge);
    }

    @Override
    public Graph<E> subgraph(List<E> vertices) {
        Map<E, Set<WeightedEdge<E,T>>> edgesSub = new HashMap<>();
        for(E vertex : edges.keySet()) {
            if(vertices.contains(vertex)) {
                for(WeightedEdge<E,T> edge : edges.get(vertex)) {
                    if(!vertices.contains(edge.getTarget())) {
                        edges.get(vertex).remove(edge);
                    }
                }
                edgesSub.put(vertex, edges.get(vertex));
            }
        }
        return new UndirectedWeightedGraph<E,T>(edgesSub);
    }

}
