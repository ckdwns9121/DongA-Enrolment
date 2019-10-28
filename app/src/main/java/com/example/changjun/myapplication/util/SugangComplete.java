package com.example.changjun.myapplication.util;

public class SugangComplete {
    private String courseName; //과목명
    private String courseWeekOrNight; //주야
    private String courseNumber; //과목코드
    private String courseClass; //분반
    private String courseCredits; //학점
    private String courseTime; //시간
    private String courseProfessor; //교수
    private String courseDivision; //전공



    public String getCourseDivision() {
        return courseDivision;
    }

    public void setCourseDivision(String courseDivision) {
        this.courseDivision = courseDivision;
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

    public String getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(String courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }


}

class SubSugangInfo{


    private String courseEnrollCredits; //신청학점 및 신청가능학점
    private String coursePreviousCredits;// 이월학점
    private String courseTotal; //신청과목수

    public String getCourseTotal() {
        return courseTotal;
    }

    public void setCourseTotal(String courseTotal) {
        this.courseTotal = courseTotal;
    }



    public String getCourseEnrollCredits() {
        return courseEnrollCredits;
    }

    public void setCourseEnrollCredits(String courseEnrollCredits) {
        this.courseEnrollCredits = courseEnrollCredits;
    }


    public String getCoursePreviousCredits() {
        return coursePreviousCredits;
    }

    public void setCoursePreviousCredits(String coursePreviousCredits) {
        this.coursePreviousCredits = coursePreviousCredits;
    }
}