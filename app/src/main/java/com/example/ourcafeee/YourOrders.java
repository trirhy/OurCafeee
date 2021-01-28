package com.example.ourcafeee;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class YourOrders extends AppCompatActivity {
    com.example.ourcafeee.DatabaseHelperYourOrders databaseHelperYourOrders;
    List<Orders> data1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);
        listView=findViewById(R.id.orderList);
        databaseHelperYourOrders=new com.example.ourcafeee.DatabaseHelperYourOrders(this);
        data1=new ArrayList<>();
    }
    public void onStart()
    {
        super.onStart();
        data1=databaseHelperYourOrders.getOrderData();
        com.example.ourcafeee.OrderAdapter adapter=new com.example.ourcafeee.OrderAdapter(com.example.ourcafeee.YourOrders.this,data1);
        listView.setAdapter(adapter);

    }


}
