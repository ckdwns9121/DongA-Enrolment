package com.example.changjun.myapplication.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Changjun on 2019-02-24.
 */

public class Department {
    public static Department Instance = new Department();

    public static Department getInstace() {
        return Instance;
    }

    public ArrayList<Course> courseList= new ArrayList<>(); //모든 수강정보를 담을 어레이리스트
    public ArrayList<SugangComplete> sugangCompletesList = new ArrayList<>(); //수강신청 완료된 항목을 파싱해서 담을 리스트
    public ArrayList<Course> addList =new ArrayList<>(); //신청할 수강정보를 담을 어레이리스트.
    public ArrayList<HashMap<String, String>> ddCollValue = new ArrayList<HashMap<String, String>>(); //단과대 key, value
    public ArrayList<HashMap<String, String>> ddDptValue = new ArrayList<HashMap<String, String>>(); //학과 key, value,



}
