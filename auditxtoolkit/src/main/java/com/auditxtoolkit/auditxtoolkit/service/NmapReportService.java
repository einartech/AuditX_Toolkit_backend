package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.exception.NmapReportExceptions;
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
        return Optional.ofNullable(
                nmapReportRepository.findById(id)
                        .orElseThrow(() -> new NmapReportExceptions.NmapReportNotFoundException(id)));
    }

    public void deleteReport(Long id) {
        if (!nmapReportRepository.existsById(id)) {
            throw new NmapReportExceptions.NmapReportNotFoundException(id);
        }
        nmapReportRepository.deleteById(id);
    }

    public List<NmapReport> getReportsByUserId(Integer userId) {
        return nmapReportRepository.findByUserId(userId);
    }
}
