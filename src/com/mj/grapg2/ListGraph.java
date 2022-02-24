package com.mj.grapg2;

import java.util.*;

public class ListGraph<V, W> implements CustomGraph<V, W> {

    private Map<V, Vertex<V>> vertices = new HashMap<>();
    private Set<Edge<V, W>> edges = new HashSet<>();


    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<V>(v));
    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to, W w) {
        Vertex<V> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V> toVertex = vertices.get(to);
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
        Vertex<V> fromVertex = vertices.get(from);
        Vertex<V> toVertex = vertices.get(to);

        if (fromVertex == null || toVertex == null) return;

        Edge<V,W> edge = new Edge<>()
        fromVertex.fromEdges

    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int vertexSize() {
        return 0;
    }

    private static class Vertex<V> {
        V value;
        Set<Edge> fromEdges = new HashSet<>();
        Set<Edge> toEdges = new HashSet<>();

        public Vertex(V v) {
            this.value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?> vertex = (Vertex<?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static class Edge<V,W> {
        private Vertex<V> from;
        private Vertex<V> to;
        private W weight;


        public Edge(Vertex<V> from, Vertex<V> to ,W weight) {
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