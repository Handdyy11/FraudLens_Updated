package com.fraudlens.graph;

import com.fraudlens.model.Transaction;
import java.util.*;

/**
 * Builds and maintains the directed adjacency list of UPI transactions.
 */
public class TransactionGraph {

    // De-duplicated adjacency list (unique edges only)
    private HashMap<String, Set<String>> uniqueEdges;

    // Raw transaction list kept for detectors that need timestamps
    private List<Transaction> transactions;

    public TransactionGraph() {
        this.uniqueEdges = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    /**
     * Builds adjacency list from the provided transaction list.
     * Time complexity: O(E) where E = number of transactions
     */
    public void buildGraph(List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
        this.uniqueEdges = new HashMap<>();

        for (Transaction t : transactions) {
            String from = t.getFromAccount();
            String to = t.getToAccount();

            // Unique Edges
            if (!uniqueEdges.containsKey(from)) {
                uniqueEdges.put(from, new HashSet<>());
            }
            uniqueEdges.get(from).add(to);

            if (!uniqueEdges.containsKey(to)) {
                uniqueEdges.put(to, new HashSet<>());
            }
        }
    }

    /** Returns adjacency list with unique edges only (for cycle/hub detection). */
    public HashMap<String, Set<String>> getUniqueAdjacencyList() {
        return uniqueEdges;
    }

    /** Returns the full transaction list for detectors that need timestamps. */
    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}

