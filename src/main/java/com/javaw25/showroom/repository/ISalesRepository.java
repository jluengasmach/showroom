package com.javaw25.showroom.repository;

import com.javaw25.showroom.dto.SaleDto;
import com.javaw25.showroom.model.Cloth;
import com.javaw25.showroom.model.Sale;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepository extends JpaRepository<Sale, Integer> {
    public List<Sale> findAllByFecha(LocalDate date);
}
