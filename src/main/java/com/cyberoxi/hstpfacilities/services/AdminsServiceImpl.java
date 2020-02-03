package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;
import com.cyberoxi.hstpfacilities.models.responses.AdminReport;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/9/20
 */
@Service
public class AdminsServiceImpl implements AdminsService {

    private UnitsRepository unitsRepository;
    private FacilitiesRepository facilitiesRepository;
    private EstablishmentsRepository establishmentsRepository;

    @Autowired
    public AdminsServiceImpl(UnitsRepository unitsRepository, FacilitiesRepository facilitiesRepository, EstablishmentsRepository establishmentsRepository) {
        this.unitsRepository = unitsRepository;
        this.facilitiesRepository = facilitiesRepository;
        this.establishmentsRepository = establishmentsRepository;
    }

    @Override
    public AdminInformation getAdminInformation() {
        List<AdminReport> adminReports = new ArrayList<>();
        adminReports.add(new AdminReport(0, "", (byte) 0, 0, 0));
        return new AdminInformation(unitsRepository.count(), facilitiesRepository.count(), establishmentsRepository.count(), 0, 0, 0, adminReports);
    }
}
