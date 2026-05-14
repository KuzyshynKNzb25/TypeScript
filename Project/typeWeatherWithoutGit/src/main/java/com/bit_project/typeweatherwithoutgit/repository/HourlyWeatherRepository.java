package com.bit_project.typeweatherwithoutgit.repository;

import com.bit_project.typeweatherwithoutgit.entity.HourlyWeather;
import com.bit_project.typeweatherwithoutgit.entity.WeatherByCity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HourlyWeatherRepository extends JpaRepository<HourlyWeather,Long> {
    List<HourlyWeather> findByCityId(long cityId);

    @Modifying
    @Transactional
    @Query("DELETE FROM HourlyWeather table WHERE table.time < :lessDate")
    void deleteAllWithLessDate(LocalDateTime lessDate);

    @Modifying
    @Transactional
    void deleteAllByCityId(long cityId);

    @Query("SELECT COUNT(table) FROM HourlyWeather table WHERE table.time < :lessDate")
    int countWithLessDate(LocalDateTime lessDate);

    @Query("SELECT COUNT(table) FROM HourlyWeather table WHERE table.time < :lessDate AND table.cityId=:cityId")
    int countWithLessDate(LocalDateTime lessDate, long cityId);

    @Query("SELECT COUNT(table) FROM HourlyWeather table WHERE table.time > :lessDate AND table.cityId= :cityId")
    int itemsWithLaterDate(LocalDateTime lessDate, Long cityId);



}
