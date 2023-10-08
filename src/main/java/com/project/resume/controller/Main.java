package com.project.resume.controller;

import com.project.resume.view.ResumeView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ResumeView resumeView = new ResumeView();
        resumeView.inputPersonInfo().inputEducationList().inputCareerList().inputSelfIntroduction();

        Resume resume = new Resume(resumeView.getPersonInfo(),
                                    resumeView.getEducationList(),
                                    resumeView.getCareerList(),
                                    resumeView.getSelfIntroduction()
                                   );

        resume.addPersonInfoToCell().addEducationInfoToCell().addCareerInfoToCell()
                                    .createExcelFile("resume01");


    }
}
