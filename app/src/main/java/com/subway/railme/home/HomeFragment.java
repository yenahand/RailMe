/*노선도 부분 api연동해서 하나하나 구현하려면 겨울방학 내로는 못 끝낼 것 같다고 하시는데 이부분
   괜찮은 해결방안 있을까요? */
package com.subway.railme.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.subway.railme.R;
import com.subway.railme.home.API.remote.SearchTask;



public class HomeFragment extends Fragment implements SearchTask.SearchTaskListener {

    private EditText searchEditText;
    private TextView searchResultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchEditText = view.findViewById(R.id.searchStation);
        searchResultTextView = view.findViewById(R.id.tv_SearchResult);

        Button searchButton = view.findViewById(R.id.searchBT);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        return view;
    }


    private void performSearch() {
        // TextView와 SearchTaskListener를 모두 전달하여 SearchTask 인스턴스 생성
        new SearchTask(searchResultTextView, this).execute();
    }
    @Override
    public void onSearchTaskComplete(String result) {
        searchResultTextView.setText(result);
    }
}
