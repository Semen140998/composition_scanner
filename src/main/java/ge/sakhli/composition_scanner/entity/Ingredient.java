package ge.sakhli.composition_scanner.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    @Column(name = "ingredientName", nullable = false)
    private String ingredientName;

    @Column(name = "ingredientClass", nullable = false)
    private Integer ingredientClass;

    @Column(name = "ingredientDescription", nullable = false)
    private String ingredientDescription;
}
