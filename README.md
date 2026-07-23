# Blockchain exercise — class diagram

```mermaid
classDiagram
class Transaction {
  -String id
  -String payer
  -String payee
  -double amount
  -double fee
  +Transaction(String payer, String payee, double amount)
  +Transaction(String payer, String payee, double amount, double fee)
  +getTotal() double
  +toString() String
}
class Mempool {
  -List~Transaction~ pending
  +submit(Transaction tx) void
  +take(int max) List~Transaction~
  +size() int
}
class Block {
  -int index
  -long timestamp
  -String previousHash
  -String hash
  -int nonce
  -List~Transaction~ transactions
  +Block(int index, String previousHash)
  +Block(int index, String previousHash, List~Transaction~ txs)
  +addTransaction(Transaction tx) boolean
  +computeHash() String
  +getHash() String
}
class Blockchain {
  -String name
  -int difficulty
  -List~Block~ chain
  +Blockchain()
  +Blockchain(int difficulty)
  +append(Block b) boolean
  +getLastBlock() Block
  +getBalanceOf(String address) double
  +isValid() boolean
  +size() int
}
class Miner {
  -String alias
  -String rewardAddress
  -Mempool mempool
  +Miner(String alias, String rewardAddress, Mempool mempool)
  +mine(Blockchain bc) Block
  +mine(Blockchain bc, int maxTx) Block
  +getRewardAddress() String
}
class HashUtil {
  <<utility>>
  +sha256(String data) String
  +hash(String data, int nonce) String
}
Blockchain "1" *-- "1..*" Block : contains
Block "1" o-- "0..*" Transaction : includes
Mempool "1" o-- "0..*" Transaction : holds
Miner "1" --> "1" Mempool : mempool
Miner ..> Blockchain : uses
Miner ..> Block : creates
Block ..> HashUtil : uses
Transaction ..> HashUtil : uses
```

## Reading the relationships

| Notation | Relationship | Where it appears | Why |
| --- | --- | --- | --- |
| filled diamond | composition | `Blockchain` — `Block` | the chain builds its own genesis block and blocks have no life outside it |
| hollow diamond | aggregation | `Mempool` / `Block` — `Transaction` | the same transaction object exists in the mempool before it is included in a block |
| solid arrow | association | `Miner` — `Mempool` | a field pointing at an object created elsewhere and shared |
| dashed arrow | dependency | `Miner` — `Blockchain` / `Block`, `Block` / `Transaction` — `HashUtil` | received as a parameter, produced as a return value, or used through a static call — never stored |

**Rule of thumb for students:** a field is an association, a parameter or local variable is a dependency. Ownership of the life cycle is what upgrades an association to aggregation or composition.

## Notes

- There is no account class. A payer or payee is just a `String` address, and a balance is computed with `Blockchain.getBalanceOf(address)` by walking every block and summing the transactions that touch it. This is roughly how Bitcoin actually works.
- Method overloading appears in three places: `Blockchain`, `Transaction`, and `Miner.mine`.
- Encapsulation is enforced through `Block`, whose `hash` and `nonce` are private and writable only by `computeHash()`, and `Blockchain.append()`, which rejects any block whose `previousHash` does not match the current tip.
