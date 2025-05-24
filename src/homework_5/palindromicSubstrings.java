//Given a string s, write an algorithm that counts the number of non-empty palindromic substrings in O(nlogn) time.

package homework_5;

import java.util.Scanner;

public class palindromicSubstrings {

    static long countPalindromeSubstrings(String s) {
        s = s.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder("*");
        for (char c : s.toCharArray()) {
            stringBuilder.append(c).append("*");
        }
        int n = stringBuilder.length();
        int[] dp = new int[n];
        int center = 0, right = 0;
        long count = 0;
        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;
            if (i < right) {
                dp[i] = Math.min(right - i, dp[mirror]);
            }
            int a = i + (1 + dp[i]);
            int b = i - (1 + dp[i]);
            while (a < n && b >= 0 && stringBuilder.charAt(a) == stringBuilder.charAt(b)) {
                dp[i]++;
                a++;
                b--;
            }
            if (i + dp[i] > right) {
                center = i;
                right = i + dp[i];
            }
            count += (dp[i] + 1) / 2;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        long count = countPalindromeSubstrings(input);
        System.out.println(count);
    }
}
