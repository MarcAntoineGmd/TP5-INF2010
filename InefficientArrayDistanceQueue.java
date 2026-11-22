/**
 * InefficientArrayDistanceQueue: keys and values are both stored
 * in arrays with corresponding indices.  Both decreaseKey and extractMin
 * iterate over these arrays
 */
public class InefficientArrayDistanceQueue implements DistanceQueue {

    private long[] queueKeys;
    private int[] queueValues;
    private int size = 0;

    public InefficientArrayDistanceQueue(int V) {
        queueKeys = new long[V];
        queueValues = new int[V];
    }

    @Override
    public void decreaseKey(int v, long k) {
        int i = 0;
        while (i < size){
            if (v == queueValues[i]) {
                queueKeys[i] = k;
                return;
            }
            i++; // ajouté, sinon boucle infinie...
        }
        queueValues[size] = v;
        queueKeys[size++] = k;
    }

    @Override
    public int extractMin() {
        if (size == 0) throw new IllegalStateException("empty queue");
        int m = 0;
        for (int i = 1; i < size; i++)
            if (queueKeys[i] < queueKeys[m]) m = i;
        int v = queueValues[m];
        size--;
        queueValues[m] = queueValues[size];
        queueKeys[m] = queueKeys[size];
        return v;
    }

    @Override
    public boolean isEmpty() { return size == 0; }
}
