package com.project.resume.controller;

import com.project.resume.model.Career;
import com.project.resume.model.Education;
import com.project.resume.model.PersonInfo;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        String[] header = headerElement;

        // 폰트 생성
        Font font = this.createFont();
        font.setBold(true);  // 볼드체 설정

        // 셀 스타일 생성 및 폰트 적용
        CellStyle style = this.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        for(int i=0; i<header.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(style);  // 스타일 적용
            sheet_resume.autoSizeColumn(i);
        }
//        font.setBold(false);
    }

    private void insertPhoto(Row row, String fileName) {
        try {
            InputStream imageStream = new FileInputStream(fileName);
            byte[] bytes = IOUtils.toByteArray(imageStream);
            int pictureIndex = sheet_resume.getWorkbook().addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

            CreationHelper creationHelper = sheet_resume.getWorkbook().getCreationHelper();

            // 그림을 추가할 셀을 선택합니다. 여기서는 A2셀을 선택합니다.
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 0, 1, (short) 1, 2);

            // 그림을 그림 객체에 추가합니다.
            XSSFDrawing drawing = (XSSFDrawing) sheet_resume.createDrawingPatriarch();
            XSSFPicture picture = drawing.createPicture(anchor, pictureIndex);

            // 셀 크기를 조절합니다.
            sheet_resume.setColumnWidth(0, (int) (40 * 256)); // 셀 가로 크기 조절
            row.setHeight((short) (40 * 40)); // 셀 세로 크기 조절 (40 픽셀을 1/20으로 변환)

            // 그림 크기를 셀 크기에 맞게 조절합니다.
            picture.resize(1.0, 1.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public Resume addPersonInfoToCell() {
        this.createHeader(new String[]{"사진", "이름", "이메일", "주소", "전화번호", "생년월일"}, 0);

        Row row = sheet_resume.createRow(1);

        // 이미지가 삽입된 열의 너비를 고정
        sheet_resume.setColumnWidth(0, (int) (40 * 256)); // 가로 크기

        insertPhoto(row, personInfo.getPhoto());
        row.createCell(0).setCellValue(personInfo.getPhoto());
        row.createCell(1).setCellValue(personInfo.getName());
        row.createCell(2).setCellValue(personInfo.getEmail());
        row.createCell(3).setCellValue(personInfo.getAddress());
        row.createCell(4).setCellValue(personInfo.getPhoneNumber());
        row.createCell(5).setCellValue(personInfo.getBirthDate());

        // 다른 열은 자동 크기 조정
        for (int k = 4; k < 6; k++) {
            sheet_resume.autoSizeColumn(k);
        }

        return this;
    }

    public Resume addEducationInfoToCell(){
        createHeader(new String[]{"졸업연도","학교명","전공","졸업여부"}, 2);

        if(educationList.isEmpty())
            return this;

        for (int i = 0; i < educationList.size(); i++) {
            Education education = educationList.get(i);

            Row row = sheet_resume.createRow(3+i);
            row.createCell(0).setCellValue(education.getGraduationYear());
            row.createCell(1).setCellValue(education.getSchoolName());
            row.createCell(2).setCellValue(education.getMajor());
            row.createCell(3).setCellValue(education.getGraduationStatus());
        }
        resizingCell(educationList);
        return this;
    }

    private void resizingCell(List<?> list){
        for(int k = 0 ; k < list.size() ; k++){
            sheet_resume.autoSizeColumn(k);
            sheet_resume.setColumnWidth(k, (sheet_resume.getColumnWidth(k))+1024); //너비 더 넓게
        }
    }

    public Resume addCareerInfoToCell(){
        createHeader(new String[]{"근무기간","근무처","담당업무","근속년수"}, educationList.size()+3);

        if (careerList.isEmpty())
            return this;

        for (int i = 0; i < careerList.size(); i++) {
            Career career = careerList.get(i);

            Row row = sheet_resume.createRow(educationList.size()+4+i);
            row.createCell(0).setCellValue(career.getWorkPeriod());
            row.createCell(1).setCellValue(career.getCompanyName());
            row.createCell(2).setCellValue(career.getJobTitle());
            row.createCell(3).setCellValue(career.getEmploymentYears());
        }
        resizingCell(careerList);
        return this;
    }

    public Resume add_SelfIntroductionSheet() {

        // 자기소개서 sheet의 첫번째 행을 가져옵니다.
        Cell cell = sheet_selfIntroduction.createRow(0).createCell(0);
        cell.setCellValue(selfIntroduction.toString());

        // column의 너비를 데이터 크기에 맞게 조절합니다.
        sheet_selfIntroduction.autoSizeColumn(0);

        return this;
    }

}

