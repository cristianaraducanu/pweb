package com.foodiefrenzy.entity;

import com.foodiefrenzy.dto.IngredientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ingredientGenerator")
    @SequenceGenerator(
            name = "ingredientGenerator",
            sequenceName = "ingredient_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name")
    private String name;
}
