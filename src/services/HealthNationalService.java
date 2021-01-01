package services;

import data.*;
import exceptions.*;

import java.net.ConnectException;
import java.util.List;

/*
    External service for managing and storing ePrescriptions from population
 */
public interface HealthNationalService {
    MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException;

    List<ProductSpecification> getProductsByKW(String keyword) throws AnyKeyWordMedicineException, ConnectException;

    ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException;

    MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescriptionException;
}
