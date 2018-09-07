package com.example.pisha.drawerlayout;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDLayout = findViewById(R.id.drawler);
        mToggle = new ActionBarDrawerToggle(this, mDLayout, R.string.open, R.string.close);
        mDLayout.addDrawerListener(mToggle);
        NavigationView nvDrawler = findViewById(R.id.nv);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawler);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void selectMenuItem(MenuItem menuItem){

        android.support.v4.app.Fragment myFragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()){
            case R.id.db:
                fragmentClass = Dashboard.class;
                break;
            case R.id.event:
                fragmentClass = Events.class;
                break;
            case R.id.search:
                fragmentClass = Search.class;
                break;
            case R.id.settings:
                fragmentClass = Settings.class;
                break;
            case R.id.activities:
                fragmentClass = Activities.class;
                break;
            default:
                fragmentClass = Dashboard.class;

        }

        try{
            myFragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();


        }catch (Exception e){
            e.printStackTrace();
        }

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectMenuItem(item);
                return true;
            }
        });
    }


}
