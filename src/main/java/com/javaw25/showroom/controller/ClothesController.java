package com.javaw25.showroom.controller;

import com.javaw25.showroom.dto.ClothDto;
import com.javaw25.showroom.service.IClothesService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/clothes")
public class ClothesController {

    final IClothesService clothesService;

    public ClothesController(IClothesService clothesService){
        this.clothesService = clothesService;
    }

    @PostMapping
    ResponseEntity<?> createNewClothe(@RequestBody ClothDto clothDto){
        return new ResponseEntity<>(clothesService.saveCloth(clothDto), HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<?> getAllClothes(@RequestParam(value = "name", required = false) String name){
        if(name != null)
            return new ResponseEntity<>(clothesService.getClothesByName(name), HttpStatus.OK);
        return new ResponseEntity<>(clothesService.getCloths(), HttpStatus.OK);
    }
    @GetMapping("/{code}")
    ResponseEntity<?> getAllClothesByCode(@PathVariable Integer code){
        return new ResponseEntity<>(clothesService.getClothByCode(code), HttpStatus.OK);
    }
    @PutMapping("/{code}")
    ResponseEntity<?> updateCloth(@RequestBody ClothDto clothDto, @PathVariable Integer code){
        return new ResponseEntity<>(clothesService.updateCloth(clothDto,code), HttpStatus.OK);
    }
    @DeleteMapping("/{code}")
    ResponseEntity<?> deleteCloth(@PathVariable int code){
        return new ResponseEntity<>(clothesService.deleteCloth(code), HttpStatus.OK);
    }
    @GetMapping("/{size}")
    ResponseEntity<?> getAllClothesBySize(@PathVariable String size){
        return new ResponseEntity<>(clothesService.getClothBySize(size), HttpStatus.OK);
    }
}
