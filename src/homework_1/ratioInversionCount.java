/*Shrek gives Donkey an array "a" of length n.
He must count the number of index pairs (i, j) such that i < j and a[i] > 19 * a[j].*/

package homework_1;

import java.util.Scanner;

public class ratioInversionCount {

    static class PairCountResult {
        int count;
        int[] sortedArray;

        PairCountResult(int count, int[] sortedArray) {
            this.count = count;
            this.sortedArray = sortedArray;
        }
    }

    private static PairCountResult countPairs(int[] arr, int start, int end) {
        int length = end - start;

        if (length <= 1) {
            int[] newArr = new int[length];
            for (int i = start; i < end; i++) {
                newArr[i - start] = arr[i];
            }
            return new PairCountResult(0, newArr);
        }

        int mid = start + length / 2;
        PairCountResult left = countPairs(arr, start, mid);
        PairCountResult right = countPairs(arr, mid, end);
        PairCountResult merged = mergeAndCount(left.sortedArray, right.sortedArray);

        return new PairCountResult(left.count + right.count + merged.count, merged.sortedArray);
    }

    private static PairCountResult mergeAndCount(int[] left, int[] right) {
        int count = 0;
        int i = 0, j = 0, k = 0;
        int[] merged = new int[left.length + right.length];

        for (int li = 0; li < left.length; li++) {
            while (j < right.length && left[li] > 19L * right[j]) {
                j++;
            }
            count += j;
        }

        i = j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return new PairCountResult(count, merged);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        PairCountResult result = countPairs(arr, 0, n);
        System.out.println(result.count);
    }
}
