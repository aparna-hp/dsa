import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafeNodes {

    List<Integer> safeNodes = new ArrayList<>();

    public static void main(String[] args) {
        SafeNodes safeNodes = new SafeNodes();
        int[][] graph = new int[][]{{1,2},{2,3},{5},{0},{5},{},{}};

        safeNodes.eventualSafeNodes(graph);
    }

    public void eventualSafeNodes(int[][] graph) {
        int[] outgress = new int[graph.length];
        populateOutgress(graph, outgress);
        markSafeNodes(graph, outgress);
        System.out.println("Safe nodes " + Arrays.toString(safeNodes.toArray()));
    }

    public void populateOutgress(int[][] graph, int[] outgress){
        for(int i=0; i < graph.length; i++) {
            outgress[i] = graph[i].length;
        }
        System.out.println("Outgress " + Arrays.toString(outgress));
    }

    public void reComputeOutgress(int[][] graph, int[] outgress, List<Integer> newSafeNodes) {
        for(int i=0; i < graph.length; i++) {
           for(int j=0; j<graph[i].length; j++) {
               if(newSafeNodes.contains(graph[i][j])) {
                   outgress[i]--;
               }
           }
        }

        System.out.println("Recomputed outgress " + Arrays.toString(outgress));
    }

    public void markSafeNodes(int[][] graph, int[] outgress) {
        List<Integer> newSafeNodes = new ArrayList<>();
        for (int i = 0; i < outgress.length; i++) {
            if (!safeNodes.contains(i) && outgress[i] == 0) {
                safeNodes.add(i);
                newSafeNodes.add(i);
            }
        }

        System.out.println("New safe nodes " + Arrays.toString(newSafeNodes.toArray()));

        if(newSafeNodes.size() > 0) {
            reComputeOutgress(graph, outgress, newSafeNodes);
            markSafeNodes(graph, outgress);
        }
    }

}

