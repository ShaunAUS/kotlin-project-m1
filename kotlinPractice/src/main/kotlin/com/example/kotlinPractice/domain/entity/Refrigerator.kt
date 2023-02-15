package com.example.kotlinPractice.domain.entity

import com.example.kotlinPractice.domain.dto.ingredient.IngredientCreateDto.Companion.INGREDIENTDTO_LIST_CONVERTER
import com.example.kotlinPractice.domain.dto.refrigerator.RefrigeratorCreateDto
import com.example.kotlinPractice.utils.ModelMapper
import jakarta.persistence.*
import lombok.Setter

//냉장고는 여러개가 될수 있다.
@Entity
class Refrigerator(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        val name: String,

        @ManyToOne
        @JoinColumn(name = "kitchen_id")
        var kitchen: Kitchen,

        @OneToMany(mappedBy = "refrigerator", orphanRemoval = true)
        val ingredients: List<Ingredient>,

        ) {
    fun updateKitchen(kitchen: Kitchen) {
        this.kitchen = kitchen
    }

    fun PutInKitchen(kitchen: Kitchen): Refrigerator {
        this.kitchen = kitchen
        return this
    }

    companion object {
        fun of(refrigeratorCreateDto: RefrigeratorCreateDto): Refrigerator {
             return ModelMapper.getMapper()
                    .map(refrigeratorCreateDto, Refrigerator::class.java)
        }
    }


}
