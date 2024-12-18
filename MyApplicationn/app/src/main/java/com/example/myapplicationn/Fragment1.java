package com.example.myapplicationn;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class Fragment1 extends Fragment {

    private static final String TAG = "Fragment1";
    RecyclerView recyclerView;
    NoteAdapter adapter;

    Context context;
    OnTabItemSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
        listener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        initUI(rootView);
        return rootView;
    }


    private void initUI(ViewGroup rootView) {

        Button todayWriteButton = rootView.findViewById(R.id.todayWriteButton);
        todayWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(1);
                }
            }
        });

        // MaterialButtonToggleGroup 설정
        MaterialButtonToggleGroup toggleGroup = rootView.findViewById(R.id.toggleGroup);
        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.button_list) {
                        Toast.makeText(getContext(), "목록 보기 선택됨", Toast.LENGTH_SHORT).show();
                        adapter.switchLayout(0); // 목록 보기
                    } else if (checkedId == R.id.button_grid) {
                        Toast.makeText(getContext(), "격자 보기 선택됨", Toast.LENGTH_SHORT).show();
                        adapter.switchLayout(1); // 격자 보기
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter();

        adapter.addItem(new Note(0, "0", "노원구 공릉동", "", "","오늘 너무 행복해!", "4", "", "12월 10일"));
        adapter.addItem(new Note(1, "1", "노원구 공릉동", "", "","친구와 재미있게 놀았어", "4", "capture1.jpg", "12월 11일"));
        adapter.addItem(new Note(2, "0", "노원구 공릉동", "", "","집에 왔는데 너무 피곤해 ㅠㅠ", "3", null, "12월 13일"));


        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);

                Log.d(TAG, "아이템 선택됨 : " + item.get_id());

                if (listener != null) {
                    listener.showFragment2(item);
                }
            }
        });
    }
}
