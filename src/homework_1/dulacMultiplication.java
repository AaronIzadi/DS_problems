/*You're asked to multiply two matrices using non-standard "Duloc math":
In Duloc, addition is replaced by multiplication, and multiplication is replaced by addition.
Given two matrices—one of size n*m and another m*k—compute their product using Duloc rules.
Then, print the resulting n*k matrix, reducing each element modulo 13800905.*/

package homework_1;

import java.util.Scanner;

public class dulacMultiplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] matrix1 = new int[n][m];
        int[][] matrix2 = new int[m][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix1[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                matrix2[i][j] = scanner.nextInt();
            }
        }

        long[][] ans = new long[n][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                ans[i][j] = 1;
                for (int l = 0; l < m; l++) {
                    if (l == 0) {
                        ans[i][j] = ((matrix1[i][l] + matrix2[l][j]) % 13800905);
                    } else {
                        ans[i][j] *= (matrix1[i][l] + matrix2[l][j]);
                        ans[i][j] = ans[i][j] % 13800905;
                    }
                }
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}