/*Duloc is shaped like a tree graph with n nodes (houses) and n−1 edges (roads).
Shrek wants to buy a new house as far as possible from Donkey’s house,
i.e., they want to find two nodes in the tree that are the farthest apart — this is the tree diameter.
You are to write a non-recursive program to find any one such pair of nodes.*/

package homework_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class dulocFarthestHouses {

    static int[] dfs(ArrayList<ArrayList<Integer>> tree, int start) {
        int n = tree.size() - 1;
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        int farthestNode = start;

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int neighbor : tree.get(current)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                    dist[neighbor] = dist[current] + 1;
                    if (dist[neighbor] > dist[farthestNode]) {
                        farthestNode = neighbor;
                    }
                }
            }
        }

        return new int[]{farthestNode, dist[farthestNode]};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int[] result = dfs(tree, 1);
        int startingNode = result[0];
        result = dfs(tree, startingNode);
        int endingNode = result[0];

        System.out.println(startingNode + " " + endingNode);
        reader.close();
    }
}
