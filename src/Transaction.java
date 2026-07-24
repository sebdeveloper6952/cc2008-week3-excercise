public class Transaction {
    String id;
    String payer;
    String payee;
    double amount;
    double fee;

    public Transaction(String payer, String payee, double amount) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public Transaction(String payer, String payee, double amount, double fee) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
        this.fee = fee;
    }

    public double getTotal() {
        return amount + fee;
    }

    public String toString() {
        return payer + " - > " + payee + " " + amount + "BTC";
    }
}
