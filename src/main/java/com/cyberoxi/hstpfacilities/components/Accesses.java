package com.cyberoxi.hstpfacilities.components;

import com.cyberoxi.hstpfacilities.models.AccessLevel;
import com.cyberoxi.hstpfacilities.models.UrlMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohamad Zarei Maram
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 3/10/2020
 */
@Component
public class Accesses {

    public List<AccessLevel> getAccessLevels() {
        return new ArrayList<AccessLevel>() {{

            add(new AccessLevel("Super Admin", "SA", new String[]{"see_information", "add_information", "change_information", "manage_system"}, new ArrayList<UrlMethod>() {{
                //add(new UrlMethod("/**", new String[]{"GET", "POST", "DELETE"}));
            }}));

            add(new AccessLevel("Units", "C0", new String[]{"see_information"}, new ArrayList<UrlMethod>() {{
                add(new UrlMethod("/web/admins/**", new String[]{"GET"}));
                add(new UrlMethod("/web/establishments/unit/**", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/unit/**", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/unit/**", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/unit/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/units/report/{id}", new String[]{"GET"}));

            }}));

            add(new AccessLevel("Employees", "A0", new String[]{"see_information", "add_information", "change_information"}, new ArrayList<UrlMethod>() {{
                add(new UrlMethod("/web/admins/**", new String[]{"GET"}));
                add(new UrlMethod("/web/establishments/unit/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/establishments/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/facilities/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/unit/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/facilities/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/ideas/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/unit/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/ideas/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/payments", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/payments/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/unit/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/units/{id}", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/units/brief", new String[]{"GET"}));
                add(new UrlMethod("/web/units/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/units/report/{id}", new String[]{"GET"}));
            }}));

            add(new AccessLevel("Incubators", "A1", new String[]{"see_information", "add_information"}, new ArrayList<UrlMethod>() {{
                add(new UrlMethod("/web/admins/**", new String[]{"GET"}));
                add(new UrlMethod("/web/establishments/unit/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/establishments/**", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/unit/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/facilities/**", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/unit/**", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/ideas/**", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/unit/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units", new String[]{"GET", "POST"}));
                add(new UrlMethod("/web/units/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units/brief", new String[]{"GET"}));
                add(new UrlMethod("/web/units/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/units/report/{id}", new String[]{"GET"}));

            }}));

            add(new AccessLevel("Experts", "A2", new String[]{"see_information"}, new ArrayList<UrlMethod>() {{
                add(new UrlMethod("/web/admins/**", new String[]{"GET"}));
                add(new UrlMethod("/web/establishments/unit/**", new String[]{"GET"}));
                add(new UrlMethod("/web/establishments/**", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/unit/**", new String[]{"GET"}));
                add(new UrlMethod("/web/facilities/**", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/unit/**", new String[]{"GET"}));
                add(new UrlMethod("/web/ideas/**", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/payments/unit/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units", new String[]{"GET"}));
                add(new UrlMethod("/web/units/{id}", new String[]{"GET"}));
                add(new UrlMethod("/web/units/brief", new String[]{"GET"}));
                add(new UrlMethod("/web/units/fields", new String[]{"GET"}));
                add(new UrlMethod("/web/units/report/{id}", new String[]{"GET"}));
            }}));

        }};
    }

    public AccessLevel getAccessLevel(String role) {
        for (AccessLevel accessLevel : getAccessLevels()) {
            if (accessLevel.getRole().equals(role))
                return accessLevel;
        }
        return null;
    }
}
