package com.mlp.trinity.demo.models;

import javax.persistence.*;

@Entity
public class PonyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique=true)
    String name;
    Integer health;
    Boolean isAlive;
    Integer age;
    Boolean hasHorn;
    Boolean hasWings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getHasHorn() {
        return hasHorn;
    }

    public void setHasHorn(Boolean hasHorn) {
        this.hasHorn = hasHorn;
    }

    public Boolean getHasWings() {
        return hasWings;
    }

    public void setHasWings(Boolean hasWings) {
        this.hasWings = hasWings;
    }

    @Override
    public String toString() {
        return "PonyModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", isAlive=" + isAlive +
                ", age=" + age +
                ", hasHorn=" + hasHorn +
                ", hasWings=" + hasWings +
                '}';
    }
}
