package sg.edu.nus.iss.se.ft05.medipal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sg.edu.nus.iss.se.ft05.medipal.R;

/**
 * Created by ashish Katre.
 */

public class ICEContactsActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * on create method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ice_contacts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * On click method
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        if (R.id.button_ice_addition == view.getId()) {

            //saveOrUpdateContacts();
        }
    }
}
