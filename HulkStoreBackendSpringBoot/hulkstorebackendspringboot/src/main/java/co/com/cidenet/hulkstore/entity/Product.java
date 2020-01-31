package co.com.cidenet.hulkstore.entity;
/*
Created by : Jaime Mejia
*/

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "productos")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede estar vacio")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "no puede estar vacio")
    @Min(value = 0L, message = "El numero debe de ser positivo")
    private Integer price;

    @NotNull(message = "no puede estar vacio")
    //@Min(value = 0L, message = "El numero debe de ser positivo")
    private Integer quantity;

    @NotEmpty(message = "no puede estar vacio")
    @Column(nullable = false)
    private String category;

    private static final long serialVersionUID = 1L;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
