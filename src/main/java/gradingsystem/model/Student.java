package gradingsystem.model;

import jakarta.persistence.*;

@Entity
public class Student extends User {
    private String firstName;
    private String lastName;

    @ManyToOne
    private StudentClass studentClass;
    @Column(unique = true)
    private String pesel;
    private String dateOfBirth;
    private String city;
    private String postalCode;
    private String streetAndNumber;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public StudentClass getClassName() {
        return studentClass;
    }

    public void setClassName(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}