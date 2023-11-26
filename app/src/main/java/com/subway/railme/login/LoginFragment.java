package com.subway.railme.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subway.railme.R;

public class LoginFragment extends Fragment {

 // 혜림씨 로그인 페이지를 프래그먼트로 만들면 화면전환하는게 어려울거야 일단 기존에 했던 XML 코드 복사해서 다른곳에 두고 프래그먼트랑 엑티비티 만들어 놨던거 삭제하고
    //다시 붙여넣기 해서 XML 코드만 살리면 될듯 !
 // 액티비티로 만들어서 로그인을 했던 여부에 따라서 예를들면 ->
    //로그인을 했다면 MainActivity로 넘어가게 else 아니면 로그인 화면이 뜨게 생각해서 구현 하면 쉬울듯 !
    //프래그먼트에서 사용하지 않는거 지워 줄테니까 일단 이 화면은 XML 코드만 살려서 엑티비티로 만들자.
    //그리고 뷰 바인딩을 통한 XML 연결 공부해주면 좋을거같아 다른 프래그먼트 살펴보면서 해보면 좋을듯 ! 이해 안되는거 있으면 바로 연락 ㄱㄱ
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}