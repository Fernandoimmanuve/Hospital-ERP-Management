package com.hospital.erp;


import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
public class LaboratoryTestServiceImpl
        implements LaboratoryTestService {




    private final LaboratoryTestRepository repository;





    public LaboratoryTestServiceImpl(
            LaboratoryTestRepository repository
    ) {


        this.repository = repository;

    }








    @Override
    public LaboratoryTest saveLaboratoryTest(
            LaboratoryTest laboratoryTest
    ) {


        return repository.save(
                laboratoryTest
        );

    }







    @Override
    public LaboratoryTest updateLaboratoryTest(
            LaboratoryTest laboratoryTest
    ) {


        return repository.save(
                laboratoryTest
        );

    }







    @Override
    public void deleteLaboratoryTest(
            Long id
    ) {


        repository.deleteById(id);

    }







    @Override
    public Optional<LaboratoryTest> getLaboratoryTestById(
            Long id
    ) {


        return repository.findById(id);

    }







    @Override
    public List<LaboratoryTest> getAllLaboratoryTests()
    {


        return repository.findAll();

    }







    @Override
    public Optional<LaboratoryTest> findByTestName(
            String testName
    ) {


        return repository
                .findByTestNameIgnoreCase(
                        testName
                );

    }







    @Override
    public List<LaboratoryTest> searchByTestName(
            String testName
    ) {


        return repository
                .findByTestNameContainingIgnoreCase(
                        testName
                );

    }







    @Override
    public List<LaboratoryTest> searchByCategory(
            String category
    ) {


        return repository
                .findByCategoryContainingIgnoreCase(
                        category
                );

    }







    @Override
    public List<LaboratoryTest> getActiveTests()
    {


        return repository
                .findByStatus(
                        "Active"
                );

    }







    @Override
    public List<LaboratoryTest> findByPriceRange(
            double minPrice,
            double maxPrice
    ) {


        return repository
                .findByPriceBetween(
                        minPrice,
                        maxPrice
                );

    }







    @Override
    public boolean existsByTestName(
            String testName
    ) {


        return repository
                .existsByTestNameIgnoreCase(
                        testName
                );

    }







    @Override
    public long getTotalTests()
    {


        return repository.count();

    }







    @Override
    public long getActiveTestCount()
    {


        return repository
                .findByStatus(
                        "Active"
                )
                .size();

    }



}