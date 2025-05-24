/*Duloc is described as a sequence of hills with different heights. After magical slime rain, the valleys between hills fill with slime, forming swampy areas.
Shrek wants to find the largest rectangle (by area) that fits entirely inside one of these swamp regions — meaning, in the histogram formed by the sequence of hill heights, he wants the largest area rectangle under the histogram.*/

package homework_3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class largestRectangleInHistogram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextLong();
        }
        long[] left = new long[n];
        long[] right = new long[n];
        left[0] = heights[0];
        right[n - 1] = heights[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], heights[i]);
        }
        for (int i = 2; i < n; i++) {
            right[n - i] = Math.max(right[n - i + 1], heights[n - i]);
        }
        long[] mud = new long[n];
        for (int i = 0; i < n; i++) {
            mud[i] = Math.max(0, Math.min(left[i], right[i]) - heights[i]);
        }
        Arrays.fill(left, 0);
        Arrays.fill(right, 0);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && mud[stack.peek()] >= mud[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && mud[stack.peek()] >= mud[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
            stack.push(i);
        }
        long maxRec = 0;
        for (int i = 0; i < n; i++) {
            long width = right[i] - left[i] + 1;
            long height = mud[i] * width;
            maxRec = Math.max(maxRec, height);
        }
        System.out.println(maxRec);
    }
}
