package com.example.project_akhir;

public class Requests {
    public String id, firstname, lastname, tempatlahir, dateOB,
            address, phoneno, email, major, gender, time, day,
            locate, note,key;
    public Requests(){
    }
    public Requests(String id, String firstname, String lastname, String tempatlahir, String dateOB, String address, String phoneno, String email, String major, String gender, String time, String day, String locate, String note) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tempatlahir = tempatlahir;
        this.dateOB = dateOB;
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
        this.major = major;
        this.gender = gender;
        this.time = time;
        this.day = day;
        this.locate = locate;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTempatlahir() {
        return tempatlahir;
    }

    public void setTempatlahir(String tempatlahir) {
        this.tempatlahir = tempatlahir;
    }

    public String getDateOB() {
        return dateOB;
    }

    public void setDateOB(String dateOB) {
        this.dateOB = dateOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", tempatlahir='" + tempatlahir + '\'' +
                ", dateOB='" + dateOB + '\'' +
                ", address='" + address + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", gender='" + gender + '\'' +
                ", time='" + time + '\'' +
                ", day='" + day + '\'' +
                ", locate='" + locate + '\'' +
                ", note='" + note + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
