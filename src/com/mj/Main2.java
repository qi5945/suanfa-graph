package com.mj;
import com.mj.graph.Graph;
import com.mj.graph.Graph2;
import com.mj.graph.ListGraph;
import com.mj.graph.ListGraph2;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {

//    	testbfs();
//		testdfs();

		testtopoSort();
//		ListGraph2<String, Integer> graph = new ListGraph2<>();
//		graph.addEdge("V0", "V1");
//		graph.addEdge("V1", "V0");
//
//		graph.addEdge("V0", "V2");
//		graph.addEdge("V2", "V0");
//
//		graph.addEdge("V0", "V3");
//		graph.addEdge("V3", "V0");
//
//		graph.addEdge("V1", "V2");
//		graph.addEdge("V2", "V1");
//
//		graph.addEdge("V2", "V3");
//		graph.addEdge("V3", "V2");
//
//		graph.print();

//        ListGraph2<String,Integer> graph = new ListGraph2<>();
//        graph.addEdge("V1", "V0", 9);
//		graph.addEdge("V1", "V2", 3);
//		graph.addEdge("V2", "V0", 2);
//		graph.addEdge("V2", "V3", 5);
//		graph.addEdge("V3", "V4", 1);
//		graph.addEdge("V0", "V4", 6);
//		graph.print();
//		graph.bfs("V1");
//
//		graph.removeVertex("V2");
//		graph.removeVertex("V4");
//		graph.print();
    }

	private static void testtopoSort() {
		Graph2 listGraph2 = directedGraph(Data.TOPO);
		List<Integer> list =  listGraph2.tppologicalSort();
		for (Integer integer : list) {
			System.out.println(integer);
		}

	}

	private static void testbfs() {
		Graph2<Object, Double> graph = directedGraph(Data.DFS_02);
		graph.bfs(0);
	}

	private static void testdfs() {
		Graph2<Object, Double> graph = directedGraph(Data.DFS_02);
		graph.dfs("a");
	}



	/**
	 * 有向图
	 */
	private static Graph2<Object, Double> directedGraph(Object[][] data) {
		Graph2<Object, Double> graph = new ListGraph2();
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
			}
		}
		return graph;
	}

	/**
	 * 无向图
	 * @param data
	 * @return
	 */
	private static Graph2<Object, Double> undirectedGraph(Object[][] data) {
		Graph2<Object, Double> graph = new ListGraph2<>();
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
				graph.addEdge(edge[1], edge[0]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
				graph.addEdge(edge[1], edge[0], weight);
			}
		}
		return graph;
	}


}
