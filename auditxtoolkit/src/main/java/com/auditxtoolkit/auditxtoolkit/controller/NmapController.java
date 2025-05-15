package com.auditxtoolkit.auditxtoolkit.controller;

import com.auditxtoolkit.auditxtoolkit.dto.request.NmapRequestDTO;
import com.auditxtoolkit.auditxtoolkit.dto.response.NmapResponseDTO;
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
    public ResponseEntity<NmapResponseDTO> scan(@RequestBody NmapRequestDTO request) {
        // Aquí podrías agregar validaciones adicionales de seguridad
        NmapResponseDTO response = nmapService.runNmapScan(request);
        return ResponseEntity.ok(response);
    }
}
