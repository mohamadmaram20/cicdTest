package com.cyberoxi.hstpfacilities.controllers;

import com.cyberoxi.hstpfacilities.models.Person;
import com.cyberoxi.hstpfacilities.models.ReceptionDate;
import com.cyberoxi.hstpfacilities.models.Unit;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 2/12/20
 */
public class UnitControllerTest extends AbstractControllersTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Ignore
    public void getUnitsList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/web/units").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Unit[] unitsList = super.mapFromJson(content, Unit[].class);
        assertTrue(unitsList.length > 0);
    }

    @Test
    @Ignore
    public void createUnit() throws Exception {
        Unit unit = new Unit();
        unit.setUsername("14005685905");
        unit.setPassword("4052039051");
        unit.setRegistrationDate(new Date());
        unit.setName("آترین آذین فن\u200Cآور");
        unit.setType((byte) 2);
        unit.setBranch((byte) 1);
        unit.setReceptionType((byte) 7);
        unit.setRegistrationNumber("12341");
        unit.setNationalId("1234");
        Person ceo = new Person();
        ceo.setFirstName("مجتبی");
        ceo.setLastName("اسماعیلی");
        ceo.setNationalCode("4052039051");
        ceo.setPhoneNumber("09188185797");
        unit.setPerson(ceo);
        unit.setPhoneNumber("08132569335");
        unit.setWebsite("cyberoxi.com");
        unit.setFax("08132569335");
        unit.setPostalAddress("همدان؛ بلوار سردار شهید همدانی؛ پارک علم و فناوری همدان");
        unit.setEmail("info@cyberoxi.com");
        unit.setAvatar("");
        unit.setSignatureOwners(Arrays.asList(ceo));
        Person teammate = new Person();
        teammate.setFirstName("محمدمهدی");
        teammate.setLastName("کهول");
        teammate.setNationalCode("");
        teammate.setPhoneNumber("09188118232");
        unit.setTeammates(Arrays.asList(teammate));
        ReceptionDate receptionDate = new ReceptionDate();
        receptionDate.setDate(new Date());
        receptionDate.setType((byte) 4);
        unit.setReceptionDates(Arrays.asList(receptionDate));

        String inputJson = super.mapToJson(unit);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/web/units").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //assertEquals(content, unit);
    }
}
