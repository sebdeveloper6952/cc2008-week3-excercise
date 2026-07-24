import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashUtil util = new HashUtil();

        // String hash = util.sha256("UVG");
        // System.out.println(hash);

        Transaction tx = new Transaction("Alex", "Otto", 2.3);
        Transaction tx1 = new Transaction("Henry", "Mario", 0.5);

        Mempool mempool = new Mempool();
        mempool.submit(tx);
        mempool.submit(tx1);
        System.out.println("mempool has " + mempool.size() + " txs");

        List<Transaction> pending = mempool.take(5);
        for (Transaction tmp : pending) {
            System.out.println(tmp);
        }
    }
}
