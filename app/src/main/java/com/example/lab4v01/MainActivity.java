package com.example.lab4v01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TabHost;
import android.widget.AdapterView;
import android.app.TabActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity {
    private Context thiscontext;
    private DatabaseHelper db;
    private ArrayList<Restaurant> retaurantsList;
    private RestaurantAdapter adapter ;
    private Button btnSave;
    private EditText name,address;
    private RadioGroup type;
    private ListView list;
    private int countR = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(MainActivity.this);
        countR = db.countRestaurants();
        //anh xa
        btnSave = (Button)findViewById(R.id.btnSave);
        name = (EditText)findViewById(R.id.ediName);
        address = (EditText)findViewById(R.id.ediAddress);
        type = (RadioGroup)findViewById(R.id.radioGroup1);
        list = (ListView)findViewById(R.id.restaurants);
        retaurantsList = new ArrayList<>();
        if(countR > 0){
            retaurantsList = db.getAllRestaurants();
            Toast.makeText(MainActivity.this,"Số nhà hàng trên database: "+countR,Toast.LENGTH_LONG).show();
        }
        adapter = new RestaurantAdapter(this,R.layout.row,retaurantsList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(onListClick);
        //bo sung  cho tab
        TabHost.TabSpec spec  = getTabHost().newTabSpec("1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);

        spec = getTabHost().newTabSpec("2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details",getResources().getDrawable(R.drawable.details));
        getTabHost().addTab(spec);
        getTabHost().getCurrentTab();
        getTabHost().setCurrentTab(2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restaurant r = new Restaurant();
                r.setName(name.getText().toString());
                r.setAddress(address.getText().toString());
                switch (type.getCheckedRadioButtonId()){
                    case R.id.radioTake_out:
                        r.setType("Take out");
                        break;
                    case R.id.radioSit_down:
                        r.setType("Sit down");
                        break;
                    case R.id.radioDelivery:
                        r.setType("Delivery");
                        break;
                }
                //toast dialog thong bao
                String msg = name.getText().toString() +" - "+ address.getText().toString()+" - "+ r.getType().toString();
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
                //them restaurant moi
                retaurantsList.add(r);
                db.insert(r.getName(),r.getAddress(),r.getType());
                adapter.notifyDataSetChanged();

            }
        });
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?>parent,View view,int position ,long id){
            Restaurant r = retaurantsList.get(position);//lay item dang duoc chon
            EditText name,address;
            RadioGroup type;

            //tham chieu
            name = (EditText)findViewById(R.id.ediName);
            address = (EditText)findViewById(R.id.ediAddress);
            type = (RadioGroup)findViewById(R.id.radioGroup1);

            //thiet lap thong tin cho tab details
            name.setText(r.getName());
            address.setText(r.getAddress());
            if(r.getType().equals("Sit down"))
                type.check(R.id.radioSit_down);
            else if(r.getType().equals("Take out"))
                type.check(R.id.radioTake_out);
            else
                type.check((R.id.radioDelivery));
            String msg = "You click in "+name.getText()+" - "+address.getText();
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            //bo sung lenh de chuyen ve tab details
            getTabHost().setCurrentTab(1);
        }
    };
}
