package com.fraudlens.controller;

import com.fraudlens.service.FraudAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller responsible for fraud detection analytics endpoints.
 * Handles detection of circular laundering patterns and high-degree hub accounts.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/fraud")
public class FraudDetectionController {

    @Autowired
    private FraudAnalysisService fraudAnalysisService;

    /**
     * GET /api/fraud/cycles
     * Returns all detected circular money laundering patterns.
     */
    @GetMapping("/cycles")
    public List<List<String>> getCycles() {
        return fraudAnalysisService.getCycles();
    }

    /**
     * GET /api/fraud/hubs
     * Returns top hub accounts (abnormally high connection counts).
     */
    @GetMapping("/hubs")
    public List<String> getHubs() {
        return fraudAnalysisService.getHubs();
    }
}

