/*Shrek and Donkey have a transport company called Khark with m carriages.
Each carriage i has a_i empty seats.
There are n customers waiting in line, and each chooses which carriage they want to buy a ticket from.
The ticket price is equal to the current number of empty seats in that carriage at the moment of sale.
You need to calculate:
The maximum total sales possible (if customers act greedily to maximize company revenue),
The minimum total sales possible (if customers act to minimize company revenue).*/

package homework_4;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class carriageSeatSaleMax {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextLong();
        long m = scanner.nextLong();
        PriorityQueue<Long> minQueue = new PriorityQueue<>();
        PriorityQueue<Long> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (long i = 0; i < m; i++) {
            long input = scanner.nextLong();
            if (input != 0) {
                minQueue.add(input);
                maxQueue.add(input);
            }
        }

        long min = 0;
        long loop = n;
        while (loop > 0) {
            long minimum = minQueue.poll();
            min += minimum;
            if (minimum - 1 > 0) {
                minQueue.add(minimum - 1);
            }
            loop--;
        }

        long max = 0;
        while (n > 0) {
            long maximum = maxQueue.poll();
            max += maximum;
            if (maximum - 1 > 0) {
                maxQueue.add(maximum - 1);
            }
            n--;
        }

        System.out.println(min + " " + max);
    }
}