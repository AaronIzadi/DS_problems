/*Geppetto gives the dragon-donkey kids a sequence of n distinct numbers and asks them to build a max-heap from it.
Specifically, you are to count how many swaps are performed during the standard heap construction algorithm, as described in the CLRS textbook.*/

package homework_3;

import java.util.Scanner;

public class heapSwaps {

    static void maxHeapify(long[] arr, int i) {
        int l = (2 * i) + 1;
        int r = (2 * i) + 2;

        int largest = i;
        if (l < arr.length && arr[l] > arr[i]) {
            largest = l;
        }
        if (r < arr.length && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            long swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            numberOfSwap++;
            maxHeapify(arr, largest);
        }
    }

    static int numberOfSwap = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextLong();
        }

        int ceil = n / 2;
        for (int i = ceil; i >= 0; i--) {
            maxHeapify(numbers, i);
        }

        System.out.println(numberOfSwap);
    }
}
