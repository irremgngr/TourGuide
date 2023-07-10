//-----------------------------------------------------
// Title: Graph class
// Author: Irem Güngör
// ID: 61375493682
// Section: 3
// Assignment: 1
// Description: This class includes the graph. Makes the connections
// between given nodes in the main class and search the route between
// given starting point and ending point.
// -----------------------------------------------------

import java.util.Collections;
import java.util.LinkedList;

public class Graph1 {
    private int cityNumber;
    private LinkedList<Integer> adj[];
    private int c;
    private LinkedList<Integer> islands;

//
    public Graph1(int cityNumber) {
        this.cityNumber = cityNumber;
        adj = new LinkedList[cityNumber + 1];
        for (int i = 0; i < cityNumber; ++i)
            adj[i] = new LinkedList();
        c = 1;
        islands = new LinkedList<Integer>();
    }

    public void addEdges(int a, int b) {
        //--------------------------------------------------------
        // Summary: Assigns the edges between given nodes.
        // Precondition: a and b are integer.
        // Postcondition: The edges between the nodes are set.
        // --------------------------------------------------------
        adj[a].add(b);
        adj[b].add(a);
    }

    public void deleteEdge(int count, int beforeLast) {
        //--------------------------------------------------------
        // Summary: Deletes the edges between visited nodes before.
        // Precondition: count and beforeLast are integer.
        // Postcondition: The edges between the nodes are  deleted.
        // --------------------------------------------------------

        for (int i = 0; i < adj[count].size(); i++) {
            if (adj[count].get(i) == beforeLast) {
                adj[count].remove(i);
                break;
            }
        }
        for (int i = 0; i < adj[beforeLast].size(); i++) {
            if (adj[beforeLast].get(i) == count) {
                adj[beforeLast].remove(i);
                break;
            }
        }
    }

    public void BFS(int s, int e) {
        //--------------------------------------------------------
        // Summary: Finds the route between starting and ending point
        // and stores it in a LinkedList.
        // Precondition: s and e are integer.
        // Postcondition: The route has been set from starting point
        // to ending point.
        // --------------------------------------------------------

        boolean visited[] = new boolean[cityNumber + 1];

        int path1[] = new int[cityNumber + 1];

        LinkedList<Integer> queue = new LinkedList<Integer>();
        LinkedList<Integer> pathCount = new LinkedList<Integer>();

        // starts with the s node and finds the route to e node.
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            LinkedList<Integer> list = adj[s];
            Collections.sort(list);

            for (int i = 0; i < list.size(); i++) {
                int v = list.get(i);
                if (v == e) {
                    path1[v] = s;
                    queue.clear();
                    break;
                }
                if (visited[v] != true) {
                    visited[v] = true;
                    queue.add(v);
                    path1[v] = s;
                }
            }
        }

        pathCount.add(e);

        int z = e;
        while (z != 0) {
            int k = path1[z];
            if (k != 0)
                pathCount.add(k);
            z = k;
        }

        for (int i = pathCount.size() - 1; i >= 0; i--) {
            islands.add(pathCount.get(i));
        }

        c++;
        if (c < 3) {
        for(int i=islands.size()-1;i>0;i--){
            // c controls the
                //if the edges between visited nodes are deleted, then when the
                //method is called again it not sees the same nodes (islands) again.
                deleteEdge(pathCount.get(i), pathCount.get(i-1));
            }
        }

    }

   public void printIslands() {
       //--------------------------------------------------------
       // Summary: Prints the road.
       // Precondition: The islands in the route.
       // Postcondition: The route is given as output.
       // --------------------------------------------------------

       Collections.sort(islands);
       for(int i = 0; i < islands.size(); i++) {
           if (i==islands.size()-1) {
               System.out.print(islands.get(i));
               break;
           }
           if (islands.get(i) == islands.get(i+1))
               islands.remove(islands.get(i+1));
           System.out.print(islands.get(i)+" ");
       }
    }
}