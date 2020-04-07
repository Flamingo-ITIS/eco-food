package ru.itis.flamingo.ecofood.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Table(name = "article")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_seq")
    @SequenceGenerator(name = "article_id_seq", sequenceName = "article_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    //Основная часть статьи
    @Column(name = "lid")
    private String lid;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private String date;
}
