package com.bit_project.typeweatherwithoutgit.repository;

import com.bit_project.typeweatherwithoutgit.entity.DailyTemperature;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyTemperatureRepository extends JpaRepository<DailyTemperature, Long> {
    List<DailyTemperature> findByCityId(long cityId);

    @Query("SELECT COUNT(table) FROM DailyTemperature table WHERE table.date < :lessDate AND table.cityId=:cityId")
    int countWithLessDate(LocalDate lessDate, long cityId);

    @Modifying
    @Transactional
    void deleteAllByCityId(long cityId);
}
