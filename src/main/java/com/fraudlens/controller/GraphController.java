package com.fraudlens.controller;

import com.fraudlens.service.FraudAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

/**
 * REST Controller responsible for network graph visualization endpoints.
 * Returns nodes and edges formatted for Vis.js rendering.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/graph")
public class GraphController {

    @Autowired
    private FraudAnalysisService fraudAnalysisService;

    /**
     * GET /api/graph
     * Returns all nodes and edges for Vis.js network graph rendering.
     *
     * Node status is pulled from the Account.riskLevel field, which uses the
     * same statusFromScore() logic as the accounts table — ensuring consistency.
     */
    @GetMapping
    public Map<String, Object> getGraph(
            @RequestParam(defaultValue = "month") String view,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null && !"month".equalsIgnoreCase(view)) {
            date = LocalDate.of(2024, 1, 18);  //default date for testing purposes
        }
        return fraudAnalysisService.getGraphDataForSlice(view, date);
    }
}
