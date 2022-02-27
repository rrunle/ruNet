package ru.abilov.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Lord")
@Getter
@Setter
@ToString
public class Lord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private long age;

    @OneToMany(mappedBy = "lord", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Planet> planets;
}
