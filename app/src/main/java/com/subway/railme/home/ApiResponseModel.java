//API 응답을 자바 객체로 매핑하는 클래스

package com.subway.railme.home;

import com.google.gson.annotations.SerializedName;

public class ApiResponseModel {
    @SerializedName("lnCd")
    private String lnCd;

    @SerializedName("mreaWideCd")
    private String mreaWideCd;

    //기타

}