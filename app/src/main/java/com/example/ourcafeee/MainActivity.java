package com.example.ourcafeee;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static ArrayList<String> foodName=new ArrayList<String>();
    public static Button numberOfItems;
    public static int sizeOfList;
    public static String owner_number="085824293707";
    public static int count_items=0;
    com.example.ourcafeee.DataBaseHelperAddCart dataBaseHelperAddCart;
    /**
     * The {@link androidx.viewpager.widget.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link androidx.fragment.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberOfItems=findViewById(R.id.numberOfItems);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+owner_number));
                if(ActivityCompat.checkSelfPermission(com.example.ourcafeee.MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(com.example.ourcafeee.MainActivity.this, "izinkan untuk pangilan", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(com.example.ourcafeee.MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    startActivity(intent);
                }
            }
        });
        image1=(ImageView) findViewById(R.id.image1);
        image1.setOnClickListener(this);

        dataBaseHelperAddCart=new com.example.ourcafeee.DataBaseHelperAddCart(this);


    }

    public void onStart()
    {
        super.onStart();
        count_items=dataBaseHelperAddCart.dataPresent();
        numberOfItems.setText(Integer.toString(count_items));
    }

    public void onClick( View v) {
            PopupMenu popupMenu = new PopupMenu(com.example.ourcafeee.MainActivity.this, image1);
            popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    int id = item.getItemId();

                    //noinspection SimplifiableIfStatement
                    if (id == R.id.LoginOrSignUp) {
                        Intent intent=new Intent(com.example.ourcafeee.MainActivity.this,AddAddress.class);
                        startActivity(intent);
                        return true;
                    }
                   else if (id == R.id.action_feedback) {
                        Intent intent=new Intent(com.example.ourcafeee.MainActivity.this,Feedback.class);
                        startActivity(intent);
                        return true;
                    }
                    else if (id == R.id.orders) {
                        Intent intent=new Intent(com.example.ourcafeee.MainActivity.this, com.example.ourcafeee.YourOrders.class);
                        startActivity(intent);
                        return true;
                    }
                    else if (id == R.id.aboutUs) {
                        Intent intent=new Intent(com.example.ourcafeee.MainActivity.this,AboutUs.class);
                        startActivity(intent);
                        return true;
                    }


                    return true;
                }
            });
            popupMenu.show();

    }

    /**
     * A {@link androidx.fragment.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    com.example.ourcafeee.Breakfast breakfast = new com.example.ourcafeee.Breakfast();
                    return breakfast;
                case 1:
                    com.example.ourcafeee.LunchAndDinner chatsFragment = new com.example.ourcafeee.LunchAndDinner();
                    return chatsFragment;
                case 2:
                    com.example.ourcafeee.PizzaAndDessert contactsFragments = new com.example.ourcafeee.PizzaAndDessert();
                    return contactsFragments;
            }
            return null;

        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Breakfast";
                case 1:
                    return "Lunch And Dinner";
                case 2:
                    return "Pizza and Dessert";

            }
            return null;
        }
    }
    public  void goToast(View view)
    {
        Intent intent = new Intent(getApplicationContext(), HomemadeToast.class);
        startActivity(intent);
    }
    public  void goGarnole(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Garnole.class);
        startActivity(intent);
    }
    public  void goEggs(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Eggs.class);
        startActivity(intent);
    }
    public  void goFruits(View view)
    {
        Intent intent = new Intent(getApplicationContext(), FreshFruitSalad.class);
        startActivity(intent);

    }
    public  void goSideOrder(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SideOrder.class);
        startActivity(intent);
    }
    public  void goOats(View view)
    {
        Intent intent = new Intent(getApplicationContext(), OatMealPorridge.class);
        startActivity(intent);
    }
    public void goCheckOut(View view)
    {
        Intent intent=new Intent(getApplicationContext(), CheckOut.class);
        startActivity(intent);
    }
    public void openSalad(View view)
    {
        Intent intent=new Intent(view.getContext(), Salad.class);
        startActivity(intent);
    }
    public void openHummus(View view)
    {
        Intent intent=new Intent(view.getContext(), Hummus.class);
        startActivity(intent);
    }public void openSoup(View view)
    {
        Intent intent=new Intent(view.getContext(), Soup.class);
        startActivity(intent);
    }public void openSideOrderLunchAndDinner(View view)
    {
        Intent intent=new Intent(view.getContext(), SideOrders.class);
        startActivity(intent);
    }public void openPastaAndSpaghetti(View view)
    {
        Intent intent=new Intent(view.getContext(), PastaAndSpaghetti.class);
        startActivity(intent);
    }
    public void openPizza(View view)
    {
        Intent intent=new Intent(view.getContext(), Pizza.class);
        startActivity(intent);
    }
    public void openDecadentDessert(View view)
    {
        Intent intent=new Intent(view.getContext(), DecadentDessert.class);
        startActivity(intent);
    }
}
