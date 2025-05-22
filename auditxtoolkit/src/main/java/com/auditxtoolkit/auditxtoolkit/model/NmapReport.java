package com.auditxtoolkit.auditxtoolkit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "nmap_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NmapReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String command;

    @Column(columnDefinition = "TEXT")
    private String output;

    private int exitCode;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
