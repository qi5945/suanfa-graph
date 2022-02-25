package com.mj.grapg2;

import java.util.*;

public class ListGraph<V, W> implements CustomGraph<V, W> {

    private Map<V, Vertex<V,W>> vertices = new HashMap<>();
    private Set<Edge<V, W>> edges = new HashSet<>();


    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex(v));
    }

    @Override
    public void removeVertex(V v) {

        Vertex vertex = vertices.get(v);
        if (vertex == null) return;

        Iterator<Edge> fromIterator = vertex.fromEdges.iterator();
        for (;fromIterator.hasNext();) {
            Edge<V,W> edge = fromIterator.next();
            fromIterator.remove();
            edge.to.toEdges.remove(edge);
            edges.remove(edge);
        }

        Iterator<Edge> toIterator = vertex.toEdges.iterator();
        for (;toIterator.hasNext();) {
            Edge<V,W> edge = toIterator.next();
            edge.from.toEdges.remove(edge);
            toIterator.remove();
            edges.remove(edge);

        }


    }

    @Override
    public void addEdge(V from, V to, W w) {
        Vertex<V,W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V,W> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }


        //看一下以前是否有边
        Edge<V, W> edge = new Edge(fromVertex,toVertex,w);
        if (fromVertex.toEdges.contains(edge)) {
            fromVertex.toEdges.remove(edge);
            toVertex.fromEdges.remove(edge);
        }

        fromVertex.toEdges.add(edge);
        toVertex.fromEdges.add(edge);


        edges.remove(edge);
        edges.add(edge);
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V,W> fromVertex = vertices.get(from);
        Vertex<V,W> toVertex = vertices.get(to);

        if (fromVertex == null || toVertex == null) return;

        Edge<V,W> edge = new Edge(fromVertex,toVertex,1);

        if (!edges.contains(edge)) {return;}

        edges.remove(edge);
        fromVertex.fromEdges.remove(edge);
        toVertex.toEdges.remove(edge);


    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    private static class Vertex<V,W> {
        V value;
        Set<Edge<V,W>> fromEdges = new HashSet<>();
        Set<Edge<V,W>> toEdges = new HashSet<>();

        public Vertex(V v) {
            this.value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<V,W> vertex = (Vertex<V,W>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static class Edge<V,W> {
        private Vertex<V,W> from;
        private Vertex<V,W> to;
        private W weight;


        public Edge(Vertex<V,W> from, Vertex<V,W> to ,W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}