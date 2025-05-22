package com.auditxtoolkit.auditxtoolkit.controller;

import com.auditxtoolkit.auditxtoolkit.dto.request.NmapReportRequestDTO;
import com.auditxtoolkit.auditxtoolkit.model.NmapReport;
import com.auditxtoolkit.auditxtoolkit.model.User;
import com.auditxtoolkit.auditxtoolkit.service.NmapReportService;
import com.auditxtoolkit.auditxtoolkit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nmap/reports")
public class NmapReportController {

    private final NmapReportService nmapReportService;
    private final UserService userService;

    public NmapReportController(NmapReportService nmapReportService, UserService userService) {
        this.nmapReportService = nmapReportService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<NmapReport> saveReport(@Valid @RequestBody NmapReportRequestDTO dto) {
        NmapReport report = new NmapReport();
        report.setCommand(dto.getCommand());
        report.setOutput(dto.getOutput());
        report.setExitCode(dto.getExitCode());

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

    @PostMapping("/user/{userId}")
    public ResponseEntity<NmapReport> saveReportForUser(
            @PathVariable Integer userId,
            @Valid @RequestBody NmapReportRequestDTO dto) {
        User user = userService.getUserEntityById(userId);
        NmapReport report = new NmapReport();
        report.setCommand(dto.getCommand());
        report.setOutput(dto.getOutput());
        report.setExitCode(dto.getExitCode());
        report.setCreatedAt(java.time.LocalDateTime.now());
        report.setUser(user);

        NmapReport saved = nmapReportService.saveReport(report);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NmapReport>> getReportsByUser(@PathVariable Integer userId) {
        List<NmapReport> reports = nmapReportService.getReportsByUserId(userId);
        return ResponseEntity.ok(reports);
    }

}
