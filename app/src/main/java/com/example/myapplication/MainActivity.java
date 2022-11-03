package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.activities.About;
import com.example.myapplication.activities.Calculator_Activity;
import com.example.myapplication.activities.DatabaseTutorialActivity;
import com.example.myapplication.activities.Fragment_Activity;
import com.example.myapplication.activities.InputActivity;
import com.example.myapplication.activities.ListViewActivity;
import com.example.myapplication.activities.RecyclerView_Acticity;
import com.example.myapplication.activities.TicTacToe_Activity;
import com.example.myapplication.activities.ViewPagerAcitivty;
import com.example.myapplication.activities.ViewPagerGallery_Activity;
import com.example.myapplication.adapters.RecyclerViewAdapter;
import com.example.myapplication.models.RecycleViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private RecyclerView list_elements;
    private RecyclerViewAdapter recyclerViewAdapter;
    private TypedArray photoArray;
    private String[] itemName;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.about_id:
              startActivity(new Intent(MainActivity.this, About.class));
              break;

            case R.id.share_id:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello i am sending this text from my app.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;


        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        drawerLayout = findViewById(R.id.drawer_layout);


        list_elements = findViewById(R.id.list_elements);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbox App");
        setSupportActionBar(toolbar);


        initListView();
        initNav();


    }

    private void initNav() {

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav_drawer,R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    private void initListView() {

        itemName = getResources().getStringArray(R.array.main_activity_list_array);
        photoArray = getResources().obtainTypedArray(R.array.main_activity_photos);

        List<RecycleViewModel> myNormalList = new ArrayList<>();

        for(int i = 0; i < itemName.length;i++){

            RecycleViewModel recycleViewModel = new RecycleViewModel(photoArray.getResourceId(i,-1), i + 1 + "." + itemName[i]);
            myNormalList.add(recycleViewModel);

        }

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,myNormalList);
        recyclerViewAdapter.setSetIconColor(Color.BLACK);
        recyclerViewAdapter.setmClickListener(this);
        list_elements.setLayoutManager(new LinearLayoutManager(this));
        list_elements.setAdapter(recyclerViewAdapter);

    }


    @Override
    public void onItemClick(View view, int position) {

        switch (position){

            case 0:
                startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                break;

            case 1:
                startActivity(new Intent(MainActivity.this, TicTacToe_Activity.class));
                break;

            case 2:
                startActivity(new Intent(MainActivity.this, Calculator_Activity.class));
                break;

            case 3:
                startActivity(new Intent(MainActivity.this, RecyclerView_Acticity.class));
                break;

            case 4:
                startActivity(new Intent(MainActivity.this, InputActivity.class));
                break;

            case 5:
                startActivity(new Intent(MainActivity.this, Fragment_Activity.class));
                break;

            case 6:
                startActivity(new Intent(MainActivity.this, ViewPagerAcitivty.class));
                break;

            case 7:
                startActivity(new Intent(MainActivity.this, ViewPagerGallery_Activity.class));
                break;

            case 8:
                startActivity(new Intent(MainActivity.this, DatabaseTutorialActivity.class));
                break;

            default:
                break;

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);

        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                switch (item.getItemId()){

                    case R.id.listview_id:
                        startActivity(new Intent(MainActivity.this,ListViewActivity.class));
                        break;
                    case R.id.tictactoe_id:
                        startActivity(new Intent(MainActivity.this,TicTacToe_Activity.class));
                        break;
                    case R.id.calculator_id:
                        startActivity(new Intent(MainActivity.this,Calculator_Activity.class));
                        break;
                    case R.id.recyclerview_id:
                        startActivity(new Intent(MainActivity.this,RecyclerView.class));
                        break;
                    case R.id.input_text_id:
                        startActivity(new Intent(MainActivity.this,InputActivity.class));
                        break;
                    case R.id.fragment_id:
                        startActivity(new Intent(MainActivity.this,Fragment_Activity.class));
                        break;
                    case R.id.view_pager_id:
                        startActivity(new Intent(MainActivity.this,ViewPagerAcitivty.class));
                        break;
                    case R.id.view_pager_gallery_id:
                        startActivity(new Intent(MainActivity.this,ViewPagerGallery_Activity.class));
                        break;
                    case R.id.data_base_id:
                        startActivity(new Intent(MainActivity.this,DatabaseTutorialActivity.class));
                        break;
                    case R.id.about_id:
                        startActivity(new Intent(MainActivity.this,About.class));
                        break;


                }


            }
        },300);

        return true;
    }
}