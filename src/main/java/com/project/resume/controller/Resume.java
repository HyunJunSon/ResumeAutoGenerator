package com.project.resume.controller;

import com.project.resume.model.Career;
import com.project.resume.model.Education;
import com.project.resume.model.PersonInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Resume extends XSSFWorkbook{
    Sheet sheet_resume;
    Sheet sheet_selfIntroduction;
    private PersonInfo personInfo;
    private List<Education> educationList;
    private List<Career> careerList;
    private StringBuilder selfIntroduction;

    Resume(PersonInfo personInfo,List<Education> educationList, List<Career> careerList,StringBuilder selfIntroduction){
        this.sheet_resume = createSheet("이력서");
        this.personInfo = personInfo;
        this.educationList = educationList;
        this.careerList = careerList;
        this.sheet_selfIntroduction = createSheet("자기소개서");
        this.selfIntroduction = selfIntroduction;
    }


    public void createExcelFile(String fileName) throws IOException {
        String filename = fileName + ".xlsx";
        FileOutputStream outputStream = new FileOutputStream(new File(filename));

        try {
            this.write(outputStream);
            this.close();
            System.out.println("엑셀 파일이 저장되었습니다." + filename);
        } catch (IOException e) {
            System.out.println("엑셀 파일 저장에 실패했습니다.");
            e.printStackTrace();
        }
    }
    //헤더생성
    private void createHeader(String[] headerElement, int row){
        Row headerRow = sheet_resume.createRow(row);
        String[] header = headerElement ;
        for(int i=0; i<header.length; i++){
            headerRow.createCell(i).setCellValue(header[i]);
        }
    }
    public Resume addPersonInfoToCell(){
        this.createHeader(new String[]{"사진", "이름", "이메일", "주소", "전화번호", "생년월일"}, 1);

        Row row = sheet_resume.createRow( 2);
        row.createCell(0).setCellValue(personInfo.getPhoto());
        row.createCell(1).setCellValue(personInfo.getName());
        row.createCell(2).setCellValue(personInfo.getEmail());
        row.createCell(3).setCellValue(personInfo.getAddress());
        row.createCell(4).setCellValue(personInfo.getPhoneNumber());
        row.createCell(5).setCellValue(personInfo.getBirthDate());

        return this;
    }

    public Resume addEducationInfoToCell(){
        createHeader(new String[]{"졸업연도","학교명","전공","졸업여부"}, 3);
        for (int i = 0; i < educationList.size(); i++) {
            Education education = educationList.get(i);

            Row row = sheet_resume.createRow(4+i);
            row.createCell(0).setCellValue(education.getGraduationYear());
            row.createCell(1).setCellValue(education.getSchoolName());
            row.createCell(2).setCellValue(education.getMajor());
            row.createCell(3).setCellValue(education.getGraduationStatus());
        }
        return this;
    }

    public Resume addCareerInfoToCell(){
        createHeader(new String[]{"근무기간","근무처","담당업무","근속년수"}, educationList.size()+4);

        for (int i = 0; i < careerList.size(); i++) {
            Career career = careerList.get(i);

            Row row = sheet_resume.createRow(educationList.size()+5+i);
            row.createCell(0).setCellValue(career.getWorkPeriod());
            row.createCell(1).setCellValue(career.getCompanyName());
            row.createCell(2).setCellValue(career.getJobTitle());
            row.createCell(3).setCellValue(career.getEmploymentYears());
        }
        return this;
    }

    public Resume Add_SelfIntroductionSheet() {
        Row row = sheet_selfIntroduction.createRow( 1);
        row.createCell(0).setCellValue(selfIntroduction.toString());
        return this;
    }
}

