package utilites;

import com.github.javafaker.Faker;

public class dataGenerator {
    private static final Faker fake = new Faker();
    public static String getFullName() {
        return fake.name().fullName();
    }
    public static String getFirstName() {
        return fake.name().firstName();
    }
    public static String getLastName() {
        return fake.name().lastName();
    }
    public static String getCompany() {
        return fake.name().name();
    }
    public static String getFirstAddress() {
        return fake.address().fullAddress();
    }
    public static String getSecondAddress() {
        return fake.address().secondaryAddress();
    }
    public static String getState() {
        return fake.country().capital();
    }
    public static String getCountry() {
        return fake.country().name();
    }
    public static String getZip() {
        return fake.code().asin();
    }
    public static String getPhoneNum() {
        return "010"+fake.number().numberBetween(1000000,9999999);
    }

    public static String getEmail() {
        return fake.internet().safeEmailAddress();
    }

    public static String getPassword() {
        return fake.internet().password();
    }
}
