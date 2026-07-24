import java.util.ArrayList;
import java.util.List;

public class Mempool {
    private List<Transaction> pending;

    public Mempool() {
        this.pending = new ArrayList<Transaction>();
    }

    public void submit(Transaction tx) {
        pending.add(tx);
    }

    public List<Transaction> take(int max) {
        if (pending.size() >= max) {
            return pending.subList(0, max);
        }

        return pending.subList(0, pending.size());
    }

    public int size() {
        return pending.size();
    }
}
