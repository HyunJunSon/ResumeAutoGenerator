package com.project.resume.controller;

import com.project.resume.view.ResumeView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ResumeView resumeView = new ResumeView();
        Resume resume = new Resume(resumeView.getPersonInfoList(), resumeView.getEducationList(), resumeView.getCareerList());

        resume.addPersonInfoToCell().addEducationInfoToCell().addCareerInfoToCell()
                .createExcelFile("resume01");


    }
}
