package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.model.NmapReport;
import com.auditxtoolkit.auditxtoolkit.repository.NmapReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NmapReportService {

    private final NmapReportRepository nmapReportRepository;

    public NmapReportService(NmapReportRepository nmapReportRepository) {
        this.nmapReportRepository = nmapReportRepository;
    }

    public NmapReport saveReport(NmapReport report) {
        report.setCreatedAt(LocalDateTime.now());
        return nmapReportRepository.save(report);
    }

    public List<NmapReport> getAllReports() {
        return nmapReportRepository.findAll();
    }

    public Optional<NmapReport> getReportById(Long id) {
        return nmapReportRepository.findById(id);
    }

    public void deleteReport(Long id) {
        nmapReportRepository.deleteById(id);
    }

    public List<NmapReport> getReportsByUserId(Integer userId) {
        return nmapReportRepository.findByUserId(userId);
    }
}
