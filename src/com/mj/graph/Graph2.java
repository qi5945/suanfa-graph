package com.mj.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Graph2<V, E> {
    int edgesSize();

    int verticesSize();

    void addVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVertex(V v);

    void removeEdge(V from, V to);

    void bfs(V begin);

    void dfs(V begin);

    List<V> tppologicalSort();

}
