package com.project.resume.view;

import com.project.resume.model.Career;
import com.project.resume.model.Education;
import com.project.resume.model.PersonInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResumeView {
    private Scanner sc = new Scanner(System.in);
    private PersonInfo personInfo;
    private List<Education> educationList;
    private List<Career> careerList;
    private StringBuilder selfIntroduction;
    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<Career> getCareerList() {
        return careerList;
    }

    public StringBuilder getSelfIntroduction() {
        return selfIntroduction;
    }

    public ResumeView() {
        this.educationList = new ArrayList<>();
        this.careerList = new ArrayList<>();
    }

    private String getStringData(String data){
        System.out.print( data+"(을/를) 입력하세요:");
        if (data.equals("학력 정보"))
            System.out.print("(종료는 q)\n졸업년도 학교명 전공 졸업여부 : ");
        if (data.equals("경력 정보"))
            System.out.print("(종료는 q)\n근무기간 근무처 담당업무 근속년수 : ");
        return sc.nextLine();
    }
    private int getIntData(String data){
        System.out.print( data+"(을/를) 입력하세요:");
        int res = sc.nextInt();
        sc.nextLine(); // 개행문자 제거
        return res;
    }
    public ResumeView inputPersonInfo() {
        String photo = getStringData("사진파일명");
        String name = getStringData("이름");
        String email = getStringData("이메일");
        String address = getStringData("주소");
        String phoneNumber = getStringData("전화번호");
        String birthDate = getStringData("생년월일");

        this.personInfo = new PersonInfo(photo, name, email, address, phoneNumber, birthDate);

        return this;
    }
    public ResumeView inputEducationList() {
        while(true){
            String inputData = getStringData("학력 정보");
            if (inputData.equals("q"))
                break;
            educationList.add(new Education(inputData));
        }
        return  this;
    }
    public ResumeView inputCareerList(){
        while(true){
            String inputData = getStringData("경력 정보");
            if (inputData.equals("q"))
                break;
            careerList.add(new Career(inputData));
        }
        return this;

    }
    public ResumeView inputSelfIntroduction() {
        System.out.println("자기소개서를 입력하세요. 여러 줄을 입력하려면 빈 줄을 입력하세요.");

        String line;
        StringBuilder selfIntroduction = new StringBuilder();
        while (true) {
            line = sc.nextLine();

            if (line.isEmpty()) {
                break;
            }
            selfIntroduction.append(line).append("\n");
        }
        this.selfIntroduction = selfIntroduction;
        return this;
    }

}
