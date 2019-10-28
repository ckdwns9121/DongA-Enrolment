package com.example.changjun.myapplication.util;

public class Course {

    private int courseID ; // 아이디값
    private String courseDepartment ;//수강학과
    private String courseGrade;   // 학년
    private String courseDivision;   // 이수구분
    private String courseNumber;   // 과목번호
    private String courseClass;   // 분반
    private String courseName;   // 교과목명
    private String courseWeekOrNight;   // 주야구분
    private String courseCredit;   // 학점
    private String courseTheoryOrPractice;   // 이론 / 실습
    private String courseProfessor;   // 담당교수
    private String courseDifferentDepartmentNo;   // 타과불가
    private String courseTime75min;   // 75분 수업
    private String courseGradeNone;   // 학년 제한 없음
    private String courseTeaching;   // 교직과목
    private String courseLanguage;   // 강의 언어
    private String courseImaginaryClass;   // 가상 수업
    private String courseExcessClass;   // 연강과목
    private String courseLimitPerson;   // 인원 제한
    private String courseAttendPerson;   // 수강 인원
    private String courseCancel;   // 폐강
    private String courseTime ; // 강의 시간 (강의시간 중복되면 안됨)
    private String[] courseTime2 = new String[2]; // 테스트강의시간
    private String courseRemarks ; // 비고


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String[] getCourseTime2() {
        return courseTime2;
    }

    public void setCourseTime2(String[] courseTime2) {
        this.courseTime2 = courseTime2;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) { this.courseDepartment = courseDepartment; }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseDivision() {
        return courseDivision;
    }

    public void setCourseDivision(String courseDivision) {
        this.courseDivision = courseDivision;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseWeekOrNight() {
        return courseWeekOrNight;
    }

    public void setCourseWeekOrNight(String courseWeekOrNight) {
        this.courseWeekOrNight = courseWeekOrNight;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseTheoryOrPractice() {
        return courseTheoryOrPractice;
    }

    public void setCourseTheoryOrPractice(String courseTheoryOrPractice) {
        this.courseTheoryOrPractice = courseTheoryOrPractice;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getCourseDifferentDepartmentNo() {
        return courseDifferentDepartmentNo;
    }

    public void setCourseDifferentDepartmentNo(String courseDifferentDepartmentNo) {
        this.courseDifferentDepartmentNo = courseDifferentDepartmentNo;
    }

    public String getCourseTime75min() {
        return courseTime75min;
    }

    public void setCourseTime75min(String courseTime75min) {
        this.courseTime75min = courseTime75min;
    }

    public String getCourseGradeNone() {
        return courseGradeNone;
    }

    public void setCourseGradeNone(String courseGradeNone) {
        this.courseGradeNone = courseGradeNone;
    }

    public String getCourseTeaching() {
        return courseTeaching;
    }

    public void setCourseTeaching(String courseTeaching) {
        this.courseTeaching = courseTeaching;
    }

    public String getCourseLanguage() {
        return courseLanguage;
    }

    public void setCourseLanguage(String courseLanguage) {
        this.courseLanguage = courseLanguage;
    }

    public String getCourseImaginaryClass() {
        return courseImaginaryClass;
    }

    public void setCourseImaginaryClass(String courseImaginaryClass) {
        this.courseImaginaryClass = courseImaginaryClass;
    }

    public String getCourseExcessClass() {
        return courseExcessClass;
    }

    public void setCourseExcessClass(String courseExcessClass) {
        this.courseExcessClass = courseExcessClass;
    }

    public String getCourseLimitPerson() {
        return courseLimitPerson;
    }

    public void setCourseLimitPerson(String courseLimitPerson) {
        this.courseLimitPerson = courseLimitPerson;
    }

    public String getCourseAttendPerson() {
        return courseAttendPerson;
    }

    public void setCourseAttendPerson(String courseAttendPerson) {
        this.courseAttendPerson = courseAttendPerson;
    }

    public String getCourseCancel() {
        return courseCancel;
    }

    public void setCourseCancel(String courseCancel) {
        this.courseCancel = courseCancel;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseRemarks() {
        return courseRemarks;
    }

    public void setCourseRemarks(String courseRemarks) {
        this.courseRemarks = courseRemarks;
    }



}
