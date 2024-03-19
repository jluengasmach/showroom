package com.javaw25.showroom.service;

import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.dto.MessageDto;
import com.javaw25.showroom.dto.SaleDto;
import java.time.LocalDate;
import java.util.List;

public interface ISalesService {

    public List<SaleDto> getAll();
    public SaleDto getByNumber(Integer number);
    public SaleDto save(SaleDto saleDto);
    public SaleDto update(SaleDto saleDto, Integer number);
    public List<SaleDto> getAllByDate(LocalDate date);
    public List<ClothDto> getAllClothBySale(Integer number);
    public MessageDto deleteSale(Integer number);

}
