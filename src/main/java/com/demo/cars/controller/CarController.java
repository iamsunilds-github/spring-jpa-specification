package com.demo.cars.controller;

import com.demo.cars.controller.criteria.CarsSearchCriteria;
import com.demo.cars.mapper.CarMapper;
import com.demo.cars.model.CarDto;
import com.demo.cars.model.CarsPageResponse;
import com.demo.cars.service.CarFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private final CarFilterService carFilterService;
    private final CarMapper carMapper;

    public CarController(CarFilterService carFilterService,
                         CarMapper carMapper) {
        this.carFilterService = carFilterService;
        this.carMapper = carMapper;
    }

    @GetMapping(value = "/cars")
    @ResponseStatus(HttpStatus.OK)
    CarsPageResponse<CarDto> getCars(CarsSearchCriteria criteria) {
        return carFilterService.searchCars(carMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
    }

}