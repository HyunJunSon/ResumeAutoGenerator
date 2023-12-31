# ResumeAutoGenerator
이력서를 자동으로 생성하는 프로그램

# src는 master branch에 있습니다.

## 목차

1. 프로그램 개요
2. [구현할 기능 목록과 각 기능의 내용](#구현할-기능-목록과-각-기능의-내용)
3. [입력과 출력 내용에 대한 설명](#입력과-출력-내용에-대한-설명)
4. [개발 도구](#개발-도구)
5. [그 외 상세한 내용들](#그-외-상세한-내용들)

## 프로그램 개요

이 프로그램은 사용자의 입력 정보를 바탕으로 이력서를 자동으로 생성하는 프로그램입니다. 이력서를 작성하는 데 필요한 기본적인 정보를 입력하면, 프로그램이 이를 바탕으로 이력서를 생성합니다.

## 구현할 기능 목록과 각 기능의 내용

* **기본 정보 입력:** 이름, 나이, 연락처, 이메일, 주소 등과 같은 기본 정보를 입력합니다.
* **학력 정보 입력:** 학력, 전공, 졸업년도 등과 같은 학력 정보를 입력합니다.
* **경력 정보 입력:** 근무 회사, 직책, 근무 기간 등과 같은 경력 정보를 입력합니다.
* **기술 스킬 입력:** 보유한 기술 스킬을 입력합니다.
* **이력서 생성:** 입력한 정보를 바탕으로 이력서를 생성합니다.

## 입력과 출력 내용에 대한 설명

* **입력:**
    * 기본 정보: 이름, 나이, 연락처, 이메일, 주소
    * 학력 정보: 학력, 전공, 졸업년도
    * 경력 정보: 근무 회사, 직책, 근무 기간
    * 기술 스킬: 보유한 기술 스킬
* **출력:**
    * 이력서: xlsx 파일로 생성됩니다.

## 개발 도구

* **개발 언어:** Java
* **빌드 도구:** Maven
* **라이브러리:** Apache POI, Apache Log4j, iText7

## 그 외 상세한 내용들

* **설치 방법:**
    1. JDK 17 이상을 설치합니다.
    2. Maven을 설치합니다.
    3. 프로젝트를 clone합니다.
    4. 다음 명령어를 실행하여 프로젝트를 빌드합니다.
