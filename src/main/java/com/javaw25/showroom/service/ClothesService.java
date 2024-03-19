package com.javaw25.showroom.service;

import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.dto.MessageDto;
import com.javaw25.showroom.exceptions.NotFoundException;
import com.javaw25.showroom.model.Cloth;
import com.javaw25.showroom.repository.IClothesRepository;
import com.javaw25.showroom.util.Mapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClothesService implements IClothesService {

    private final IClothesRepository clothesRepository;

    public ClothesService(IClothesRepository clothesRepository){this.clothesRepository = clothesRepository;}

    @Override
    public ClothDto saveCloth(ClothDto clothDto) {
        Cloth savedCloth = clothesRepository.save(Mapper.getMapper().map(clothDto, Cloth.class));
        return Mapper.getMapper().map(savedCloth, ClothDto.class);
    }

    @Override
    public List<ClothDto> getCloths() {
        return clothesRepository.findAll()
                .stream()
                .map(cloth -> Mapper.getMapper().map(cloth, ClothDto.class))
                .toList();
    }

    @Override
    public ClothDto getClothByCode(int code) {
        Cloth cloth = clothesRepository.findById(code).orElse(null);
        if(cloth == null ){
            throw new NotFoundException("La prenda no fue encontrada");
        }
        return Mapper.getMapper().map(cloth,ClothDto.class);
    }

    @Override
    public MessageDto updateCloth(ClothDto clothDto, int code) {
        Cloth savedCloth = clothesRepository.findById(code).orElse(null);
        if (savedCloth == null){
            throw new NotFoundException("Cloth not found");
        }
        savedCloth.setNombre(clothDto.getNombre());
        savedCloth.setTipo(clothDto.getTipo());
        savedCloth.setMarca(clothDto.getMarca());
        savedCloth.setColor(clothDto.getColor());
        savedCloth.setTalle(clothDto.getTalle());
        savedCloth.setCantidad(clothDto.getCantidad());
        savedCloth.setPrecio_venta(clothDto.getPrecio_venta());
        clothesRepository.save(savedCloth);
        return new MessageDto("Updated Successfully");
    }

    @Override
    public MessageDto deleteCloth(int code) {

        clothesRepository.deleteById(code);

        return new MessageDto("Deleted successfully");
    }

    @Override
    public List<ClothDto> getClothBySize(String talle) {
        return clothesRepository.findAllByTalle(talle)
                .stream()
                .map(cloth -> Mapper.getMapper().map(cloth, ClothDto.class))
                .toList();
    }

    @Override
    public List<ClothDto> getClothesByName(String name) {
        return clothesRepository.findAllByNombreContainingIgnoreCase(name).stream().map(c -> Mapper.getMapper().map(c,ClothDto.class)).toList();
    }
}
