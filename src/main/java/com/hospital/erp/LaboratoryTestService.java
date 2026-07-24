package com.hospital.erp;

import java.util.List;
import java.util.Optional;



public interface LaboratoryTestService {



    // Save Test

    LaboratoryTest saveLaboratoryTest(
            LaboratoryTest laboratoryTest
    );





    // Update Test

    LaboratoryTest updateLaboratoryTest(
            LaboratoryTest laboratoryTest
    );





    // Delete Test

    void deleteLaboratoryTest(
            Long id
    );





    // Find By ID

    Optional<LaboratoryTest> getLaboratoryTestById(
            Long id
    );





    // Get All Tests

    List<LaboratoryTest> getAllLaboratoryTests();





    // Find Test By Name

    Optional<LaboratoryTest> findByTestName(
            String testName
    );





    // Search Tests

    List<LaboratoryTest> searchByTestName(
            String testName
    );





    // Category Search

    List<LaboratoryTest> searchByCategory(
            String category
    );





    // Active Tests

    List<LaboratoryTest> getActiveTests();





    // Price Range Search

    List<LaboratoryTest> findByPriceRange(
            double minPrice,
            double maxPrice
    );





    // Duplicate Check

    boolean existsByTestName(
            String testName
    );





    // Dashboard Count

    long getTotalTests();



    long getActiveTestCount();



}