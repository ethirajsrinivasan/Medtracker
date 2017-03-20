package sg.edu.nus.iss.se.ft05.medipal.activities;

import android.app.DatePickerDialog;
import android.content.Context;
<<<<<<< HEAD
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
=======
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
>>>>>>> a6169a7... Personal bio added and shared Preference
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
<<<<<<< HEAD
import android.widget.TextView;
=======
>>>>>>> a6169a7... Personal bio added and shared Preference
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import sg.edu.nus.iss.se.ft05.medipal.R;
import sg.edu.nus.iss.se.ft05.medipal.constants.Constants;
<<<<<<< HEAD
import sg.edu.nus.iss.se.ft05.medipal.dao.PersonalBioDAO;
import sg.edu.nus.iss.se.ft05.medipal.dao.PersonalBioDAOImpl;
=======
import sg.edu.nus.iss.se.ft05.medipal.enums.BloodType;
>>>>>>> a6169a7... Personal bio added and shared Preference
import sg.edu.nus.iss.se.ft05.medipal.model.PersonalBio;

/**
 * @author Moushumi Seal
 */
public class PersonalBioActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private EditText mName, mDob, mIdNo, mAddress, mPostalCode, mHeight;
    private Spinner mSpn_bloodType;
    private Button mSaveBtn;
    private Calendar dateCalendar;
<<<<<<< HEAD
    private TextView mTv_bloodType;

=======
>>>>>>> a6169a7... Personal bio added and shared Preference

    private static final SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);

    DatePickerDialog datePickerDialog;

    private Context context;
    private PersonalBio personalBio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_bio);
<<<<<<< HEAD
        setTitle(Constants.TITLE_PERSONAL_BIO);
        Context context = getApplicationContext();
        findViewsById();
        setListeners();
        Bundle b = getIntent().getExtras();
        if(b != null){
            switch(b.getString(Constants.ACTION)){
                case Constants.VIEW:
                    mName.setEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);
                    mSaveBtn.setText(Constants.EDIT);
                    makeFieldsEditable(false);
                    getPersonalbioValuesById(b.getInt("userId"));
                    break;
                case Constants.NEW:
                    mSaveBtn.setText(Constants.SAVE);
                    break;
            }
        }
=======

        findViewsById();
        setListeners();
>>>>>>> a6169a7... Personal bio added and shared Preference
    }

    private void findViewsById(){
        mName = (EditText)findViewById(R.id.name);
        mDob = (EditText)findViewById(R.id.dob);
        mDob.setInputType(InputType.TYPE_NULL);
        mIdNo = (EditText)findViewById(R.id.idNo);
        mAddress = (EditText) findViewById(R.id.address);
        mPostalCode = (EditText) findViewById(R.id.postalCode);
        mHeight = (EditText) findViewById(R.id.height);
        mSpn_bloodType = (Spinner) findViewById(R.id.spn_bloodType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_type_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpn_bloodType.setAdapter(adapter);
<<<<<<< HEAD
        mTv_bloodType = (TextView) findViewById(R.id.tv_bloodType_value);
=======
>>>>>>> a6169a7... Personal bio added and shared Preference
        mSaveBtn = (Button) findViewById(R.id.savePersonalBio);
    }

    private void setListeners() {
        mSaveBtn.setOnClickListener(this);
        mDob.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateCalendar = Calendar.getInstance();
                dateCalendar.set(year, monthOfYear, dayOfMonth);
                mDob.setText(formatter.format(dateCalendar.getTime()));
            }
        },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.dob:
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.savePersonalBio:
<<<<<<< HEAD
                if(mSaveBtn.getText().equals(Constants.SAVE))
                 savePersonalbio();
                else if(mSaveBtn.getText().equals(Constants.EDIT)){
                    mSaveBtn.setText(Constants.UPDATE);
                    makeFieldsEditable(true);
                } else
                    updatePersonalBio();
                break;
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.dob:
                if(hasFocus) {
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();
                }
=======
                savePersonalbio();
>>>>>>> a6169a7... Personal bio added and shared Preference
                break;
        }
    }

<<<<<<< HEAD
    private void makeFieldsEditable(boolean enable) {
        mDob.setEnabled(enable);
        mDob.setOnClickListener(null);
        mIdNo.setEnabled(enable);
        mIdNo.setEnabled(enable);
        mAddress.setEnabled(enable);
        mPostalCode.setEnabled(enable);
        mHeight.setEnabled(enable);
        if(!enable) {
            mSpn_bloodType.setVisibility(View.GONE);
            mTv_bloodType.setVisibility(View.VISIBLE);
        } else {
            mSpn_bloodType.setVisibility(View.VISIBLE);
            mTv_bloodType.setVisibility(View.GONE);
        }
    }


    private void getPersonalbioValuesById(int id) {
        personalBio = PersonalBio.findById(getApplicationContext(), id);
        mName.setText(personalBio.getName());
        mDob.setText(personalBio.getDob());
        mIdNo.setText(personalBio.getIdNo());
        mAddress.setText(personalBio.getAddress());
        mPostalCode.setText(personalBio.getPostalCode());
        mHeight.setText(personalBio.getHeight());
        mSpn_bloodType.setSelection(Arrays.asList(
                getResources().getStringArray(R.array.blood_type_list))
                .indexOf(personalBio.getBloodType()));
        mTv_bloodType.setText(personalBio.getBloodType());
    }

    /**
     * Save Personal bio details
     */
=======
>>>>>>> a6169a7... Personal bio added and shared Preference
    public void savePersonalbio() {
        String name = mName.getText().toString();
        String dob = mDob.getText().toString();
        String idNo = mIdNo.getText().toString();
        String address = mAddress.getText().toString();
        String postalCode = mPostalCode.getText().toString();
        String height = mHeight.getText().toString();
        String bloodType = mSpn_bloodType.getSelectedItem().toString();
<<<<<<< HEAD
        personalBio = new PersonalBio(name,dob,idNo,address,postalCode,height,bloodType);
        context = getApplicationContext();
        PersonalBioDAO personalBioDAO = new PersonalBioDAOImpl(context);
        if(isValid()) {
            if (personalBio.save(context) == -1)
                Toast.makeText(context, R.string.insert_error, Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(context, R.string.add_success, Toast.LENGTH_SHORT).show();
                int id = personalBioDAO.findPersonalBioId(name, dob, idNo);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("personId", id);
                resultIntent.putExtra("personName", name);
                setResult(RESULT_OK, resultIntent);
            }
            finish();
        }
    }

    /**
     * Update Personal bio details
     */
    private void updatePersonalBio(){
        personalBio.setName(mName.getText().toString());
        personalBio.setDob(mDob.getText().toString());
        personalBio.setIdNo(mIdNo.getText().toString());
        personalBio.setAddress(mAddress.getText().toString());
        personalBio.setPostalCode(mPostalCode.getText().toString());
        personalBio.setHeight(mHeight.getText().toString());
        personalBio.setBloodType(mSpn_bloodType.getSelectedItem().toString());
        mTv_bloodType.setText(mSpn_bloodType.getSelectedItem().toString());
        context = getApplicationContext();
        if(isValid()) {
            if (personalBio.update(context) == -1) {
                Toast.makeText(context, R.string.insert_error, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(context, R.string.update_success, Toast.LENGTH_SHORT).show();
                makeFieldsEditable(false);
                mSaveBtn.setText(Constants.EDIT);
            }
        }
    }

    /**
     * Validating fields
     */
    private boolean isValid() {
        boolean isvalid = true;
        if(isMandatoryFieldsFilled()) {
            if (personalBio.getHeight().matches("^[0.]+?")) {
                mHeight.setError(Constants.INVALID_HEIGHT);
                mHeight.requestFocus();
                isvalid = false;
            }
            if(personalBio.getPostalCode().matches("^[0.]+?")) {
                mPostalCode.setError(Constants.INVALID_POSTAL_CODE);
                mPostalCode.requestFocus();
                isvalid = false;
            }
        }
        return isvalid;
    }

    /**
     * checking if mandatory fields are blank
     */
    private boolean isMandatoryFieldsFilled() {
        boolean isvalid = true;
        if (TextUtils.isEmpty(personalBio.getName())
                && TextUtils.isEmpty(personalBio.getDob())
                && TextUtils.isEmpty(personalBio.getIdNo())
                && TextUtils.isEmpty(personalBio.getAddress())
                && TextUtils.isEmpty(personalBio.getPostalCode())
                && TextUtils.isEmpty(personalBio.getHeight())) {
            AlertDialog.Builder warningDialog = new AlertDialog.Builder(this);
            warningDialog.setTitle(Constants.TITLE_WARNING);
            warningDialog.setMessage(R.string.warning);
            warningDialog.setPositiveButton(Constants.OK_BUTTON, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface alert, int which) {
                    alert.dismiss();
                }
            });
            warningDialog.show();
            isvalid = false;
        } else {
            if (TextUtils.isEmpty(personalBio.getName())) {
                mName.setError(Constants.EMPTY_PERSONAL_BIO_NAME);
                isvalid = false;
            } else if (TextUtils.isEmpty(personalBio.getDob())) {
                mDob.setError(Constants.EMPTY_DOB);
                mDob.requestFocus();
                isvalid = false;
            } else if (TextUtils.isEmpty(personalBio.getIdNo())) {
                mIdNo.setError(Constants.EMPTY_IDNO);
                mIdNo.requestFocus();
                isvalid = false;
            } else if (TextUtils.isEmpty(personalBio.getAddress())) {
                mAddress.setError(Constants.EMPTY_ADDRESS);
                mAddress.requestFocus();
                isvalid = false;
            } else if (TextUtils.isEmpty(personalBio.getPostalCode())) {
                mPostalCode.setError(Constants.EMPTY_POSTAL_CODE);
                mPostalCode.requestFocus();
                isvalid = false;
            }else if (TextUtils.isEmpty(personalBio.getHeight())) {
                mHeight.setError(Constants.EMPTY_HEIGHT);
                mHeight.requestFocus();
                isvalid = false;
            }
        }
        return isvalid;
=======
        Context context = getApplicationContext();
        PersonalBio personalBio = new PersonalBio(name,dob,idNo,address,postalCode,height,bloodType);
        if(personalBio.save(context) == -1)
            Toast.makeText(context,R.string.insert_error, Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(context, R.string.add_success, Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra(Constants.COMPLETION_STATUS,Constants.COMPLETION_STATUS_SUCCESS);
            setResult(RESULT_OK,resultIntent);
            finish();
        }

>>>>>>> a6169a7... Personal bio added and shared Preference
    }
}
