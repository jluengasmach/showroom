package com.javaw25.showroom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaw25.showroom.model.Cloth;
import java.time.LocalDate;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleDto {
    Integer numero;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate fecha;
    Double total;
    String medioPago;
    Set<ClothDto> listaPrendas;
}
