package com.mj.graph;

import java.util.*;

public class ListGraph2<V, E> implements Graph2<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    public void print() {
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
        });

        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex(v));

    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //判断
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, weight);

        fromVertex.outEdges.remove(edge);
        fromVertex.outEdges.add(edge);

        toVertex.inEdges.remove(edge);
        toVertex.inEdges.add(edge);

        edges.remove(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        if (vertices.containsKey(v)) {
            Vertex<V, E> vertex = vertices.get(v);

            for (Edge<V, E> outEdge : vertex.outEdges) {
                outEdge.to.inEdges.remove(outEdge);
                edges.remove(outEdge);
            }

            for (Edge<V, E> inEdge : vertex.inEdges) {
                inEdge.from.outEdges.remove(inEdge);
                edges.remove(inEdge);
            }
            vertices.remove(v);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        Vertex<V, E> roVertex = vertices.get(to);

        if (fromVertex == null || roVertex == null) {
            return;
        }

        Edge<V, E> edge = new Edge<>(fromVertex, roVertex, null);

        if (edges.contains(edge)) {
            edges.remove(edge);
            edge.from.outEdges.remove(edge);
            edge.to.inEdges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin) {
        Vertex beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            System.out.println(vertex.value);

            for (Edge<V, E> outEdge : vertex.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                queue.offer(outEdge.to);
                visitedVertices.add(outEdge.to);
            }
        }
    }

    @Override
    public void dfs(V begin) {
        Vertex beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex> visitedVertices = new HashSet();
        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(beginVertex);
        System.out.println(beginVertex.value);
        visitedVertices.add(beginVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> outEdge : vertex.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                stack.push(outEdge.from);
                stack.push(outEdge.to);
                visitedVertices.add(outEdge.to);
                System.out.println(outEdge.to.value);
            }
        }
//        dfs(beginVertex,visitedVertices);

    }

    @Override
    public List<V> tppologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        Map<Vertex<V, E>, Integer> ins = new HashMap<>();
        vertices.forEach((V v, Vertex vertex) -> {
            if (vertex.inEdges.size() == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, vertex.inEdges.size());
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            //放入返回结果
            list.add(vertex.value);

            for (Edge<V, E> outEdge : vertex.outEdges) {
                int toIn = ins.get(outEdge.to) - 1;
                if (toIn == 0) {
                    queue.offer(outEdge.to);
                } else {
                    ins.put(outEdge.to, toIn);
                }
            }


        }

        return list;
    }

    //递归调用
//    private void dfs(Vertex<V, E> vertex, Set<Vertex> visitedVertices) {
//        System.out.println(vertex.value);
//        visitedVertices.add(vertex);
//        for (Edge<V, E> outEdge : vertex.outEdges) {
//            if (visitedVertices.contains(outEdge.to)) break;
//            dfs(outEdge.to, visitedVertices);
//        }
//    }


    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V v) {
            value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
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

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
