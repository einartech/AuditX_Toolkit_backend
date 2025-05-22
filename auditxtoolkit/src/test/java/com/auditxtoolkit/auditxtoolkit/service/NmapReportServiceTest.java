package com.auditxtoolkit.auditxtoolkit.service;

import com.auditxtoolkit.auditxtoolkit.exception.NmapReportExceptions;
import com.auditxtoolkit.auditxtoolkit.model.NmapReport;
import com.auditxtoolkit.auditxtoolkit.repository.NmapReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NmapReportServiceTest {

    private NmapReportRepository nmapReportRepository;
    private NmapReportService nmapReportService;

    @BeforeEach
    void setUp() {
        nmapReportRepository = mock(NmapReportRepository.class);
        nmapReportService = new NmapReportService(nmapReportRepository);
    }

    @Test
    void saveReport_shouldSetCreatedAtAndSave() {
        NmapReport report = new NmapReport();
        when(nmapReportRepository.save(any(NmapReport.class))).thenReturn(report);

        NmapReport saved = nmapReportService.saveReport(report);

        assertNotNull(saved);
        assertNotNull(report.getCreatedAt());
        verify(nmapReportRepository, times(1)).save(report);
    }

    @Test
    void getAllReports_shouldReturnList() {
        List<NmapReport> reports = Arrays.asList(new NmapReport(), new NmapReport());
        when(nmapReportRepository.findAll()).thenReturn(reports);

        List<NmapReport> result = nmapReportService.getAllReports();

        assertEquals(2, result.size());
        verify(nmapReportRepository, times(1)).findAll();
    }

    @Test
    void getReportById_shouldReturnReportIfExists() {
        NmapReport report = new NmapReport();
        when(nmapReportRepository.findById(1L)).thenReturn(Optional.of(report));

        Optional<NmapReport> result = nmapReportService.getReportById(1L);

        assertTrue(result.isPresent());
        assertEquals(report, result.get());
    }

    @Test
    void getReportById_shouldThrowIfNotFound() {
        when(nmapReportRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NmapReportExceptions.NmapReportNotFoundException.class,
                () -> nmapReportService.getReportById(1L));
    }

    @Test
    void deleteReport_shouldDeleteIfExists() {
        when(nmapReportRepository.existsById(1L)).thenReturn(true);

        nmapReportService.deleteReport(1L);

        verify(nmapReportRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteReport_shouldThrowIfNotExists() {
        when(nmapReportRepository.existsById(1L)).thenReturn(false);

        assertThrows(NmapReportExceptions.NmapReportNotFoundException.class,
                () -> nmapReportService.deleteReport(1L));
    }

    @Test
    void getReportsByUserId_shouldReturnList() {
        List<NmapReport> reports = Arrays.asList(new NmapReport(), new NmapReport());
        when(nmapReportRepository.findByUserId(42)).thenReturn(reports);

        List<NmapReport> result = nmapReportService.getReportsByUserId(42);

        assertEquals(2, result.size());
        verify(nmapReportRepository, times(1)).findByUserId(42);
    }
}
