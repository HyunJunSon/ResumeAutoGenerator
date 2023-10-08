package com.project.resume.model;

public class Education {

    private int graduationYear;
    private String schoolName;
    private String major;
    private String graduationStatus;

    public Education() {
    }

    public Education(String inputData) {
        String[] arr = inputData.split("\\s+");
        this.graduationYear = Integer.parseInt(arr[0]);
        this.schoolName = arr[1];
        this.major = arr[2];
        this.graduationStatus = arr[3];
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(String graduationStatus) {
        this.graduationStatus = graduationStatus;
    }

    @Override
    public String toString() {
        return "Education{" +
                "graduationYear=" + graduationYear +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", graduationStatus='" + graduationStatus + '\'' +
                '}';
    }
}
