package com.mace.swfs.persistance.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Domain for logging API request and responses along with http status and header information.
 */
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "api_log")
@NoArgsConstructor
@AllArgsConstructor
public class ApiLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uri")
    private String uri;

    @Column(name = "method", length = 15)
    private String method;

    @Column(name = "request_headers", columnDefinition = "TEXT")
    private String requestHeaders;

    @Column(name = "request_body", columnDefinition = "TEXT")
    private String requestBody;

    @Column(name = "response_headers", columnDefinition = "TEXT")
    private String responseHeaders;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "status_text", columnDefinition = "TEXT")
    private String statusText;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}