package com.foodiefrenzy.entity;

import com.foodiefrenzy.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoryGenerator")
    @SequenceGenerator(
            name = "categoryGenerator",
            sequenceName = "category_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name")
    private String name;
}
