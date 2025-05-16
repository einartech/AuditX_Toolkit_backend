package com.auditxtoolkit.auditxtoolkit.controller;

import com.auditxtoolkit.auditxtoolkit.model.NmapReport;
import com.auditxtoolkit.auditxtoolkit.service.NmapReportService;
import com.auditxtoolkit.auditxtoolkit.dto.request.NmapReportRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/nmap/reports")
public class NmapReportController {

    private final NmapReportService nmapReportService;

    public NmapReportController(NmapReportService nmapReportService) {
        this.nmapReportService = nmapReportService;
    }

    @PostMapping
    public ResponseEntity<NmapReport> saveReport(@Valid @RequestBody NmapReportRequestDTO dto) {
        NmapReport report = new NmapReport();
        report.setCommand(dto.getCommand());
        report.setOutput(dto.getOutput());
        report.setExitCode(dto.getExitCode());
        // createdAt se setea en el servicio

        NmapReport saved = nmapReportService.saveReport(report);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<NmapReport>> getAllReports() {
        return ResponseEntity.ok(nmapReportService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NmapReport> getReportById(@PathVariable Long id) {
        return nmapReportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        nmapReportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
