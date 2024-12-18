# Single Diary

메모처럼 간단하게 한 줄 작성하는 일기장 앱입니다. 사진과 그날의 날씨, 기분을 추가할 수 있습니다.

## 기능

1. 일기 리스트 조회
    
    일기 앱이 처음 실행되면 저장된 일기들이 첫 화면에 보이게 만듭니다. (SQL을 만들어 실행) 일기 리스트 화면에서 사용자는 새로운 일기를 삭제할 수 있는 입력 화면으로 이동
   
   <img src ="https://github.com/user-attachments/assets/293198bc-c46c-44e0-ba4d-c1fe77b3342f" Height="400"/>

3. 일기 작성
    
    새로운 일기를 작성하려면 입력화면으로 전환되어야 함. 일기 작성 후 저장 버튼을 클릭하면 데이터베이스에 일기 내용을 저장합니다.
    
    <img src ="https://github.com/user-attachments/assets/29113612-9cd8-4f6e-9fe7-767591b8b774" Height="400"/>

    
4. 일기 수정
    
    일기 리스트 화면에서 작성했던 일기 하나를 선택하면 입력 화면으로 전환되면서 선택한 일기의 내용이 나타남. 수정 후 저장하여 데이터베이스 업데이트
    
5. 일기 삭제
    
    일기 리스트 화면에서 삭제하려는 일기를 선택하면 입력화면으로 전환. 삭제버튼 클릭 시 일기 삭제
    

# API

## **1. Android Jetpack 라이브러리**

### **(1) RecyclerView**

- 데이터 목록을 효율적으로 표시하기 위해 사용되었습니다.
- 각 항목을 사용자 지정 레이아웃으로 정의하여 동적으로 생성 및 관리합니다.

### **(2) Room Database (SQLite Wrapper)**

- 데이터를 로컬 데이터베이스에 저장하고 관리하기 위해 SQLite를 간소화한 Room을 사용했습니다.
- DAO(Data Access Object)를 통해 SQL 명령문 없이 데이터 조작을 수행합니다.

### **(3) Lifecycle 및 ViewModel**

- `ViewModel`은 데이터를 UI와 독립적으로 관리하기 위해 사용됩니다.
- `Lifecycle`은 Fragment 및 Activity의 생명 주기를 효율적으로 관리합니다.

## **2. Google Material Components**

- **Material Design** 표준에 기반한 UI 요소를 구현하기 위해 사용되었습니다.
    - **MaterialButton**: 커스터마이징된 버튼.
    - **BottomNavigationView**: 화면 하단 네비게이션 메뉴.
    - **MaterialButtonToggleGroup**: 다중 버튼 선택 그룹.

## **3. Google Play Services**

### **(1) 위치 서비스**

- 사용자의 현재 위치를 가져오기 위해 Google Play Services의 위치 관련 API를 사용했습니다.
- `ACCESS_FINE_LOCATION` 및 `ACCESS_COARSE_LOCATION` 권한이 필요합니다.

## **4. Gson**

- JSON 데이터를 직렬화/역직렬화하는 데 사용되었습니다.
- 서버로부터 데이터를 받아 앱에서 파싱하고 처리하기 위해 활용했습니다.

## **5. Glide 또는 Picasso (이미지 처리 라이브러리)**

- 로컬 또는 외부에서 가져온 이미지를 RecyclerView 및 ImageView에 효율적으로 로드하고 캐싱합니다.

<img src = "https://github.com/user-attachments/assets/a8579aac-432f-4722-b379-110a981d7bc3" Height="400"/>


## **6. MPAndroidChart**

- 데이터를 시각화하여 그래프와 차트를 표시하는 데 사용되었습니다.
- 날짜 별 작성한 차트, 기분의 통계에 사용되었습니다.
    
    
    <img src = "https://github.com/user-attachments/assets/5c7cc22b-c182-4620-be01-0329e8dbdc14" Height="400"/>

    

## **7. Android Volley**

- 서버와의 네트워크 통신을 처리하기 위해 사용되었습니다.
- HTTP 요청(GET, POST 등)을 간편하게 보낼 수 있는 API를 제공합니다.

## reference

<Do it! 안드로이드 앱 프로그래밍 - 개정 7판>: 다이어리 앱 만들기 부분을 참고했습니다.

gpt 사용
