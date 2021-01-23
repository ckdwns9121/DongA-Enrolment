<h1 align="center">동아대학교 모바일 수강신청</h1>

<p align="center"><img src="https://play-lh.googleusercontent.com/Xkhfw107OmQRO42432wJMVCCOSsJCzL3z0owyyIC9jskCuhps7qJ3ozchBAO0Pn5Xg=s180-rw" width="240" /></p>

<p align="center">스마트한 동아인의 필수 App은 무엇? 바로 이것(This is)!</p>

<p align="center"><a href="https://play.google.com/store/apps/details?id=kr.co.thisis.dsisproject&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img src="https://user-images.githubusercontent.com/45222982/93845190-e26bb100-fcda-11ea-9dd3-737ffb68223a.png" width="240" /></a>   <a href="https://apps.apple.com/kr/app/%EB%94%94%EC%8A%A4%EC%9D%B4%EC%A6%88/id1490702439?mt=8"><img src="https://user-images.githubusercontent.com/45222982/93845192-e39cde00-fcda-11ea-8d05-cf1e50243b3c.png" width="240" /></a></p>

## 프로젝트 참가 이력
- Thisis Backend
  - WeShow
  - 학식정보
  - 전화번호부
  - 학사일정
 

## Tech
 - PHP
 - MYSQL
 - JSON
 - simpledom
 - crawling
 - BackTraking Algorithm
 - cron scheduler


## WeShow
  시간표 경우의 수 자동생성 프로그램

### Screen Shots
<p align="center"><img src="https://user-images.githubusercontent.com/45222982/93857691-f40e8200-fcf5-11ea-9e4f-f13ddaca4285.png" width="280" height="500"/> <img src="https://user-images.githubusercontent.com/45222982/93857700-f670dc00-fcf5-11ea-9a30-a4d41e9f9fe6.png" width="280" height="500"/></p>

### Function

| Name | Param | Returns | Description |
|---|---|---|---|
| `findScheduleCases` | result / schedules / selectedList / timeTable / count | - | 선택된 시간표 정보를 통해 가능한 모든 시간표 경우의 수 추출 |
| `canTakeit` | timeTable / $selectedList[$count] | boolean | 해당 시간에 이미 선택된 수업이 있는지 확인 |
| `paintSchedule` | timeTable, schedules, selectedList[count][i] | - | 해당 수업을 시간표에 추가 |
| `eraseSchedule` | timeTable, schedules, selectedList[count][i] | - | 새로운 경우의 수 추츨을 위해 수업 제거 |


### Source
```
/*선택된 시간표를 이용해 만들 수 있는 강의시간 경우의 수 추출*/
function findScheduleCases(&$result, &$schedules, &$selectedList, &$timeTable, $count){
    if($count == count($selectedList)){
        printResult($schedules);
        echo '<br>';
        array_push($result, $schedules); //result push
        return;
    }
    if(is_array($selectedList[$count])){
        for($i = 0; $i < count($selectedList[$count]); $i++){
            if(canTakeit($timeTable, $selectedList[$count][$i])){
                paintSchedule($timeTable, $schedules, $selectedList[$count][$i]);
                findScheduleCases($result, $schedules, $selectedList, $timeTable, $count + 1);
                eraseSchedule($timeTable, $schedules, $selectedList[$count][$i]);
            }
        } 
    }
    else if(canTakeit($timeTable, $selectedList[$count])){
        paintSchedule($timeTable, $schedules, $selectedList[$count]);
        findScheduleCases($result, $schedules, $selectedList, $timeTable, $count + 1);
        eraseSchedule($timeTable, $schedules, $selectedList[$count]);
    }
}
```

## 학식정보
  동아대학교 학식정보 제공

### Screen Shots
<p align="center"><img src="https://user-images.githubusercontent.com/45222982/93844004-c108c600-fcd6-11ea-8d0a-d69830603aa6.png" width="280" height="500"/> <img src="https://user-images.githubusercontent.com/45222982/93844005-c239f300-fcd6-11ea-9c60-be58bb901603.png" width="280" height="500"/></p>

### 식당 List
- 승학캠퍼스
  - 교수회관
  - 학생회관
  - 도서관
  
- 부민캠퍼스
  - 교수회관
  - 학생회관
  
- 한림생활관
  - 승학기숙사
  - 부민기숙사
  
### Table

| id | setMenu | oneMenu | snackMenu | date |
|---|---|---|---|---|
|1|보쌈정식|소고기덮밥|라면|2020-09-21|
|2|제육정식|오리불고기덮밥|돈까스|2020-09-21|
  
### Source
```
/*
for($id = -1; $id < 7; $id++){//어제날짜부터~일주일
    $day = date("Ymd",strtotime ($id." days"));
    $html = file_get_html("http://www.donga.ac.kr/gzSub_007005005.aspx?DT=".$day);
    $date = date("Y-m-d",strtotime($id." days"));
}
```
  

## 전화번호부
  동아대학교 전화번호부정보 제공

### Screen Shots
<p align="center"><img src="https://user-images.githubusercontent.com/45222982/93844006-c2d28980-fcd6-11ea-9cae-fc48a82e6582.png" width="280" height="500"/></p>

### List
- 승학캠퍼스
- 부민캠퍼스
- 구덕캠퍼스

### Table

| id | organization | office | number |
|---|---|---|---|
|1|학사관리과|교육과정위원회|200-6124|
|2|학생상담센터|사무실|200-6070|

### Source
```
/*
switch($numberInfo[0]){
  case "승학":
    return $sql = "UPDATE call_number_sh SET organization = '{$numberInfo[1]}', office = '{$numberInfo[2]}', number = '{$numberInfo[3]}' WHERE id = ++$shId";
  case "부민":
    return $sql = "UPDATE call_number_bm SET organization = '{$numberInfo[1]}', office = '{$numberInfo[2]}', number = '{$numberInfo[3]}' WHERE id = ++$bmId";
  case "구덕":
    return $sql = "UPDATE call_number_gd SET organization = '{$numberInfo[1]}', office = '{$numberInfo[2]}', number = '{$numberInfo[3]}' WHERE id = ++$gdId";
}
```

## 학사일정
  동아대학교 학사일정정보 제공

### Screen Shots
<p align="center"><img src="https://user-images.githubusercontent.com/45222982/93844009-c2d28980-fcd6-11ea-823c-d59a4a09b6e6.png" width="280" height="500"/></p>

### Table

| id | semester | date | calendar |
|---|---|---|---|
|1|1| 2020-02-12(수) ~ 02-14(금)|제1학기 재학생 수강신청|
|2|2| 2020-08-24(월) ~ 08-27(목)|제2학기 등록|

## cron
 - 0 0,4,8,9,10,11,12,13,14,15,16,17,18,20 * * * php ~~.../cafeteriaUpdate.php~~
 - #0 0 1 * * php ~~.../Calendar_update.php~~
 - 0 0 1 * * php ~~.../CallNumberUpdate.php~~




동아대학교 수강신청 어플리케이션
===============================
### 1. 개발 동기   
```
 동아대학교 수강신청 시스템은 대부분 학교가 가지고 있는 장바구니 시스템이 없을 뿐더러 PC기반 시스템이여서 많은 불편함이 있다.  
 이를 해결하기 위해 안드로이드 스튜디오를 활용해 모바일 기반 장바구니 수강신청 시스템을 고안했다.   
```     

### 2. 기능   
   - 강의시간표 조회기능.   
   - 신청현황을 오버했을 시 색상 변경.   
   - 수강하고자 하는 과목을 장바구니에 담아 원클릭 수강신청 가능.   
   - 수강신청한 시간표 생성.   
   - 수강취소 기능.   
　     
### 3. 사용한 라이브러리
  - HttpClient 4.2: [link](https://hc.apache.org/httpcomponents-client-4.2.x/index.html)   
  - Jericho 3.1: [link](https://mvnrepository.com/artifact/net.htmlparser.jericho/jericho-html/3.1)   
  - APK File: [link](https://play.google.com/store/apps/details?id=com.sugangApp.changjun.myapplication)


### 4. 스크린샷

<img src="https://user-images.githubusercontent.com/40492343/69025588-013e4e80-0a0b-11ea-923e-3337e0ec6d16.png" width="202px" height="318px"></img>
<img src="https://user-images.githubusercontent.com/40492343/92086218-d770df80-ee04-11ea-85f0-5e959f101a27.jpg" width="202px" height="318px"></img>
<img src="https://user-images.githubusercontent.com/40492343/92086522-3f272a80-ee05-11ea-9c4e-233d56106945.jpg" width="202px" height="318px"></img>
<img src="https://user-images.githubusercontent.com/40492343/92086531-451d0b80-ee05-11ea-9ff3-8d72d2c33d08.jpg" width="202px" height="318px"></img>
<img src="https://user-images.githubusercontent.com/40492343/92086546-477f6580-ee05-11ea-92bd-ec03eafbd78a.jpg" width="202px" height="318px"></img>
<img src="https://user-images.githubusercontent.com/40492343/92086553-49492900-ee05-11ea-826e-49ad9c2d4c4d.jpg" width="202px" height="318px"></img>
<img src="https://user-images.githubusercontent.com/40492343/92086557-4a7a5600-ee05-11ea-9e69-0e3879633573.jpg" width="202px" height="318px"></img>
