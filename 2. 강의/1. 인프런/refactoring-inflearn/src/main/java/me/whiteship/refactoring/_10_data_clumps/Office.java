package me.whiteship.refactoring._10_data_clumps;

public class Office {

    private String location;
    private PhoneNumber phoneNumber;

    public Office(String location, PhoneNumber phoneNumber) {
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public String officePhoneNumber() {
        return this.phoneNumber.toString();
    }

    public String getOfficeAreCode() {
        return this.phoneNumber.getAreaCode();
    }

    public void setOfficeAreCode(String officeAreCode) {
        this.phoneNumber.setAreaCode(officeAreCode);
    }

    public String getOfficeNumber() {
        return this.phoneNumber.getNumber();
    }

    public void setOfficeNumber(String officeNumber) {
        this.phoneNumber.setNumber(officeNumber);
    }
}
