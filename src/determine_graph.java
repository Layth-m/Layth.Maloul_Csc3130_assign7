
import java.util.Scanner;

public class determine_graph {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the matrix (n): ");
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        System.out.println("Enter the elements of the matrix (0 for no edge, 1 for edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        if (isDirectedGraph(matrix)) {
            System.out.println("The given matrix represents a directed graph.");
        } else {
            System.out.println("The given matrix does not represent a directed graph.");
        }

        scanner.close();
    }
    public static boolean isDirectedGraph(int[][] matrix) {
        int n = matrix.length;

        // Check  matrix is square
        if (n != matrix[0].length) {
            return false;
        }
        // check matrix is symmetric along  diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        // check diagonal elements are all zeros (no self-loops)
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] != 0) {
                return false;
            }
        }
        return true;
    }
}

