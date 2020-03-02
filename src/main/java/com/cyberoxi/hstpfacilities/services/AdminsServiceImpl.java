package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;
import com.cyberoxi.hstpfacilities.models.responses.AdminReport;
import com.cyberoxi.hstpfacilities.repositories.AdminsRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private AuditorService auditorService;
    private AdminsRepository adminsRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminsServiceImpl(UnitsRepository unitsRepository, FacilitiesRepository facilitiesRepository, EstablishmentsRepository establishmentsRepository, AuditorService auditorService, AdminsRepository adminsRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.unitsRepository = unitsRepository;
        this.facilitiesRepository = facilitiesRepository;
        this.establishmentsRepository = establishmentsRepository;
        this.auditorService = auditorService;
        this.adminsRepository = adminsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AdminInformation getAdminInformation() {
        List<AdminReport> adminReports = new ArrayList<>();
        Iterable<Unit> units = unitsRepository.findAll();
        long facilitiesArrears = 0;
        long establishmentArrears = 0;
        for (Unit unit : units) {
            long debtFacilityRemained = auditorService.facilitiesDebt(unit.getFacilities());
            long debtEstablishmentRemained = auditorService.establishmentsDebt(unit.getEstablishments());
            facilitiesArrears += debtFacilityRemained;
            establishmentArrears += debtEstablishmentRemained;
            adminReports.add(new AdminReport(unit.getId(), unit.getName(), unit.getBranch(), debtFacilityRemained, debtEstablishmentRemained));
        }
        return new AdminInformation(unitsRepository.count(), facilitiesRepository.count(), establishmentsRepository.count(), facilitiesArrears + establishmentArrears, establishmentArrears, facilitiesArrears, adminReports);
    }

    @Override
    public Admin save(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        return adminsRepository.save(admin);
    }
}
