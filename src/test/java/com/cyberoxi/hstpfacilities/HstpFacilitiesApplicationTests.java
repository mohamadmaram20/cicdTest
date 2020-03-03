package com.cyberoxi.hstpfacilities;

import com.cyberoxi.hstpfacilities.services.EstablishmentService;
import com.cyberoxi.hstpfacilities.services.PaymentService;

import com.cyberoxi.hstpfacilities.services.AuditorService;
import com.cyberoxi.hstpfacilities.services.UnitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HstpFacilitiesApplicationTests {

    private UnitService unitService;
    private EstablishmentService establishmentService;
    private PaymentService paymentService;
    private AuditorService auditorService;

    @Autowired
    public HstpFacilitiesApplicationTests(UnitService unitService, EstablishmentService establishmentService, PaymentService paymentService, AuditorService auditorService) {
        this.unitService = unitService;
        this.establishmentService = establishmentService;
        this.paymentService = paymentService;
        this.auditorService = auditorService;
    }

    @Test
    void contextLoads() {
    }

    /*@Test
    void insert() throws ParseException {
        Establishment establishment = new Establishment();
        establishment.setContractNumber("12345");
        establishment.setPlace("");

        establishment.setContractDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-02"));
        establishment.setContractStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-06-01"));
        establishment.setContractEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-01"));
        establishment.setNumberOfMonth(12);
        establishment.setNumberOfDay(365);
        establishment.setFinalContractMonthlyAmount(100000000);

        Unit unit = new Unit();
        unit.setUsername("test1");
        unit.setPassword("test");
        unit.setRegistrationDate(new Date());
        unit.setName("test1");
        unit.setType((byte) 0);
        unit.setBranch((byte) 0);
        unit.setReceptionType((byte) 0);
        unit.setRegistrationNumber("1234");
        unit.setNationalId("1234");
        unit.setPerson(new Person());
        unit.setPhoneNumber("");
        unit.setWebsite("");
        unit.setFax("");
        unit.setPostalAddress("");
        unit.setEmail("");
        unit.setAvatar("");
        unit.setSignatureOwners(Lists.newArrayList());
        unit.setTeammates(Lists.newArrayList());
        unit.setEstablishments(Lists.newArrayList(establishment));
        unit.setFacilities(Lists.newArrayList());
        unit.setReceptionDates(Lists.newArrayList());
        unit.setIdeas(Lists.newArrayList());

        Unit unitSaved = unitService.saveUnit(unit);

        Payment payment = new Payment();
        payment.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-15"));
        payment.setAmount(100000000);
        payment.setType((byte) 0);
        payment.setReferenceId("1234");
        payment.setContractId(unitSaved.getEstablishments().get(0).getId());
        payment.setContractType('e');
        payment.setUnitId(unitSaved.getId());
        payment.setTrackingCode("");

        paymentService.savePayment(payment);
    }

    @Test
    void auditor() {
        assertThat(auditorService.establishmentDebt(establishmentService.getEstablishment(24))).isEqualTo(700000000);
    }*/

}
