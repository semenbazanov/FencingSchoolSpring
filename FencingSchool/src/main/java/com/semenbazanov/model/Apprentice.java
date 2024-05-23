package com.semenbazanov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apprentices")
public class Apprentice {
    /**
     * •	id
     * •	surname
     * •	name
     * •	patronymic
     * •	phoneNumber
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "apprentice")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Training> trainings;
}
