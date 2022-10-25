package com.example.infits;

import android.graphics.drawable.Drawable;

public class ModelForFood {
    private String name;
    private String calorie;
    private String description;
    private String nutrients;
    private String ingredients;
    private Drawable photo;
    private String category;
    private String preparation;
    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public ModelForFood(String name, String calorie, Drawable photo){
        this.name = name;
        this.calorie = calorie;
        this.photo = photo;
    }

    // parameterized constructor
    public ModelForFood(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation, String quantity) {
        this.name = name;
        this.calorie = calorie;
        this.description = description;
        this.nutrients = nutrients;
        this.ingredients = ingredients;
        this.photo = photo;
        this.category = category;
        this.preparation = preparation;
        this.quantity = quantity;
    }

    public ModelForFood(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation) {
        this.name = name;
        this.calorie = calorie;
        this.description = description;
        this.nutrients = nutrients;
        this.ingredients = ingredients;
        this.photo = photo;
        this.category = category;
        this.preparation = preparation;
    }

    public String getName() {
        return name;
    }
    public String getCalorie() {
        return calorie;
    }

    public String getDescription() {
        return description;
    }

    public String getNutrients() {
        return nutrients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public String getCategory() {
        return category;
    }

    public String getPreparation() {
        return preparation;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
