package de.luca.baeck.java.graph.Edge.Class;

import de.luca.baeck.java.graph.Edge.AbstractClass.AbstractEdge;

public class EdgeClass<E> extends AbstractEdge<E> {

    public EdgeClass(E target) {
        super(target);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof EdgeClass<?>) {
            @SuppressWarnings("unchecked")
            EdgeClass<E> edge = (EdgeClass<E>)obj;
            return this.target.equals(edge.getTarget());
        }
        return false;
    }

}
