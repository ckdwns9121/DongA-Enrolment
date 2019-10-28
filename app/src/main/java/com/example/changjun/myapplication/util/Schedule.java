package com.example.changjun.myapplication.util;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Schedule {

    private static String monday[] = new String[31];
    private static String tuesday[] = new String[31];
    private static String wednesday[] = new String[31];
    private static String thursday[] = new String[31];
    private static String friday[] = new String[31];


    public static String mondayText[] = new String[31];
    public static String tuesdayText[] = new String[31];
    public static String wednesdayText[] = new String[31];
    public static String thursdayText[] = new String[31];
    public static String fridayText[] = new String[31];

    public static String mondayColor[] = new String[31];
    public static String tuesdayColor[] = new String[31];
    public static String wednesdayColor[] = new String[31];
    public static String thursdayColor[] = new String[31];
    public static String fridayColor[] = new String[31];

    public static String mondayNumber[] = new String[31];
    public static String tuesdayNumber[] = new String[31];
    public static String wednesdayNumber[] = new String[31];
    public static String thursdayNumber[] = new String[31];
    public static String fridayNumber[] = new String[31];

    public static String mondayClass[] = new String[31];
    public static String tuesdayClass[] = new String[31];
    public static String wednesdayClass[] = new String[31];
    public static String thursdayClass[] = new String[31];
    public static String fridayClass[] = new String[31];

    private static Schedule instance = new Schedule();

    int count=0;

    ScheduleList scheduleList;
    private static ArrayList<ScheduleList> scheduleLists = new ArrayList<>();

    private String parseColor[] = new String[7];

    public static Schedule getInstance() {
        return instance;
    }

    public Schedule() {
        parseColor[0] = "#FF5868"; //빨간
        parseColor[1] = "#81D694"; //민트
        parseColor[2] = "#FFB163"; //주황
        parseColor[3] = "#FFFF69"; //노랑
        parseColor[4] = "#C07AFF"; //보라
        parseColor[5] = "#217700"; //진한초록
        parseColor[6] = "#006DC5"; //진한파랑
    }

    public void addSchedule(String courseTime, String courseName, String courseProfessor, String courseNumber, String courseClass) {

        int temp;
        Random ran = new Random();
        int colorIndex = ((ran.nextInt(6) + 1 ));
        if ((temp = courseTime.indexOf("월")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    Log.e("시간표만들기", (courseTime.substring(startPoint + 1, endPoint)));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            Log.e("시간표만들기22", (courseTime.substring(startPoint + 1, endPoint)));
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            ArrayList<Integer> courseTimes = new ArrayList<>();
                            for (int save = startIndex; save <= endIndex; save++) {
                                courseTimes.add(save);
                                Log.e("스케줄리스트 월욜", "시간" + save);
                            }
                            scheduleList = new ScheduleList(courseName, courseTimes, parseColor[count], courseNumber, courseClass, "월");
                            scheduleLists.add(scheduleList);
                            break;
                        }
                    }
                }
            }
        }
        if ((temp = courseTime.indexOf("화")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    Log.e("시간표만들기", (courseTime.substring(startPoint + 1, endPoint)));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            Log.e("시간표만들기22", (courseTime.substring(startPoint + 1, endPoint)));
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            ArrayList<Integer> courseTimes = new ArrayList<>();
                            for (int save = startIndex; save <= endIndex; save++) {
                                courseTimes.add(save);
                                Log.e("스케줄리스트 화욜", "시간" + save);
                            }
                            scheduleList = new ScheduleList(courseName, courseTimes, parseColor[count], courseNumber, courseClass, "화");
                            scheduleLists.add(scheduleList);
                            break;
                        }
                    }
                }
            }
        }
        if ((temp = courseTime.indexOf("수")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    Log.e("시간표만들기", (courseTime.substring(startPoint + 1, endPoint)));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            Log.e("시간표만들기22", (courseTime.substring(startPoint + 1, endPoint)));
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            ArrayList<Integer> courseTimes = new ArrayList<>();
                            for (int save = startIndex; save <= endIndex; save++) {
                                courseTimes.add(save);
                                Log.e("스케줄리스트 수욜", "시간" + save);

                            }
                            scheduleList = new ScheduleList(courseName, courseTimes, parseColor[count], courseNumber, courseClass, "수");
                            scheduleLists.add(scheduleList);
                            break;
                        }
                    }
                }
            }
        }

        if ((temp = courseTime.indexOf("목")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    Log.e("시간표만들기", (courseTime.substring(startPoint + 1, endPoint)));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            Log.e("시간표만들기22", (courseTime.substring(startPoint + 1, endPoint)));
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            ArrayList<Integer> courseTimes = new ArrayList<>();
                            for (int save = startIndex; save <= endIndex; save++) {
                                courseTimes.add(save);
                                Log.e("스케줄리스트 목욜", "시간" + save);
                            }
                            scheduleList = new ScheduleList(courseName, courseTimes, parseColor[count], courseNumber, courseClass, "목");
                            scheduleLists.add(scheduleList);
                            break;
                        }
                    }
                }
            }
        }

        if ((temp = courseTime.indexOf("금")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    Log.e("시간표만들기", (courseTime.substring(startPoint + 1, endPoint)));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            Log.e("시간표만들기22", (courseTime.substring(startPoint + 1, endPoint)));
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            ArrayList<Integer> courseTimes = new ArrayList<>();
                            for (int save = startIndex; save <= endIndex; save++) {
                                courseTimes.add(save);
                                Log.e("스케줄리스트 금욜", "시간" + save);
                            }
                            scheduleList = new ScheduleList(courseName, courseTimes, parseColor[count], courseNumber, courseClass, "금");
                            scheduleLists.add(scheduleList);
                            break;
                        }
                    }
                }
            }
        }

        if ((temp = courseTime.indexOf("無")) > -1) {
            return;
        }
        count++;
        if(count==parseColor.length) count=0;
    }

    /*중복체크 */
    public boolean overlapCheck(String courseTime) {
        int temp;
        boolean overlap_check=true;
        if ((temp = courseTime.indexOf("월")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            for (int check = 0; check < scheduleLists.size(); check++) {
                                if (scheduleLists.get(check).scheduleDay.equals("월")) {
                                    for (int timecheck = 0; timecheck < scheduleLists.get(check).scheduleTime.size(); timecheck++) {
                                        if (scheduleLists.get(check).scheduleTime.get(timecheck) == startIndex || scheduleLists.get(check).scheduleTime.get(timecheck) == endIndex) {
                                            overlap_check= false;
                                        }
                                        if(scheduleLists.get(check).scheduleTime.get(timecheck)<startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)>endIndex){
                                            overlap_check=false;
                                        }
                                        if(scheduleLists.get(check).scheduleTime.get(timecheck)>startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)<endIndex){
                                            overlap_check=false;
                                        }
                                    }
                                }
                            }
                            return overlap_check;
                        }
                    }
                }
            }
        }

        if ((temp = courseTime.indexOf("화")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            for (int check = 0; check < scheduleLists.size(); check++) {
                                if (scheduleLists.get(check).scheduleDay.equals("화")) {
                                    for (int timecheck = 0; timecheck < scheduleLists.get(check).scheduleTime.size(); timecheck++) {
                                        //완전 겹치는경우, end랑  start랑 겹치는경우 ,start랑 end랑 겹치는경우
                                        if (scheduleLists.get(check).scheduleTime.get(timecheck) == startIndex || scheduleLists.get(check).scheduleTime.get(timecheck) == endIndex) {
                                           Log.e("완전겹치는경우 컴파일러소공들어옴","왜근데 안댈까?");
                                            overlap_check= false;
                                        }
                                        if(scheduleLists.get(check).scheduleTime.get(timecheck)<startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)>endIndex){
                                            overlap_check=false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)>startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)<endIndex){
                                            overlap_check=false;
                                        }
                                    }
                                }
                            }
                            return overlap_check;
                        }
                    }
                }
            }
        }
        if ((temp = courseTime.indexOf("수")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            for (int check = 0; check < scheduleLists.size(); check++) {
                                if (scheduleLists.get(check).scheduleDay.equals("수")) {
                                    Log.e("수욜중복체크","들어옴");
                                    for (int timecheck = 0; timecheck < scheduleLists.get(check).scheduleTime.size(); timecheck++) {
                                        if (scheduleLists.get(check).scheduleTime.get(timecheck) == startIndex || scheduleLists.get(check).scheduleTime.get(timecheck) == endIndex) {
                                            overlap_check= false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)<startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)>endIndex){
                                            overlap_check=false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)>startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)<endIndex){
                                            overlap_check=false;
                                        }
                                    }
                                }
                            }
                            return overlap_check;

                        }
                    }
                }
            }
        }
        if ((temp = courseTime.indexOf("목")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            for (int check = 0; check < scheduleLists.size(); check++) {
                                if (scheduleLists.get(check).scheduleDay.equals("목")) {
                                    for (int timecheck = 0; timecheck < scheduleLists.get(check).scheduleTime.size(); timecheck++) {
                                        if (scheduleLists.get(check).scheduleTime.get(timecheck) == startIndex || scheduleLists.get(check).scheduleTime.get(timecheck) == endIndex) {
                                            overlap_check= false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)<startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)>endIndex){
                                            overlap_check=false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)>startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)<endIndex){
                                            overlap_check=false;
                                        }
                                    }
                                }
                            }
                            return overlap_check;

                        }
                    }
                }
            }
        }
        if ((temp = courseTime.indexOf("금")) > -1) {
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < courseTime.length() && courseTime.charAt(i) != '('; i++) {
                if (courseTime.charAt(i) == '-') {
                    endPoint = i;
                    int startIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                    startPoint = endPoint;
                    for (int lastTime = startPoint; lastTime < courseTime.length(); lastTime++) {
                        if (courseTime.charAt(lastTime) == '(') {
                            endPoint = lastTime;
                            int endIndex = Integer.parseInt(courseTime.substring(startPoint + 1, endPoint));
                            for (int check = 0; check < scheduleLists.size(); check++) {
                                if (scheduleLists.get(check).scheduleDay.equals("금")) {
                                    for (int timecheck = 0; timecheck < scheduleLists.get(check).scheduleTime.size(); timecheck++) {
                                        if (scheduleLists.get(check).scheduleTime.get(timecheck) == startIndex || scheduleLists.get(check).scheduleTime.get(timecheck) == endIndex) {
                                            overlap_check= false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)<startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)>endIndex){
                                            overlap_check=false;
                                        }
                                         if(scheduleLists.get(check).scheduleTime.get(timecheck)>startIndex && scheduleLists.get(check).scheduleTime.get(timecheck)<endIndex){
                                            overlap_check=false;
                                        }
                                        else {
                                            Log.e("소실시간", scheduleLists.get(check).scheduleTime.get(timecheck)+"이거소실");
                                            Log.e("추가시간",endIndex+"이거추가");
                                        }
                                    }
                                }
                            }
                            return overlap_check;

                        }
                    }
                }
            }
        }
        if ((temp = courseTime.indexOf("無")) > -1) {
            return true;
        }
        return overlap_check;
    }


    public void deleteSchedule(String courseName){

      for(int i = 0; i< scheduleLists.size(); i++){
          if(this.scheduleLists.get(i).scheduleName.equals(courseName)){
              Log.e("삭제", scheduleLists.get(i).scheduleName);
              scheduleLists.remove(i--);
          }
      }
    }

    public void setting(TextView[] monday, TextView[] tuesday, TextView[] wednesday , TextView[] thursday , TextView[] friday ){

        for(int i = 0; i< scheduleLists.size(); i++){
            Log.e("그리기시간", scheduleLists.get(0).scheduleTime+"나오냐");
            //공백이지 않다
            if(!this.scheduleLists.get(i).scheduleName.equals("")){
                //월요일이면
                if(this.scheduleLists.get(i).scheduleDay.equals("월")) {
                    for (int timeIndex = 0; timeIndex < scheduleLists.get(i).scheduleTime.size(); timeIndex++) {
                        int index = this.scheduleLists.get(i).scheduleTime.get(timeIndex)-1;
                        Log.e("여기서 왜터지냐?????","인덱스번호"+index);
                        monday[index].setText(this.scheduleLists.get(i).scheduleName); //월요일에 알고리즘으로채우고
                        monday[index].setBackgroundColor(Color.parseColor(this.scheduleLists.get(i).scheduleColor)); //알고리즘에 대한 색깔을 칠한다
                    }
                }
                if(this.scheduleLists.get(i).scheduleDay.equals("화")) {
                    for (int timeIndex = 0; timeIndex < scheduleLists.get(i).scheduleTime.size(); timeIndex++) {
                        int index = this.scheduleLists.get(i).scheduleTime.get(timeIndex)-1;
                        Log.e("여기서 왜터지냐?????","인덱스번호"+index);
                        Log.e("이거이름뭐임?",this.scheduleLists.get(i).scheduleName);
                        tuesday[index].setText(this.scheduleLists.get(i).scheduleName); //월요일에 알고리즘으로채우고
                        tuesday[index].setBackgroundColor(Color.parseColor(this.scheduleLists.get(i).scheduleColor)); //알고리즘에 대한 색깔을 칠한다
                    }
                }
                if(this.scheduleLists.get(i).scheduleDay.equals("수")) {
                    for (int timeIndex = 0; timeIndex < scheduleLists.get(i).scheduleTime.size(); timeIndex++) {
                        int index = this.scheduleLists.get(i).scheduleTime.get(timeIndex)-1;
                        Log.e("여기서 왜터지냐?????","인덱스번호"+index);
                        wednesday[index].setText(this.scheduleLists.get(i).scheduleName); //월요일에 알고리즘으로채우고
                        wednesday[index].setBackgroundColor(Color.parseColor(this.scheduleLists.get(i).scheduleColor)); //알고리즘에 대한 색깔을 칠한다
                    }
                }
                if(this.scheduleLists.get(i).scheduleDay.equals("목")) {
                    for (int timeIndex = 0; timeIndex < scheduleLists.get(i).scheduleTime.size(); timeIndex++) {
                        int index = this.scheduleLists.get(i).scheduleTime.get(timeIndex)-1;
                        Log.e("여기서 왜터지냐?????","인덱스번호"+index);
                        thursday[index].setText(this.scheduleLists.get(i).scheduleName); //월요일에 알고리즘으로채우고
                        thursday[index].setBackgroundColor(Color.parseColor(this.scheduleLists.get(i).scheduleColor)); //알고리즘에 대한 색깔을 칠한다
                    }
                }
                if(this.scheduleLists.get(i).scheduleDay.equals("금")) {
                    for (int timeIndex = 0; timeIndex < scheduleLists.get(i).scheduleTime.size(); timeIndex++) {
                        int index = this.scheduleLists.get(i).scheduleTime.get(timeIndex)-1;
                        Log.e("여기서 왜터지냐?????","인덱스번호"+index);
                        friday[index].setText(this.scheduleLists.get(i).scheduleName); //월요일에 알고리즘으로채우고
                        friday[index].setBackgroundColor(Color.parseColor(this.scheduleLists.get(i).scheduleColor)); //알고리즘에 대한 색깔을 칠한다
                    }
                }
            }
        }

    }

}

class ScheduleList {

    ScheduleList(String scheduleName , ArrayList<Integer> scheduleTime, String scheduleColor , String scheduleNumber, String scheduleClass , String scheduleDay){
        this.scheduleName = scheduleName;
        this.scheduleColor = scheduleColor;
        this.scheduleNumber = scheduleNumber;
        this.scheduleClass =scheduleClass;
        this.scheduleTime = scheduleTime;
        this.scheduleDay = scheduleDay;
    }
    public String scheduleName;
    public String scheduleColor;
    public String scheduleNumber;
    public String scheduleClass;
    public String scheduleDay;
    public ArrayList<Integer> scheduleTime = new ArrayList<>();
}
