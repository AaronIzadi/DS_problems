/*Fergus asks his dad, Shrek, to help with his kindergarten homework, which involves calculating binomial coefficients m of n (combinations).
Shrek refuses to believe such math is taught to kids and doesn’t help. Given multiple queries of the form m of n mod p calculate the answers efficiently.*/

package homework_2;

import java.io.*;
import java.util.*;

public class combinationModulo {
    static long p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        p = Long.parseLong(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sb.append(binomialMod(n, k, p)).append("\n");
        }

        System.out.print(sb);
    }

    // Safe binomial coefficient mod p (no large factorials)
    static long binomialMod(int n, int k, long mod) {
        if (k < 0 || k > n) return 0;

        long numerator = 1;
        long denominator = 1;

        for (int i = 0; i < k; i++) {
            numerator = (numerator * (n - i)) % mod;
            denominator = (denominator * (i + 1)) % mod;
        }

        return (numerator * modInverse(denominator, mod)) % mod;
    }

    // Modular inverse using Fermat’s Little Theorem (mod is prime)
    static long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}

