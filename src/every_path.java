
import java.util.ArrayList;
        import java.util.List;

public class every_path {

    private int[][] graph;
    private int vertices;

    public every_path(int[][] graph) {
        this.graph = graph;
        this.vertices = graph.length;
    }

    public List<List<Integer>> findAllSimplePaths(int u, int w) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(u);
        dfs(u, w, 5, currentPath, paths);
        return paths;
    }

    private void dfs(int u, int w, int length, List<Integer> currentPath, List<List<Integer>> paths) {
        if (length == 0 && u == w) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }
        if (length <= 0) {
            return;
        }
        for (int v = 0; v < vertices; v++) {
            if (graph[u][v] != 0 && !currentPath.contains(v)) {
                currentPath.add(v);
                dfs(v, w, length - 1, currentPath, paths);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        // testing
        int[][] graph = {
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };

        every_path finder = new every_path(graph);
        List<List<Integer>> paths = finder.findAllSimplePaths(0, 4);
        for (List<Integer> path : paths) {
            System.out.println("Path: " + path);
        }
    }
}
