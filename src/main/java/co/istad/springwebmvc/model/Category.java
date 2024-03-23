package co.istad.springwebmvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="categories")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(unique = true, nullable = false, length = 40)
    private  String name;
    @Column(columnDefinition = "TEXT")
    private String description;

}
