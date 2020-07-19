package ru.dfarkhutdinov.back.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name is required")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotNull(message = "ReqName is required")
    @Column(name = "req_name", nullable = false)
    private String reqName;

    @NotNull(message = "Deleted is required")
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private transient Set<Banner> banners;

}
