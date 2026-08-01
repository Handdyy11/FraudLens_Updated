package com.fraudlens.controller;

import com.fraudlens.service.FraudAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller responsible for account entity endpoints.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private FraudAnalysisService fraudAnalysisService;

    /**
     * GET /api/accounts
     * Returns all 100 accounts with risk scores and status badges.
     */
    @GetMapping
    public List<Map<String, Object>> getAccounts() {
        return fraudAnalysisService.getAccountInfoList();
    }
}
