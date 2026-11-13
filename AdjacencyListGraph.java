import java.util.*;

/**
 * Graph stored as one hash map (neighbor -> weight) per vertex.
 * LinkedHashMap keeps insertion order so output is deterministic.
 *
 * Space: Theta(V + E).
 * hasEdge / weight: O(1) expected.
 * adj(v): O(out-degree), so BFS is O(V + E) and heap Dijkstra is
 * O((V + E) log V).
 */
public class AdjacencyListGraph extends Graph {

    private final List<Map<Integer, Integer>> neighbors;

    public AdjacencyListGraph(int V, boolean directed) {
        super(V, directed);
        neighbors = new ArrayList<>(V);
        for (int v = 0; v < V; v++) neighbors.add(new LinkedHashMap<>());
    }

    @Override
    protected void addArc(int v1, int v2, int weight) {
        neighbors.get(v1).put(v2, weight); // temps constant attendu
    }

    @Override
    public boolean hasEdge(int v1, int v2) {
        return valid(v1) && neighbors.get(v1).containsKey(v2);
    }

    @Override
    public int weight(int v1, int v2) {
        Integer w = valid(v1) ? neighbors.get(v1).get(v2) : null;
        if (w == null) throw new NoSuchElementException("no edge " + v1 + "->" + v2);
        return w;
    }

    @Override
    public Collection<Integer> adj(int v) {
        if (!valid(v)) return Collections.emptyList();
        // vue en lecture seule des clés, sans copie : on paie seulement le degré en itérant
        return Collections.unmodifiableSet(neighbors.get(v).keySet());
    }
}
