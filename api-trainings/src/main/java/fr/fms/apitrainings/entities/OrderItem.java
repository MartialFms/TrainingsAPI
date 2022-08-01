package fr.fms.apitrainings.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;

    @ManyToOne
    private Training training;

    @ManyToOne(cascade = {CascadeType.ALL}) @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Orders orders;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", training=" + training +
                ", orders=" + orders +
                '}';
    }
}
