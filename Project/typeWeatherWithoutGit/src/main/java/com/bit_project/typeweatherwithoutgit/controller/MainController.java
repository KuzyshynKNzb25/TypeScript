package com.bit_project.typeweatherwithoutgit.controller;

import com.bit_project.typeweatherwithoutgit.dto.CityDto;
import com.bit_project.typeweatherwithoutgit.dto.SimpleWeatherRequest;
import com.bit_project.typeweatherwithoutgit.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class MainController {
    @Autowired
    MainService mainService;
    @GetMapping("/test")
    public void test() {
        mainService.testRequest();
        
    }


    @GetMapping("/initial-request")
    public SimpleWeatherRequest initialRequest() {
        return mainService.makeInitialRequest();
    }


    @GetMapping("/offer-city-by-name/{city-name}")
    public List<CityDto> offerCityByCityName(
            @PathVariable("city-name") String cityName) {

        if(cityName == null || cityName.length() < 2) {
            return null;
        }

        return mainService.offerCityByName(cityName);
    }


    @PostMapping("/find-by-item")
    public SimpleWeatherRequest findByItem(
            @RequestBody CityDto cityItem) {

        return mainService.findByItem(cityItem.getCity());
    }
}
