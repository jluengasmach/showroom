package com.javaw25.showroom.service;

import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.dto.MessageDto;
import com.javaw25.showroom.model.Cloth;
import java.util.List;

public interface IClothesService {

    public ClothDto saveCloth(ClothDto clothDto);
    public List<ClothDto> getCloths();
    public ClothDto getClothByCode(int code);
    public MessageDto updateCloth(ClothDto clothDto, int code);
    public MessageDto deleteCloth(int code);
    public List<ClothDto> getClothBySize(String talle);
    public List<ClothDto> getClothesByName(String name);

}
