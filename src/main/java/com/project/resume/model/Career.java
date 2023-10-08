package com.project.resume.model;

public class Career {

    private String workPeriod;
    private String companyName;
    private String jobTitle;
    private int employmentYears;

    public Career() {
    }

    public Career(String inputData) {
        String[] arr = inputData.split("\\s+");
        this.workPeriod = arr[0];
        this.companyName = arr[1];
        this.jobTitle = arr[2];
        this.employmentYears = Integer.parseInt(arr[3]);
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        this.workPeriod = workPeriod;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getEmploymentYears() {
        return employmentYears;
    }

    public void setEmploymentYears(int employmentYears) {
        this.employmentYears = employmentYears;
    }

    @Override
    public String toString() {
        return "Career{" +
                "workPeriod='" + workPeriod + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", employmentYears=" + employmentYears +
                '}';
    }
}

