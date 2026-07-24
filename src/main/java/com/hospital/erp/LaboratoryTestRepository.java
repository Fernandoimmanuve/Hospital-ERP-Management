package com.hospital.erp;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LaboratoryTestRepository
        extends JpaRepository<LaboratoryTest, Long> {



    // =====================================
    // FIND TEST BY NAME
    // =====================================

    Optional<LaboratoryTest> findByTestNameIgnoreCase(
            String testName
    );





    // =====================================
    // SEARCH TEST NAME
    // =====================================

    List<LaboratoryTest> 
    findByTestNameContainingIgnoreCase(
            String testName
    );





    // =====================================
    // FIND TEST BY CATEGORY
    // =====================================

    List<LaboratoryTest> findByCategory(
            String category
    );





    // =====================================
    // SEARCH CATEGORY
    // =====================================

    List<LaboratoryTest>
    findByCategoryContainingIgnoreCase(
            String category
    );





    // =====================================
    // ACTIVE TEST LIST
    // =====================================

    List<LaboratoryTest> findByStatus(
            String status
    );





    // =====================================
    // PRICE FILTER
    // =====================================

    List<LaboratoryTest> 
    findByPriceBetween(
            double minPrice,
            double maxPrice
    );





    // =====================================
    // CHECK DUPLICATE TEST NAME
    // =====================================

    boolean existsByTestNameIgnoreCase(
            String testName
    );



}