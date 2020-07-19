package ru.dfarkhutdinov.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "banners")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banners")
public class Banner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Banner name is required")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotNull(message = "Price is required")
    @Digits(integer = 6, fraction = 2, message = "Value is too long")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "Category is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @NotNull(message = "Content is required")
    @Column(name = "content")
    private String content;

    @NotNull(message = "Deleted is required")
    @Column(name = "deleted")
    private boolean deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "banner", fetch = FetchType.EAGER)
    private transient Set<Request> requests;
}
