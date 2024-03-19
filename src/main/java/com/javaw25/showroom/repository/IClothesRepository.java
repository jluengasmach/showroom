package com.javaw25.showroom.repository;

import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.model.Cloth;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IClothesRepository extends JpaRepository<Cloth, Integer> {

    public List<Cloth> findAllByTalle(String talle);

//    @Query("select c from Cloth c where c.nombre like :name")
//    List<Cloth> findAllByName(String name);

    List<Cloth> findAllByNombreContainingIgnoreCase(String name);

}