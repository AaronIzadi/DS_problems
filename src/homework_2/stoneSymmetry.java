/*Given coordinates of stones on a grid (up to 20,000 stones), determine where to add stones to make the arrangement symmetric according to specific rules:
For every pair of stones at
(x_1, y_1)  and (x_2, y_2), either:
x_1 = x_2 (same column), or
y_1 = y_2 (same row), or
The point (x_1, y_2) has a stone.
You must add the minimum number of stones at integer coordinates to satisfy this.*/

package homework_2;

import java.io.*;
import java.util.*;

public class stoneSymmetry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<String> stones = new HashSet<>();
        int[] xs = new int[n];
        int[] ys = new int[n];

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            xs[i] = Integer.parseInt(parts[0]);
            ys[i] = Integer.parseInt(parts[1]);
            stones.add(xs[i] + "," + ys[i]);
        }

        Set<String> toAdd = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = xs[i], y1 = ys[i];
                int x2 = xs[j], y2 = ys[j];

                if (x1 != x2 && y1 != y2) {
                    String corner1 = x1 + "," + y2;
                    String corner2 = x2 + "," + y1;

                    if (!stones.contains(corner1)) {
                        toAdd.add(corner1);
                    }
                    if (!stones.contains(corner2)) {
                        toAdd.add(corner2);
                    }
                }
            }
        }
        System.out.println(toAdd.size());
        for (String s : toAdd) {
            System.out.println(s.replace(",", " "));
        }
    }
}
