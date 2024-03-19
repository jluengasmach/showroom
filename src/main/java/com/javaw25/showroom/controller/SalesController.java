package com.javaw25.showroom.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.dto.MessageDto;
import com.javaw25.showroom.dto.SaleDto;
import com.javaw25.showroom.model.Cloth;
import com.javaw25.showroom.service.SalesService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService service;

    public SalesController(SalesService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SaleDto> newSale(@RequestBody SaleDto saleDto){
        return new ResponseEntity<>(service.save(saleDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> getSales(@RequestParam(value = "date", required = false) @JsonFormat(pattern = "dd-MM-yyyy")LocalDate date){
        if (date==null) return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        return new ResponseEntity<>(service.getAllByDate(date), HttpStatus.OK);
    }

    @GetMapping("/{number}")
    public ResponseEntity<SaleDto> getSale(@PathVariable Integer number){
        return  new ResponseEntity<>(service.getByNumber(number), HttpStatus.OK);
    }

    @PutMapping("/{number}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable Integer number, @RequestBody SaleDto saleDto){
        return  new ResponseEntity<>(service.update(saleDto, number), HttpStatus.OK);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<MessageDto> deleteSale(@PathVariable Integer number){
        return  new ResponseEntity<>(service.deleteSale(number), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/clothes/{number}")
    public ResponseEntity<List<ClothDto>> getSalesByClothes(@PathVariable Integer number){
        return  new ResponseEntity<>(service.getAllClothBySale(number), HttpStatus.OK);
    }
}
