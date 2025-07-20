package com.main.m_cogdell_libraryassignment4;

public class Authors {

    private static Integer authorID;
    private static String fullName;
    private static String nationality;
    private static Integer birthYear;

    // Constructor 1
    public Authors(Integer authorID, String fullName, String nationality, Integer birthYear) {
        this.authorID = authorID;
        this.fullName = fullName;
        this.nationality = nationality;
        this.birthYear = birthYear;
    }

    // Constructor 2
    public Authors() {
        //this.fullName = fullName;
    }

    // Getters & Setters ============================================================

    public Integer getAuthorID() {
        return authorID;
    }
    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

//==============================================================================================



}
