package com.fraudlens.controller;

import com.fraudlens.service.FraudAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

/**
 * REST Controller responsible for dashboard summary KPI statistics.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private FraudAnalysisService fraudAnalysisService;

    /**
     * GET /api/stats
     * Returns summary counts for the dashboard header cards.
     */
    @GetMapping
    public Map<String, Object> getStats(
            @RequestParam(defaultValue = "month") String view,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null && !"month".equalsIgnoreCase(view)) {
            date = LocalDate.of(2024, 1, 18);
        }
        return fraudAnalysisService.getStatsForSlice(view, date);
    }
}
