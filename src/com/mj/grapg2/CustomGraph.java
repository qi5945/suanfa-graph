package com.mj.grapg2;

public interface CustomGraph<V, W> {

    void addVertex(V v);

    void removeVertex(V v);

    void addEdge(V from, V to, W w);

    void removeEdge(V from, V to);

    int edgeSize();

    int vertexSize();

}
