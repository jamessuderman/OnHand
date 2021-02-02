/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/27/2021
 */

package com.sudee.gcu.onhand.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeIngredientId;
    @OneToOne
    private Ingredient ingredient;
    private int amount;

}
