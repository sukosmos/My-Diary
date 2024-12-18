plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myapplicationn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplicationn"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // 기본 AndroidX 라이브러리
    implementation("androidx.appcompat:appcompat:1.6.1") // AppCompat 최신 버전
    implementation("com.google.android.material:material:1.9.0") // Material Design
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // ConstraintLayout 최신 버전
    implementation("androidx.recyclerview:recyclerview:1.3.1") // RecyclerView 최신 버전
    implementation("androidx.cardview:cardview:1.0.0") // CardView

    // 테스트용 라이브러리
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // 추가 라이브러리
    implementation("com.android.volley:volley:1.2.1") // Volley 네트워크 라이브러리
    implementation("com.google.code.gson:gson:2.10.1") // Gson 최신 버전
    implementation("com.stanfy:gson-xml-java:0.1.7") // XML 파싱을 위한 Gson
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0") // 그래프 라이브러리 최신 안정화 버전
    implementation("com.github.pedroSG94:AutoPermissions:1.0.3") // 권한 라이브러리
    implementation("com.github.channguyen:rsv:1.0.1") // RSV 라이브러리
}

