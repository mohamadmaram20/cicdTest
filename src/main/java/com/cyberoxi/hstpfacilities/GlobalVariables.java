package com.cyberoxi.hstpfacilities;

import com.cyberoxi.hstpfacilities.models.responses.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
public class GlobalVariables {

    public static final String WEB_ROUTE = "/web";
    public static final String MANAGE_ROUTE = "/mng";

    public static final int TRACKING_CODE_LENGTH = 8;

    public static Map<String, List<Field>> UNIT_FIELDS;

    static {
        UNIT_FIELDS = new HashMap<>();
        List<Field> branches = new ArrayList<>();
        branches.add(new Field((byte) 1, "پذیرش شده در پارک"));
        branches.add(new Field((byte) 2, "مرکز رشد همدان"));
        branches.add(new Field((byte) 3, "مرکز رشد ملایر"));
        branches.add(new Field((byte) 4, "مرکز رشد لالجین"));
        branches.add(new Field((byte) 5, "مرکز رشد کبودرآهنگ"));
        branches.add(new Field((byte) 6, "مرکز رشد رزن"));
        branches.add(new Field((byte) 7, "مرکز رشد کشاورزی"));
        branches.add(new Field((byte) 8, "مرکز نوآوری اسدآباد"));
        branches.add(new Field((byte) 9, "مرکز نوآوری نهاوند"));
        branches.add(new Field((byte) 10, "مرکز نوآوری تویسرکان"));
        UNIT_FIELDS.put("branch", branches);

        List<Field> receptionTypes = new ArrayList<>();
        receptionTypes.add(new Field((byte) 1, "کانون"));
        receptionTypes.add(new Field((byte) 2, "پیش رشد - جدید"));
        receptionTypes.add(new Field((byte) 3, "پیش رشد - تبدیل وضعیت از کانون"));
        receptionTypes.add(new Field((byte) 4, "رشد - جدید"));
        receptionTypes.add(new Field((byte) 5, "رشد - تبدیل وضعیت از دوره رشد مقدماتی"));
        receptionTypes.add(new Field((byte) 6, "موسسه - جدید"));
        receptionTypes.add(new Field((byte) 7, "موسسه - تبدیل وضعیت از دوره رشد "));
        UNIT_FIELDS.put("receptionType", receptionTypes);

        List<Field> receptionDateTypes = new ArrayList<>();
        receptionDateTypes.add(new Field((byte) 1, "کانون"));
        receptionDateTypes.add(new Field((byte) 2, "پیش رشد"));
        receptionDateTypes.add(new Field((byte) 3, "رشد"));
        receptionDateTypes.add(new Field((byte) 4, "موسسه"));
        UNIT_FIELDS.put("receptionDateTypes", receptionDateTypes);

        List<Field> types = new ArrayList<>();
        types.add(new Field((byte) 1, "شخص حقیقی"));
        types.add(new Field((byte) 2, "مسئولیت محدود"));
        types.add(new Field((byte) 3, "سهامی خاص"));
        types.add(new Field((byte) 4, "تعاونی"));
        types.add(new Field((byte) 5, "سایر"));
        UNIT_FIELDS.put("types", types);
    }

    public static Map<String, List<Field>> IDEA_FIELDS;

    static {
        IDEA_FIELDS = new HashMap<>();
        List<Field> ideaFields = new ArrayList<>();
        ideaFields.add(new Field((byte) 1, "فناوری اطلاعات و ارتباطات"));
        ideaFields.add(new Field((byte) 2, "برق"));
        ideaFields.add(new Field((byte) 3, "الکترونیک قدرت و مخابرات و سخت‌افزار"));
        ideaFields.add(new Field((byte) 4, "مکانیک و طراحی صنعتی"));
        ideaFields.add(new Field((byte) 5, "کشاورزی، منابع طبیعی، صنایع غذایی و محیط زیست"));
        ideaFields.add(new Field((byte) 6, "پزشکی و سلامت"));
        ideaFields.add(new Field((byte) 7, "شیمی و مواد"));
        ideaFields.add(new Field((byte) 8, "عمران و معماری و صنعت ساختمان"));
        ideaFields.add(new Field((byte) 9, "سایر"));
        IDEA_FIELDS.put("fields", ideaFields);
    }

    public static Map<String, List<Field>> FACILITY_FIELDS;

    static {
        FACILITY_FIELDS = new HashMap<>();
        List<Field> changeType = new ArrayList<>();
        changeType.add(new Field((byte) 1, "استمهال"));
        changeType.add(new Field((byte) 2, "افزایش سقف"));
        changeType.add(new Field((byte) 3, "عدم تعلق فاز بعدی"));
        FACILITY_FIELDS.put("changeType", changeType);
    }
}
