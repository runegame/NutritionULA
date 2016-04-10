package com.nutritionula.classes;

/**
 * Created by Angel C on 08/04/2016.
 */
public class Food {
    private int _ID;
    private String name;
    private String description;
    private int group;
    private int calories;
    private double carbohydrates;
    private double proteins;
    private double lipids;
    private int points;
    private String url;

    public int get_ID() {
        return _ID;
    }

    public Food(int id, String name, String description,int group, int calories, double carbohydrates, double proteins, double lipids, int points, String url) {
        set_ID(id);
        setName(name);
        setDescription(description);
        setGroup(group);
        setCalories(calories);
        setCarbohydrates(carbohydrates);
        setProteins(proteins);
        setLipids(lipids);
        setPoints(points);
        setUrl(url);
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroup () {
        return this.group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getCalories () {
        return this.calories;
    }

    public void setCalories (int calories) {
        this.calories = calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getLipids() {
        return lipids;
    }

    public void setLipids(double lipids) {
        this.lipids = lipids;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUrl () {
        return this.url;
    }

    public void setUrl (String url) {
        this.url = url;
    }
}
