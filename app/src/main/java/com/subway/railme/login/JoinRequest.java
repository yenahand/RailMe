// 회원가입에서 입력 받은 값 요청 받는 클래스
package com.subway.railme.login;

import static android.os.Build.ID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//import com.subway.railme.databinding.ActivityJoinBinding;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class JoinRequest extends StringRequest {
//
//    final static private String URL = ""; // 파이어베이스 링크 첨부 예정
//    private Map<String, String>map;
//
//    public JoinRequest(EditText ID, EditText Password, EditText Nickname, Response.Listener<String> listener) {
//        super(Method.POST,URL,listener,null);
//
//        map = new HashMap<>();
//        map.put("ID", ID); // 사용자 ID
//        map.put("Password", Password); // 사용자 비밀번호
//        map.put("Nickname", Nickname); // 사용자 이름
//    }
//    @Override
//    protected Map<String, String>getParams() throws AuthFailureError{
//        return map;
//    }
//}
