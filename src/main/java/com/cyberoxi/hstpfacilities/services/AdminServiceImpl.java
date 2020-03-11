package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.models.User;
import com.cyberoxi.hstpfacilities.models.Credential;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;
import com.cyberoxi.hstpfacilities.models.responses.AdminReport;
import com.cyberoxi.hstpfacilities.repositories.*;
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
    private UserRepository userRepository;
    private CredentialRepository credentialRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminServiceImpl(UnitRepository unitRepository, FacilityRepository facilityRepository, EstablishmentRepository establishmentRepository, AuditorService auditorService, UserRepository userRepository, CredentialRepository credentialRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.unitRepository = unitRepository;
        this.facilityRepository = facilityRepository;
        this.establishmentRepository = establishmentRepository;
        this.auditorService = auditorService;
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
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
    public User save(User user) {
        if (!(user.getCredential().getRole().startsWith("A") || user.getCredential().getRole().startsWith("S")))
            throw new BadRequestException("Role for user must start with A");
        Credential credential = user.getCredential();
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
        Credential savedCredential = credentialRepository.save(credential);
        user.setCredential(savedCredential);
        return userRepository.save(user);
    }
}
