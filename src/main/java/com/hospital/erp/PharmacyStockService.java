package com.hospital.erp;






public interface PharmacyStockService {



    boolean issueMedicine(
            String medicineName,
            int quantity,
            Long prescriptionId,
            String pharmacistName
    );



}