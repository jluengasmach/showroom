package com.javaw25.showroom.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer numero;
    LocalDate fecha;
    Double total;
    String medioPago;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cloth_sales",
            joinColumns = @JoinColumn(name = "numero_sale"),
            inverseJoinColumns = @JoinColumn(name = "codigo_cloth")
    )
    Set<Cloth> listaPrendas;
}
