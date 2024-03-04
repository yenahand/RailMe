// 로그인에서 입력 받은 값 요청 받는 클래스
package com.subway.railme.login;

import android.widget.EditText;

//// 오류 수정 예정
//import com.android.volley.AuthFailureError;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class LoginResult extends StringRequest {
//    final static private String URL = ""; // 파이어베이스 링크 첨부 예정
//    private Map<String, String> map;
//
//    public void LoginRequest(EditText ID, EditText Password, Response.Listener<String> listener) {
//        super(Method.POST,URL,listener,null);
//
//        map = new HashMap<>();
//        map.put("ID", ID); // 사용자 ID
//        map.put("Password", Password); // 사용자 비밀번호
//    }
//
//    @Override
//    protected Map<String, String>getParams() throws AuthFailureError {
//        return map;
//    }
//}
