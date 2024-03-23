package co.istad.springwebmvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(columnDefinition = "BINARY(16)")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @Column(unique = true, nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer qty;

    private LocalDate importedDate;
    private Boolean status;

//    @ManyToOne
//    private Category category;

}
