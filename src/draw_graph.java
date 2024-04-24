import org.graphstream.graph.implementations.MultiGraph;
import java.util.HashMap;
import java.util.Map;

//only does text output

public class draw_graph {

    static Map<Character, Integer> connections;

    public static void main(String[] args) {
        //test input
        String input = "[(I,2),(A,5),(E,4),(F,1),(T,2),(S,3)]";
        connections = parseInput(input);
        drawGraph(connections);
        printAsMatrix();
    }

    private static Map<Character, Integer> parseInput(String input) {
        Map<Character, Integer> connections = new HashMap<>();
        // Remove square brackets and spaces
        input = input.replaceAll("[\\[\\]\\s]", "");
        // Split input into pairs enclosed within parentheses
        String[] pairs = input.split("\\),\\(");

        for (String pair : pairs) {
            // Remove parentheses from each pair
            pair = pair.replaceAll("[\\(\\)]", "");
            String[] components = pair.split(",");
            if (components.length == 2) {
                char vertex = components[0].charAt(0);
                int weight = Integer.parseInt(components[1]);
                connections.put(vertex, weight);
            } else {
                System.err.println("Invalid input format for pair: " + pair);
            }
        }

        return connections;
    }


    private static void drawGraph(Map<Character, Integer> connections) {
        System.out.println("Directed Graph:");
        for (Map.Entry<Character, Integer> entry : connections.entrySet()) {
            char vertex = entry.getKey();
            int weight = entry.getValue();
            char leftVertex = getVertex(connections, vertex, -weight);
            char rightVertex = getVertex(connections, vertex, weight);
            System.out.println(vertex + " -> " + leftVertex + " " + rightVertex);
        }
    }

    private static char getVertex(Map<Character, Integer> connections, char vertex, int offset) {
        int n = connections.size();
        int index = (getIndex(connections, vertex) + offset + n) % n;
        char[] vertices = new char[n];
        for (Map.Entry<Character, Integer> entry : connections.entrySet()) {
            vertices[getIndex(connections, entry.getKey())] = entry.getKey();
        }
        return vertices[index];
    }

    private static int getIndex(Map<Character, Integer> connections, char vertex) {
        int i = 0;
        for (char v : connections.keySet()) {
            if (v == vertex) {
                return i;
            }
            i++;
        }
        return -1; // Vertex not found
    }

    public static void printAsMatrix() {
        System.out.println("Here is the list as a matrix.");
        int n = connections.size();
        char[] vertices = new char[n];
        int[][] matrix = new int[n][n];

        // Populate the vertices array
        int i = 0;
        for (char vertex : connections.keySet()) {
            vertices[i] = vertex;
            i++;
        }

        // Populate the adjacency matrix
        for (Map.Entry<Character, Integer> entry : connections.entrySet()) {
            char vertex = entry.getKey();
            int index = getIndex(connections, vertex);
            int weight = entry.getValue();
            char leftVertex = getVertex(connections, vertex, -weight);
            char rightVertex = getVertex(connections, vertex, weight);
            if (leftVertex != '\0') {
                matrix[index][getIndex(connections, leftVertex)] = 1;
            }
            if (rightVertex != '\0') {
                matrix[index][getIndex(connections, rightVertex)] = 1;
            }
        }

        // Print the adjacency matrix
        for (i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}

