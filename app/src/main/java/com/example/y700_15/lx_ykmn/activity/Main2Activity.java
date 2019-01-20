package com.example.y700_15.lx_ykmn.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.y700_15.lx_ykmn.R;
import com.example.y700_15.lx_ykmn.fragment.FindmeFragment;
import com.example.y700_15.lx_ykmn.fragment.HomeFragment;
import com.example.y700_15.lx_ykmn.fragment.MyFragment;
import com.example.y700_15.lx_ykmn.fragment.SCartFragment;
import com.example.y700_15.lx_ykmn.fragment.TypeFragment;

public class Main2Activity extends AppCompatActivity {

    private ViewPager pager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.type:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.findme:
                    pager.setCurrentItem(2);
                    return true;
                case R.id.scart:
                    pager.setCurrentItem(3);
                    return true;
                case R.id.my:
                    pager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pager = findViewById(R.id.pagers);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new TypeFragment();
                    case 2:
                        return new FindmeFragment();
                    case 3:
                        return new SCartFragment();
                        default:
                            return new MyFragment();
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

}
