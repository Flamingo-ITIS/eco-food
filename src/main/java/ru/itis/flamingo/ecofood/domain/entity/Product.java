package ru.itis.flamingo.ecofood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.CountType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Table(name = "product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "count_type")
    private CountType countType;

    @Column(name = "count")
    private Integer count;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "product_image",
        joinColumns = { @JoinColumn(name = "product_id") },
        inverseJoinColumns = { @JoinColumn(name = "image_id") }
    )
    private List<Image> images;

}
