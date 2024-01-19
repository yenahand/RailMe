package com.subway.railme;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// 회원가입에서 입력 받은 값 요청 받는 클래스
public class JoinRequest extends StringRequest {
    final static private String URL = ""; // 파이어베이스 링크 첨부 예정
    private Map<String, String>map;

    public JoinRequest(String userID, String userPassword, String userName, Response.Listener<String> listener) {
        super(Method.POST,URL,listener,null);

        map = new HashMap<>();
        map.put("userID", userID); // 사용자 ID
        map.put("userPassword", userPassword); // 사용자 비밀번호
        map.put("userName", userName); // 사용자 이름
    }
    @Override
    protected Map<String, String>getParams() throws AuthFailureError{
        return map;
    }
}
