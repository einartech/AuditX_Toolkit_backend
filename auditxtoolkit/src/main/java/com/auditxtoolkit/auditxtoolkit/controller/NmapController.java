package com.auditxtoolkit.auditxtoolkit.controller;

import com.auditxtoolkit.auditxtoolkit.dto.NmapRequest;
import com.auditxtoolkit.auditxtoolkit.dto.NmapResponse;
import com.auditxtoolkit.auditxtoolkit.service.NmapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nmap")
public class NmapController {

    private final NmapService nmapService;

    public NmapController(NmapService nmapService) {
        this.nmapService = nmapService;
    }

    @PostMapping("/scan")
    public ResponseEntity<NmapResponse> scan(@RequestBody NmapRequest request) {
        // Aquí podrías agregar validaciones adicionales de seguridad
        NmapResponse response = nmapService.runNmapScan(request);
        return ResponseEntity.ok(response);
    }
}
