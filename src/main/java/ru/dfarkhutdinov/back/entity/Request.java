package ru.dfarkhutdinov.back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Setter
@Getter
@Table(name = "requests")
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Banner is required")
    @ManyToOne
    @JoinColumn(name = "banner_id", referencedColumnName = "id", nullable = false)
    private Banner banner;

    @NotNull(message = "User agent is required")
    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @Pattern(regexp = ipPattern, message = "Incorrect ip address")
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$", message = "Incorrect date format")
    @Column(name = "date")
    private Date date;

    private static final String ipPattern = "^((0|1\\\\d?\\\\d?|2[0-4]?\\\\d?|25[0-5]?|[3-9]\\\\d?)\\\\.)" +
            "{3}(0|1\\\\d?\\\\d?|2[0-4]?\\\\d?|25[0-5]?|[3-9]\\\\d?)$";
}
