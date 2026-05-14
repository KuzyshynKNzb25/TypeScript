package com.bit_project.typeweatherwithoutgit.repository;

import com.bit_project.typeweatherwithoutgit.entity.WeatherByCity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherByCityRepository extends JpaRepository<WeatherByCity, Long> {
    WeatherByCity findByName(String name);

    @Query("SELECT table FROM WeatherByCity table WHERE table.name LIKE :cityName% ORDER BY table.numberOfRequests DESC ")
    List<WeatherByCity> offerByName(@Param("cityName") String name, Pageable pageable);

    @Query("SELECT table FROM WeatherByCity table WHERE table.ukrainianName LIKE :ukrainianName% ORDER BY table.numberOfRequests DESC ")
    List<WeatherByCity> offerByUkrainianName(@Param("ukrainianName") String ukrainianName, Pageable pageable);

    @Query("SELECT table.ukrainianName FROM WeatherByCity table WHERE table.name=:name")
    List<String> findUkrainianNameByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE WeatherByCity table SET table.numberOfRequests = table.numberOfRequests+1 WHERE table.id=:id")
    void incrementOrderById(@Param("id") Long id);

}
