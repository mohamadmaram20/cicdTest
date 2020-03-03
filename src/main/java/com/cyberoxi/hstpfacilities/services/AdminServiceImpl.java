package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;
import com.cyberoxi.hstpfacilities.models.responses.AdminReport;
import com.cyberoxi.hstpfacilities.repositories.AdminRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilityRepository;
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
public class AdminServiceImpl implements AdminService {

    private UnitRepository unitRepository;
    private FacilityRepository facilityRepository;
    private EstablishmentRepository establishmentRepository;
    private AuditorService auditorService;
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminServiceImpl(UnitRepository unitRepository, FacilityRepository facilityRepository, EstablishmentRepository establishmentRepository, AuditorService auditorService, AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.unitRepository = unitRepository;
        this.facilityRepository = facilityRepository;
        this.establishmentRepository = establishmentRepository;
        this.auditorService = auditorService;
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AdminInformation getAdminInformation() {
        List<AdminReport> adminReports = new ArrayList<>();
        Iterable<Unit> units = unitRepository.findAll();
        long facilitiesArrears = 0;
        long establishmentArrears = 0;
        for (Unit unit : units) {
            long debtFacilityRemained = auditorService.facilitiesDebt(unit.getFacilities());
            long debtEstablishmentRemained = auditorService.establishmentsDebt(unit.getEstablishments());
            facilitiesArrears += debtFacilityRemained;
            establishmentArrears += debtEstablishmentRemained;
            adminReports.add(new AdminReport(unit.getId(), unit.getName(), unit.getBranch(), debtFacilityRemained, debtEstablishmentRemained));
        }
        return new AdminInformation(unitRepository.count(), facilityRepository.count(), establishmentRepository.count(), facilitiesArrears + establishmentArrears, establishmentArrears, facilitiesArrears, adminReports);
    }

    @Override
    public Admin save(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}
