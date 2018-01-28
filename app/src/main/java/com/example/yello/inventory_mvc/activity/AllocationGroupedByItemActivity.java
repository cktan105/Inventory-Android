package com.example.yello.inventory_mvc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yello.inventory_mvc.R;
import com.example.yello.inventory_mvc.model.AllocationViewModel;
import com.example.yello.inventory_mvc.model.Retrieval_Item;
import com.example.yello.inventory_mvc.utility.Key;

import org.w3c.dom.Text;

import java.util.List;

public class AllocationGroupedByItemActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_grouped_by_item);
        final ListView lv = findViewById(R.id.listViewAllocationGroup);
        final String itemCode = getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_5_ITEMCODE);
/*        TextView code = (TextView) findViewById();*/
        TextView itemDescrp = (TextView) findViewById(R.id.textView11);
        itemDescrp.setText(getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_1_DESCRIPTION));


        new AsyncTask<String, Void, List<AllocationViewModel>>() {

            @Override
            protected List<AllocationViewModel> doInBackground(String... params) {
                return AllocationViewModel.getAllocationListGroupedByItem(params[0]);
            }

            @Override
            protected void onPostExecute(List<AllocationViewModel> result) {

                SimpleAdapter adapter =
                        new SimpleAdapter(getApplicationContext(), result,
                                R.layout.allocation_group_row,
                                new String[]{"orderNum", "departmentCode", "qtyReq"},
                                new int[]{R.id.textView5, R.id.textView6, R.id.textView7});

                lv.setAdapter(adapter);
            }
        }.execute(itemCode);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AllocationViewModel  allocationItem = (AllocationViewModel) parent.getAdapter().getItem(position);

        Intent intent = new Intent(getApplicationContext(),AllocationUpdateActivity.class );
        intent.putExtra("orderNum", allocationItem.get("orderNum"));
        intent.putExtra("itemCode", getIntent().getExtras().getString(Key.RETRIEVAL_ITEM_5_ITEMCODE));
        startActivity(intent);

    }
    }