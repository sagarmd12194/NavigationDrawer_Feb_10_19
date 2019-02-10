package com.example.mynavitest;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
         actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView=(NavigationView)findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        //default fragment added
        if (savedInstanceState ==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new accoutn_fragment()).commit();
        }

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Floating Button Pressed ...",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.account:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new accoutn_fragment()).commit();
            break;
            case R.id.setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new setting_fragment()).commit();
            break;
            case R.id.logout:
                Toast.makeText(this,"Logout button is pressed",Toast.LENGTH_LONG).show();
                break;
        }

        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);

       return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
      switch (itemId){
         case R.id.settings:
             getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new setting_fragment()).commit();
          break;
         case  R.id.logout:
             Toast.makeText(this,"Logout button is pressed",Toast.LENGTH_LONG).show();
          break;
  }
        return super.onOptionsItemSelected(item);
    }
}
