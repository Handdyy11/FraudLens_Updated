package com.fraudlens.controller;

import com.fraudlens.model.Transaction;
import com.fraudlens.service.FraudAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST Controller responsible for transaction retrieval and DTO serialization.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private FraudAnalysisService fraudAnalysisService;

    /**
     * GET /api/transactions?date=YYYY-MM-DD
     * Returns transactions filtered by date. Without a date param, returns the 100 most recent.
     */
    @GetMapping
    public List<Map<String, Object>> getTransactions(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Transaction> txns = date != null
                ? fraudAnalysisService.filterByDate(date)
                : fraudAnalysisService.getTransactions().stream()
                    .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                    .limit(100)
                    .collect(Collectors.toList());

        return txns.stream().map(this::txnToMap).collect(Collectors.toList());
    }

    private Map<String, Object> txnToMap(Transaction t) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("txnId", t.getTxnId());
        m.put("fromAccount", t.getFromAccount());
        m.put("toAccount", t.getToAccount());
        m.put("amount", t.getAmount());
        m.put("timestamp", t.getTimestamp().toString());
        m.put("type", t.getType());
        return m;
    }
}
