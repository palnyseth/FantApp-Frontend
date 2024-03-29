package no.nyseth.fantasd;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity a = this;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(a, R.id.nav_host_fragment)
                        .navigate(R.id.action_navToAddItem);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
               R.id.nav_home, R.id.nav_login, R.id.nav_create, R.id.nav_additem, R.id.nav_buyitem, R.id.nav_market, R.id.nav_item, R.id.nav_listing) //lrgg til ting her
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int menuId = destination.getId();
                switch(menuId) {
                    case R.id.nav_login:
                        Toast.makeText(MainActivity.this, "Logg inn", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_create:
                        Toast.makeText(MainActivity.this, "Registrering", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_additem:
                        Toast.makeText(MainActivity.this, "Legge ut", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_buyitem:
                        Toast.makeText(MainActivity.this, "Kjøp", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_item:
                        Toast.makeText(MainActivity.this, "Til items!", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_listing:
                        Toast.makeText(MainActivity.this, "greg", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    default:
                        fab.show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}