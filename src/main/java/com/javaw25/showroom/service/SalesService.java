package com.javaw25.showroom.service;

import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.dto.MessageDto;
import com.javaw25.showroom.dto.SaleDto;
import com.javaw25.showroom.exceptions.NotFoundException;
import com.javaw25.showroom.model.Cloth;
import com.javaw25.showroom.model.Sale;
import com.javaw25.showroom.repository.ISalesRepository;
import com.javaw25.showroom.util.Mapper;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Service
public class SalesService implements ISalesService {

    private final ISalesRepository salesRepo;

    public SalesService(ISalesRepository salesRepo) {
        this.salesRepo = salesRepo;
    }

    @Override
    public List<SaleDto> getAll() {
        return salesRepo.findAll()
                .stream()
                .map(sale -> Mapper.getMapper().map(sale, SaleDto.class))
                .toList();
    }

    @Override
    public SaleDto getByNumber(Integer number) {
        Sale sale = salesRepo.findById(number).orElseThrow(() -> new NotFoundException("Venta no encontrada."));
        return Mapper.getMapper().map(sale, SaleDto.class);
    }

    @Override
    public SaleDto save(SaleDto saleDto) {
        Sale sale = salesRepo.save(Mapper.getMapper().map(saleDto, Sale.class));
        return Mapper.getMapper().map(sale, SaleDto.class);
    }

    @Override
    public SaleDto update(SaleDto saleDto, Integer number) {
        saleDto.setNumero(number);
        Sale sale = Mapper.getMapper().map(saleDto, Sale.class);
        salesRepo.save(sale);
        return saleDto;
    }

    @Override
    public List<SaleDto> getAllByDate(LocalDate date) {
        return salesRepo.findAllByFecha(date)
                .stream()
                .map(sale -> Mapper.getMapper().map(sale, SaleDto.class))
                .toList();
    }

    @Override
    public List<ClothDto> getAllClothBySale(Integer number) {
        return salesRepo.findById(number)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada.")).getListaPrendas()
                .stream()
                .map(cloth -> Mapper.getMapper().map(cloth, ClothDto.class))
                .toList();
    }

    @Override
    public MessageDto deleteSale(Integer number){
        salesRepo.deleteById(number);
        return new MessageDto("Deleted successfully");
    }
}
