/*노선도 부분 api연동해서 하나하나 구현하려면 겨울방학 내로는 못 끝낼 것 같다고 하시는데 이부분
   괜찮은 해결방안 있을까요? */
package com.subway.railme.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.subway.railme.R;
import com.subway.railme.home.API.SearchTask;



public class HomeFragment extends Fragment {

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
                executeSearch();
            }
        });

        return view;
    }

    private void executeSearch() {
        String searchKeyword = searchEditText.getText().toString();
        if (!searchKeyword.isEmpty()) {
            // AsyncTask 실행
            new SearchTask(searchResultTextView).execute(searchKeyword);
        } else {
            Toast.makeText(getContext(), "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
