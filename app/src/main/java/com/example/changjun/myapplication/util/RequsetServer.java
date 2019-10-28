package com.example.changjun.myapplication.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.changjun.myapplication.activity.BasketActivity;
import com.example.changjun.myapplication.activity.MenuActivity;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyStore;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RequsetServer extends Thread {

    private static RequsetServer instance = new RequsetServer(); //엑티비티에서 같은객체 리턴하기위한 인스턴스
    public static CustomProgress customProgress;

    private Context m_context = null;
    private Activity m_activity = null;
    private Department information;

    /* 통신할 파라메터 변수*/
    private String urlString = new String();
    private String userID = new String(); //학번
    private String userPW = new String(); //비번
    private String ddColl = new String(); //단과대학 value 값
    private String ddDpt = new String(); // 학과정보 value 값
    private String ddMajor = new String(); //전공 value 값
    private String ddlSmt = new String(); //학기 value 값
    private String curi = new String();
    private String cls = new String();
    private String curinm = new String();

    public String year = new String();
    public int month = 0;

    private ArrayList<Course> addList; //장바구니 리스트
    private Course course; //과목정보를 담을 클래스
    private SugangComplete sugangComplete;

    public boolean isLoginCheck = false; //로그인 인증체크
    public boolean isCollCheck = false; //단과대선택
    public boolean isDptCheck = false; //학과선택
    public boolean isMajorCheck = false;//전공선택
    public boolean isSubjectCheck = false;//검색할 과목명이 있는지

    protected static HttpClient httpClient;
    protected static HttpPost httpPost;
    protected static HttpResponse response;
    public LoginThread loginThread;
    public ParseingThread parseingThread;
    public SugangThread sugangThread;
    public GetSugangList getSugangList;
    public CancleThread cancleThread;
    public static String sugangResult;
    public static String cancleResult;

    public RequsetServer() {
        httpClient = getHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
    }

    public RequsetServer(Context context, Activity activity) {
        this.m_context = context;
        this.m_activity = activity;
        httpClient = getHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
        customProgress = new CustomProgress(m_context);
    }

    public static RequsetServer getInstance() {
        return instance;
    }

    public void doLogin(String userID, String userPW) {
        Log.v("알림", "최초 로그인에 사용되는 곳함수 들어옴");
        this.userID = userID;
        this.userPW = userPW;
        getTime();
        loginThread = new LoginThread();
        loginThread.start();
        customProgress.show();
    }

    public void requsetSugang(ArrayList<Course> addList) {
        this.addList = addList;
        getTime();
        sugangThread = new SugangThread();
        sugangThread.start();
    }

    public void requsetCancle(String curi, String cls, String curinm){
        this.curi =curi;
        this.cls =cls;
        this.curinm= curinm;
        cancleThread = new CancleThread();
        cancleThread.start();
    }

    public void doParse(String ddColl) {
        getTime();
        this.ddColl = ddColl;
        this.isCollCheck = true;
        this.isDptCheck = false;
        this.isMajorCheck = false;
        parseingThread=new ParseingThread();
        parseingThread.start();
    }

    public void doParse(String ddColl, String ddDpt) {
        getTime();
        this.ddColl = ddColl;
        this.ddDpt = ddDpt;
        this.isCollCheck = true;
        this.isDptCheck = true;
        this.isMajorCheck = false;
        parseingThread=new ParseingThread();
        parseingThread.start();
    }

    public void doParse(String ddColl, String ddDpt, String ddMajor) {
        getTime();
        this.ddColl = ddColl; // 대학 value
        this.ddDpt = ddDpt; //학과 value
        this.ddMajor = ddMajor; //전공 value
        this.isCollCheck = true;
        this.isDptCheck = true;
        this.isMajorCheck=false;
        this.isSubjectCheck = true;
        parseingThread=new ParseingThread();
        parseingThread.start();
    }

    public void getList(){
        Log.e("리스트 들고오는거","시작");
        getSugangList = new GetSugangList();
        getSugangList.start();
    }

    public class LoginThread extends Thread {
        @Override
        public void run() {
            Log.v("알림", "로그인 스레드 시작");
            List<BasicNameValuePair> nameValuePairs;
            information = Department.getInstace();
            String loginString = "";

            String __VIEWSTATE = new String();
            String __EVENTVALIDATION = new String();
            String __VIEWSTATEGENERATOR = new String();

            try {
                urlString = "https://sugang.donga.ac.kr/login.aspx";
                httpPost = new HttpPost(urlString);
                nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                loginString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Source source = new Source(new String(loginString));
                source.fullSequentialParse();
                List<Element> firstList = source.getAllElements(HTMLElementName.INPUT); //INPUT이 있는 모든값들을 다넣음.

                for (int i = 0; i < firstList.size(); i++) {
                    if (firstList.get(i).getAttributeValue("name").equals("__VIEWSTATE")) {
                        __VIEWSTATE = firstList.get(i).getAttributeValue("value");
                    } else if (firstList.get(i).getAttributeValue("name").equals("__VIEWSTATEGENERATOR")) {
                        __VIEWSTATEGENERATOR = firstList.get(i).getAttributeValue("value");
                    } else if (firstList.get(i).getAttributeValue("name").equals("__EVENTVALIDATION")) {
                        __EVENTVALIDATION = firstList.get(i).getAttributeValue("value");
                    }
                }
                nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
                nameValuePairs.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION));
                nameValuePairs.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR));
                nameValuePairs.add(new BasicNameValuePair("txtStudentCd", userID));
                nameValuePairs.add(new BasicNameValuePair("txtPasswd", userPW));
                nameValuePairs.add(new BasicNameValuePair("ibtnLogin.x", "0"));
                nameValuePairs.add(new BasicNameValuePair("ibtnLogin.y", "0"));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                loginString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Log.v("알람", loginString);
                if (loginString.contains("학부 수강신청")) {
                    isLoginCheck = true;
                    handler.sendEmptyMessage(0);
                } else {
                    isLoginCheck = false;
                    handler.sendEmptyMessage(0);
                }
                if (isLoginCheck) {
                    Log.v("서버체크","여긴옴?");
                    urlString = "http://sugang.donga.ac.kr/SUGANGLECTIME.aspx";
                    httpPost = new HttpPost(urlString);
                    nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    response = httpClient.execute(httpPost);
                    loginString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                    Log.d("서버체크",loginString);
                    source = new Source(new String(loginString));
                    source.fullSequentialParse();
                    Element selected = (Element) source.getAllElements(HTMLElementName.SELECT).get(2);
                    Element option = null;
                    int optionCount = selected.getAllElements(HTMLElementName.OPTION).size();
                    HashMap hm;
                    information.ddCollValue.clear();
                    for (int i = 0; i < optionCount; i++) {
                        hm = new HashMap<String, String>();
                        option = (Element) selected.getAllElements(HTMLElementName.OPTION).get(i);
                        hm.put(option.getTextExtractor().toString(), option.getAttributeValue("value"));
                        information.ddCollValue.add(hm);
                    }
                    for (int i = 0; i < information.ddCollValue.size(); i++) {
                        Log.v("대학정보값", information.ddCollValue.get(i).toString());
                    }
                    Log.v("대학정보파싱", "끝");
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ParseingThread extends Thread {
        @Override
        public void run() {
            List<BasicNameValuePair> nameValuePairs;
            information = Department.getInstace();
            String __VIEWSTATE = new String();
            String __EVENTVALIDATION = new String();
            String __VIEWSTATEGENERATOR = new String();
            String responseStringA = "";
            String resPonseStringB = "";
            String resPonseStringC = "";
            String responseStringD = "";
            try {
                urlString = "http://sugang.donga.ac.kr/SUGANGLECTIME.aspx";
                httpPost = new HttpPost(urlString);
                nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                responseStringA = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                if (isCollCheck) {
                    Source source = new Source(new String(responseStringA));
                    source.fullSequentialParse();
                    List<net.htmlparser.jericho.Element> firstList = source.getAllElements(HTMLElementName.INPUT);
                    for (int i = 0; i < firstList.size(); i++) {
                        if (firstList.get(i).getAttributeValue("name").equals("__VIEWSTATE")) {
                            __VIEWSTATE = firstList.get(i).getAttributeValue("value");
                        } else if (firstList.get(i).getAttributeValue("name").equals("__VIEWSTATEGENERATOR")) {
                            __VIEWSTATEGENERATOR = firstList.get(i).getAttributeValue("value");
                        } else if (firstList.get(i).getAttributeValue("name").equals("__EVENTVALIDATION")) {
                            __EVENTVALIDATION = firstList.get(i).getAttributeValue("value"); //A라는 인자값 파싱하고
                        }
                    }
                    nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION)); // A넘겨주기
                    nameValuePairs.add(new BasicNameValuePair("__LASTFOCUS", ""));
                    nameValuePairs.add(new BasicNameValuePair("__EVENTTARGET", "ddlColl"));
                    nameValuePairs.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
                    nameValuePairs.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR));
                    nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
                    nameValuePairs.add(new BasicNameValuePair("radiosel", "rbAll"));
                    nameValuePairs.add(new BasicNameValuePair("ddlYear", year));
                    nameValuePairs.add(new BasicNameValuePair("ddlSmt", ddlSmt));
                    nameValuePairs.add(new BasicNameValuePair("ddlDpt", "NO"));
                    nameValuePairs.add(new BasicNameValuePair("ddlMajor", "NO"));
                    nameValuePairs.add(new BasicNameValuePair("ddlColl", ddColl)); //공과대학 4700
                    nameValuePairs.add(new BasicNameValuePair("ddlComDiv", "%"));
                    nameValuePairs.add(new BasicNameValuePair("txtCuri", ""));
                    nameValuePairs.add(new BasicNameValuePair("txtCuriNm", ""));
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    response = httpClient.execute(httpPost);
                    resPonseStringB = EntityUtils.toString(response.getEntity(), HTTP.UTF_8); //학과가 선택된 페이지 return

                    source = new Source(new String(resPonseStringB)); //B페이지 파싱
                    source.fullSequentialParse();
                    net.htmlparser.jericho.Element selected = (net.htmlparser.jericho.Element) source.getAllElements(HTMLElementName.SELECT).get(3);
                    net.htmlparser.jericho.Element option = null;
                    int optionCount = selected.getAllElements(HTMLElementName.OPTION).size(); //3번째 셀렉트안에있는 옵션갯수.

                    HashMap hm;
                    information.ddDptValue.clear();

                    for (int i = 0; i < optionCount; i++) {
                        hm = new HashMap<String, String>();
                        option = (net.htmlparser.jericho.Element) selected.getAllElements(HTMLElementName.OPTION).get(i);
                        hm.put(option.getTextExtractor().toString(), option.getAttributeValue("value"));
                        information.ddDptValue.add(hm);
                    }

                    if (isCollCheck && isDptCheck) {
                        source = new net.htmlparser.jericho.Source(new String(resPonseStringB)); //학과가선택되있는 페이지
                        source.fullSequentialParse();
                        List<net.htmlparser.jericho.Element> secondList = source.getAllElements(HTMLElementName.INPUT);
                        for (int i = 0; i < secondList.size(); i++) {
                            if (secondList.get(i).getAttributeValue("name").equals("__VIEWSTATE")) {
                                __VIEWSTATE = secondList.get(i).getAttributeValue("value");
                            } else if (secondList.get(i).getAttributeValue("name").equals("__VIEWSTATEGENERATOR")) {
                                __VIEWSTATEGENERATOR = secondList.get(i).getAttributeValue("value");
                            } else if (secondList.get(i).getAttributeValue("name").equals("__EVENTVALIDATION")) {
                                __EVENTVALIDATION = secondList.get(i).getAttributeValue("value"); //B를 파싱해옴
                            }
                        }
                        nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                        nameValuePairs.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION)); //그 B를 학과검색할때 념겨줌
                        nameValuePairs.add(new BasicNameValuePair("__LASTFOCUS", ""));
                        nameValuePairs.add(new BasicNameValuePair("__EVENTTARGET", "ddlDpt")); //학과조회
                        nameValuePairs.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
                        nameValuePairs.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR));
                        nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
                        nameValuePairs.add(new BasicNameValuePair("radiosel", "rbAll"));
                        nameValuePairs.add(new BasicNameValuePair("ddlYear", year));
                        nameValuePairs.add(new BasicNameValuePair("ddlSmt", ddlSmt));
                        nameValuePairs.add(new BasicNameValuePair("ddlDpt", ddDpt)); //전자공학과 4717
                        nameValuePairs.add(new BasicNameValuePair("ddlMajor", "0"));
                        nameValuePairs.add(new BasicNameValuePair("ddlColl", ddColl)); //공과대학 4700
                        nameValuePairs.add(new BasicNameValuePair("ddlComDiv", "%"));
                        nameValuePairs.add(new BasicNameValuePair("txtCuri", ""));
                        nameValuePairs.add(new BasicNameValuePair("txtCuriNm", ""));
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        response = httpClient.execute(httpPost);
                        resPonseStringC = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);  //C가 있는 곳
                        if (isCollCheck && isDptCheck && isSubjectCheck) {
                            source = new net.htmlparser.jericho.Source(new String(resPonseStringC));
                            source.fullSequentialParse();
                            List<net.htmlparser.jericho.Element> thridList = source.getAllElements(HTMLElementName.INPUT);
                            for (int i = 0; i < thridList.size(); i++) {
                                if (thridList.get(i).getAttributeValue("name").equals("__VIEWSTATE")) {
                                    __VIEWSTATE = thridList.get(i).getAttributeValue("value");
                                } else if (thridList.get(i).getAttributeValue("name").equals("__VIEWSTATEGENERATOR")) {
                                    __VIEWSTATEGENERATOR = thridList.get(i).getAttributeValue("value");
                                } else if (thridList.get(i).getAttributeValue("name").equals("__EVENTVALIDATION")) {
                                    __EVENTVALIDATION = thridList.get(i).getAttributeValue("value"); //
                                }
                            }
                            nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                            nameValuePairs.add(new BasicNameValuePair("__EVENTVALIDATION", __EVENTVALIDATION));
                            nameValuePairs.add(new BasicNameValuePair("__LASTFOCUS", ""));
                            nameValuePairs.add(new BasicNameValuePair("__EVENTTARGET", ""));
                            nameValuePairs.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
                            nameValuePairs.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR));
                            nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
                            nameValuePairs.add(new BasicNameValuePair("radiosel", "rbAll"));
                            nameValuePairs.add(new BasicNameValuePair("ddlYear", year));
                            nameValuePairs.add(new BasicNameValuePair("ddlSmt", ddlSmt));
                            nameValuePairs.add(new BasicNameValuePair("ddlDpt", ddDpt)); //전자공학과 4717
                            nameValuePairs.add(new BasicNameValuePair("ddlMajor", "0")); //전공선택 o
                            nameValuePairs.add(new BasicNameValuePair("ddlColl", ddColl)); //공과대학 4700
                            nameValuePairs.add(new BasicNameValuePair("ddlComDiv", "%"));
                            nameValuePairs.add(new BasicNameValuePair("txtCuri", ""));
                            nameValuePairs.add(new BasicNameValuePair("txtCuriNm", ""));
                            nameValuePairs.add(new BasicNameValuePair("ibtnSearch.x", "0"));
                            nameValuePairs.add(new BasicNameValuePair("ibtnSearch.y", "0"));
                            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                            response = httpClient.execute(httpPost);
                            responseStringD = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                            source = new Source(new String(responseStringD));
                            source.fullSequentialParse();
                            net.htmlparser.jericho.Element table = source.getAllElements(HTMLElementName.TABLE).get(3);
                            net.htmlparser.jericho.Element a;
                            List<Element> td,tr= null;
                            tr = table.getAllElements(HTMLElementName.TR);
                            int tr_count = table.getAllElements(HTMLElementName.TR).size(); //4번째 테이블 안에있는 TR의갯수;
                            information.courseList.clear();
                            Log.e("초기화했다","왓다");
                            // getSubject
                            for (int i = 1; i < tr_count; i++) {
                                course = new Course();
                                td = tr.get(i).getAllElements(HTMLElementName.TD);
                                a = (net.htmlparser.jericho.Element) tr.get(i).getAllElements(HTMLElementName.A).get(0);
                                course.setCourseID(i); //고유번호
                                course.setCourseDepartment(td.get(0).getTextExtractor().toString()); //수강학과
                                course.setCourseGrade(td.get(1).getTextExtractor().toString()); // 학년
                                course.setCourseDivision(td.get(2).getTextExtractor().toString()); //이수구분
                                course.setCourseNumber(td.get(3).getTextExtractor().toString()); //과목번호
                                course.setCourseClass(td.get(4).getTextExtractor().toString()); //분반
                                course.setCourseName(a.getTextExtractor().toString()); //교과목명
                                course.setCourseWeekOrNight(td.get(6).getTextExtractor().toString()); //주야
                                course.setCourseCredit(td.get(7).getTextExtractor().toString()); //학점
                                course.setCourseTheoryOrPractice(td.get(8).getTextExtractor().toString()); //이론/실습
                                course.setCourseProfessor(td.get(9).getTextExtractor().toString()); //담당교수
                                course.setCourseDifferentDepartmentNo(td.get(10).getTextExtractor().toString()); //타과불가
                                course.setCourseTime75min(td.get(11).getTextExtractor().toString()); //75분수업
                                course.setCourseGradeNone(td.get(12).getTextExtractor().toString()); //학년제한없음
                                course.setCourseTeaching(td.get(13).getTextExtractor().toString()); //교직과목
                                course.setCourseLanguage(td.get(14).getTextExtractor().toString()); //강의언어
                                course.setCourseImaginaryClass(td.get(15).getTextExtractor().toString()); //가상수업
                                course.setCourseExcessClass(td.get(16).getTextExtractor().toString()); //연강과목
                                course.setCourseLimitPerson(td.get(17).getTextExtractor().toString()); //인원제한
                                course.setCourseAttendPerson(td.get(18).getTextExtractor().toString()); //수강인원
                                course.setCourseCancel(td.get(19).getTextExtractor().toString()); //폐강
                                course.setCourseTime(td.get(20).getTextExtractor().toString()); //강의시간
                                course.setCourseRemarks(td.get(21).getTextExtractor().toString()); //비고
                                information.courseList.add(course);
                            }
                            Log.e("여까지옴","왓다");
                            isSame(information.courseList);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* 수강신청 프로세서*/
    public class SugangThread extends Thread {
        @Override
        public void run() {
            List<BasicNameValuePair> nameValuePairs;
            String responseString;
            try {
                urlString = "http://sugang.donga.ac.kr/SUGANG030.aspx"; //수강신청 url;
                httpPost = new HttpPost(urlString);
                sugangResult="";
                for(int i=0;i<addList.size();i++) {
                    nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("curi", addList.get(i).getCourseNumber()));
                    nameValuePairs.add(new BasicNameValuePair("cls", addList.get(i).getCourseClass()));
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    response = httpClient.execute(httpPost);
                    responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                    Log.e("되냐?",responseString);
                    if(responseString.contains("신청하신 과목을 저장 하였습니다.")){
                        sugangResult+= addList.get(i).getCourseName()+" 신청완료\n";
                        //SugangEnrollmentActivity.total-= Integer.parseInt(addList.get(i).getCourseCredit());
                        BasketActivity.total-=Integer.parseInt(addList.get(i).getCourseCredit());
                        addList.remove(i);
                        i--;
                    }
                    else if(responseString.contains("이미 수강신청되어 있습니다.")){
                        sugangResult+=addList.get(i).getCourseName()+" 중복신청\n";
                      //  SugangEnrollmentActivity.total-= Integer.parseInt(addList.get(i).getCourseCredit());
                        BasketActivity.total-=Integer.parseInt(addList.get(i).getCourseCredit());
                        addList.remove(i);
                        i--;
                    }
                    else if(responseString.contains("이미 신청한 교과목과 시간표가 중복되어 신청할수 없습니다.")){
                        sugangResult+=addList.get(i).getCourseName()+" 시간중복\n";
                    }
                }
                sugangResult+="수강신청이 완료되었습니다.";

            } catch (Exception e) {
                sugangResult="수강신청 기간이 아닙니다.";
                Log.e("서버닫힘","서버닫힘");
                e.printStackTrace();
            }
        }
    }

    public class CancleThread extends Thread {
        @Override
        public void run() {
            List<BasicNameValuePair> nameValuePairs;
            String cancleString="";
            try {
                urlString = "http://sugang.donga.ac.kr/SUGANG031.aspx"; //수강신청취소
                httpPost = new HttpPost(urlString);
                nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("curi", curi));
                nameValuePairs.add(new BasicNameValuePair("cls", cls));
                nameValuePairs.add(new BasicNameValuePair("curinm", curinm));
                Log.e("취소할과목명",curinm);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                cancleString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Log.e("되냐?",cancleString);

            } catch (Exception e) {
                Log.e("서버닫힘","서버닫힘");
                e.printStackTrace();
            }
        }
    }

    public class GetSugangList extends Thread{
        @Override
        public void run(){
            urlString = "http://sugang.donga.ac.kr/SUGANG010.aspx"; //수강신청리스트 url
            httpPost = new HttpPost(urlString);
            List<BasicNameValuePair> nameValuePairs;
            String strResult = "";
            Log.e("리스트","들고와지는거파싱부분");
            information = Department.getInstace();
            try{
                nameValuePairs = new ArrayList<BasicNameValuePair>(2);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response = httpClient.execute(httpPost);
                strResult = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                Source source = new Source(new String(strResult));
                source.fullSequentialParse();
                Element table = source.getAllElements(HTMLElementName.TABLE).get(5);
                List<Element> td,tr= null;
                tr = table.getAllElements(HTMLElementName.TR);
                int tr_count = table.getAllElements(HTMLElementName.TR).size();
                information.sugangCompletesList.clear();
                for(int i=1; i<tr_count;i++){
                    sugangComplete = new SugangComplete();
                    td = tr.get(i).getAllElements(HTMLElementName.TD);
                    sugangComplete.setCourseDivision(td.get(0).getTextExtractor().toString());
                    sugangComplete.setCourseWeekOrNight(td.get(1).getTextExtractor().toString());
                    sugangComplete.setCourseNumber(td.get(2).getTextExtractor().toString());
                    sugangComplete.setCourseName(td.get(3).getTextExtractor().toString());
                    sugangComplete.setCourseClass(td.get(4).getTextExtractor().toString());
                    sugangComplete.setCourseCredits(td.get(5).getTextExtractor().toString());
                    sugangComplete.setCourseTime(td.get(6).getTextExtractor().toString());
                    sugangComplete.setCourseProfessor(td.get(7).getTextExtractor().toString());
                    information.sugangCompletesList.add(sugangComplete);
                    Log.e("리스트들고오기",td.get(3).getTextExtractor().toString());
                }

                isSame2(information.sugangCompletesList);
            }
            catch (Exception e){
                cancleResult="조회기간이 아닙니다.";
                Log.e("신청목록에러","에러뜨네");
                e.printStackTrace();
            }
        }
    }

    /* 메인 엑티비티 UI 컨트롤을 위한 핸들러
    *  스레드 내에서 UI 컨트롤 불가하기때문에
    *  핸들러를 사용해서 해야함
    * */

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (isLoginCheck) {
               // Toast.makeText(m_context, "메인페이지로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(m_activity, MenuActivity.class);
                m_activity.startActivity(intent);
                super.handleMessage(msg);
            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(m_context);
                AlertDialog dialog =builder.setMessage("학번 또는 비밀번호를 확인해주세요.")
                        .setPositiveButton("확인",null)
                        .create();
                dialog.show();
                super.handleMessage(msg);
            }
        customProgress.dismiss();
        }
    };

    /*SSL 보안인증
    * 학교서버자체에 SSL암호화 프로토콜 보안이 되있어서
    * SSL 인증후 로그인 시도 해야함
    * 지금 로그인이 잘되는데 만약 플레이스토어에 등록되지 않을시
    * 콜로니에서 커스텀해준 getHttpClient() 함수로 수정해야함
    * */

    private HttpClient getHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore
                    .getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new SFSSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();

            registry.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(
                    params, registry);
            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public void isSame(ArrayList<Course> courseList){
        for(int i=0;i<courseList.size()-1;i++){
            if ((courseList.get(i).getCourseNumber().equals(courseList.get(i + 1).getCourseNumber())) && (courseList.get(i).getCourseClass().equals(courseList.get(i+1).getCourseClass())))
            {
                String now = courseList.get(i).getCourseTime();
                String next = courseList.get(i+1).getCourseTime();
                courseList.get(i).setCourseTime(now+"/"+next);
                courseList.remove(i+1);
            }
        }
    }
    public void isSame2(ArrayList<SugangComplete> sugangCompletesList){
        for(int i=0;i<sugangCompletesList.size()-1;i++){
            if ((sugangCompletesList.get(i).getCourseNumber().equals(sugangCompletesList.get(i + 1).getCourseNumber())) && (sugangCompletesList.get(i).getCourseClass().equals(sugangCompletesList.get(i+1).getCourseClass())))
            {
                String now = sugangCompletesList.get(i).getCourseTime();
                String next = sugangCompletesList.get(i+1).getCourseTime();
                sugangCompletesList.get(i).setCourseTime(now+"/"+next);
                sugangCompletesList.remove(i+1);
            }
        }
    }

    /*현재 학기 받아오기 */
    public void getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        year = CurYearFormat.format(date);
        month = Integer.parseInt(CurMonthFormat.format(date));
        /* 지금이 몇학기인지 월별로 구하기 */
        if (month <=1) ddlSmt = "21"; //동계
        else if (month <= 6) ddlSmt = "10"; //1학기
        else if (month <= 7) ddlSmt = "11"; //하계
        else ddlSmt = "20"; //2학기
    }
}
