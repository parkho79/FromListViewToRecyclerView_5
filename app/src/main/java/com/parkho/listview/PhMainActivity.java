package com.parkho.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhMainActivity extends AppCompatActivity {

    // List item
    private List<PhCountryItem> mItemList;

    // ListView adapter
    private PhCountryArrayAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindList();
    }

    private void bindList() {
        mItemList = new ArrayList<>();
        mItemList.add(new PhCountryItem(R.drawable.ico_southkorea, "Korea"));
        mItemList.add(new PhCountryItem(R.drawable.ico_china, "China"));
        mItemList.add(new PhCountryItem(R.drawable.ico_japan, "Japan"));
        mItemList.add(new PhCountryItem(R.drawable.ico_unitedstates, "America"));
        mItemList.add(new PhCountryItem(R.drawable.ico_newzealand, "NewZealand"));

        mCountryAdapter = new PhCountryArrayAdapter(this, mItemList);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mCountryAdapter);

        // List item click event 처리
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                Toast.makeText(PhMainActivity.this, item.getCountry() + " Click event", Toast.LENGTH_SHORT).show();
            }
        });

        // List item long click event 처리
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> a_parent, View a_view, final int a_position, long a_id) {
                // Popup menu 생성
                PopupMenu popup = new PopupMenu(PhMainActivity.this, a_view);
                getMenuInflater().inflate(R.menu.main_list_menu, popup.getMenu());

                // Popup menu click event 처리
                popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem a_item) {
                        final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                        switch (a_item.getItemId()) {
                            case R.id.action_insert:
                                Toast.makeText(PhMainActivity.this, item.getCountry() + " " + getString(R.string.insert), Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.action_delete:
                                Toast.makeText(PhMainActivity.this, item.getCountry() + " " + getString(R.string.delete), Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.action_modify:
                                Toast.makeText(PhMainActivity.this, item.getCountry() + " " + getString(R.string.modify), Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.action_info:
                                Toast.makeText(PhMainActivity.this, item.getCountry() + " " + getString(R.string.info), Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                break;
                        }
                        return false;
                    }
                });

                // Popup 보이기
                popup.show();

                return true;
            }
        });
    }
}