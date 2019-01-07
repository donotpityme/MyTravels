package com.example.administrator.mytravels;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.administrator.mytravels.base.BaseActivity;
import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.utils.MyDate;
import com.example.administrator.mytravels.utils.MyString;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import androidx.annotation.Nullable;

public class EditTravelsActivity extends BaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = EditTravelsActivity.class.getSimpleName();

    private EditText mTitleEt;
    private EditText mPlaceEt;
    private EditText mStartDtEt;
    private EditText mEndDtEt;
    private long mStartDt;
    private long mEndDt;
    private Place mPlace;
    private Travel mTravel;
    private boolean mInEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_travels);

        mTitleEt = findViewById(R.id.title_et);
        mStartDtEt = findViewById(R.id.start_dt);
        mEndDtEt = findViewById(R.id.end_dt);
        findViewById(R.id.start_dt_layout).setOnClickListener(this);
        mStartDtEt.setOnClickListener(this);
        findViewById(R.id.end_dt_layout).setOnClickListener(this);
        mEndDtEt.setOnClickListener(this);
        mPlaceEt = findViewById(R.id.place_et);
        findViewById(R.id.place_layout).setOnClickListener(this);
        mPlaceEt.setOnClickListener(this);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (BaseActivity.REQACTION_EDIT_TRAVEL.equals(getIntent().getAction())) {
            mInEditMode = true;
            setTitle("Edit Travel");
            mTravel = (Travel) getIntent().getExtras().getSerializable(REQKEY_TRAVEL);
            mTitleEt.setText(mTravel.getTitle());
            mStartDt = mTravel.getStartDt();
            mStartDtEt.setText(mTravel.getStartDtText());
            mEndDt = mTravel.getEndDt();
            mEndDtEt.setText(mTravel.getEndDtText());
            mPlaceEt.setText(mTravel.getPlaceName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnadd: {
                validate();
            }
            break;
            case R.id.start_dt_layout:
            case R.id.start_dt: {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setTag(v.getId());
                if (mEndDt > 0) {
                    dpd.getDatePicker().setMaxDate(mEndDt);
                }
                dpd.show();
            }
            break;
            case R.id.end_dt_layout:
            case R.id.end_dt: {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setTag(v.getId());
                if (mStartDt > 0) {
                    dpd.getDatePicker().setMinDate(mStartDt);
                }
                dpd.show();
            }
            break;
            case R.id.place_layout:
            case R.id.place_et: {
                try {
                    AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                            .build();
                    Intent intent
                            = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(typeFilter)
                            .build(this);
                    startActivityForResult(intent, REQCD_PLACE_AUTOCOMPLETE);
                } catch (GooglePlayServicesRepairableException e) {
                    GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(), 0).show();
                } catch (GooglePlayServicesNotAvailableException e) {
                    String message = "Google Play Services is not available: " + GoogleApiAvailability.getInstance().getErrorString(e.errorCode);
                    Log.e(TAG, message);
                    Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();
                }
            }
            break;

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Object tag = view.getTag();
        Calendar calendar = Calendar.getInstance();
        if (tag.equals(R.id.start_dt)) {
            calendar.set(year, month, dayOfMonth, 0, 0, 0);
            if (mEndDt > 0 && mEndDt < calendar.getTimeInMillis()) return;
            mStartDt = calendar.getTimeInMillis();
            mStartDtEt.setText(MyDate.getString(calendar.getTime()));
        } else {
            calendar.set(year, month, dayOfMonth, 23, 59, 59);
            if (mStartDt > 0 && mStartDt > calendar.getTimeInMillis()) return;
            mEndDt = calendar.getTimeInMillis();
            mEndDtEt.setText(MyDate.getString(calendar.getTime()));
        }
    }

    private void validate() {
        String title = mTitleEt.getText().toString();
        if (MyString.isEmpty(title)) {
            Snackbar.make(mTitleEt, "Travel Title is empty!", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (mStartDt == 0) {
            Snackbar.make(mTitleEt, "Start is empty!", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (!mInEditMode && mPlace == null) {
            Snackbar.make(mTitleEt, "City is empty!", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (mEndDt == 0) {
            Snackbar.make(mTitleEt, "Start is empty!", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (mTravel == null) {
            mTravel = new Travel(title);
        } else {
            mTravel.setTitle(title);
        }
        //mTravel = new Travel(title);
        mTravel.setStartDt(mStartDt);
        mTravel.setEndDt(mEndDt);
        if (mPlace != null) {
            mTravel.setPlaceId(mPlace.getId());
            mTravel.setPlaceName((String) mPlace.getName());
            mTravel.setPlaceAddr((String) mPlace.getAddress());
            mTravel.setPlaceLat(mPlace.getLatLng().latitude);
            mTravel.setPlaceLng(mPlace.getLatLng().longitude);
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra(REQKEY_TRAVEL, mTravel);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case REQCD_PLACE_AUTOCOMPLETE: {
                mPlace = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: " + mPlace);
                mPlaceEt.setText(mPlace.getName());
            }
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mInEditMode) {
            getMenuInflater().inflate(R.menu.menu_edittravel, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_travel_del: {
                showAlertOkCancel("Are you sure you want to delete?",
                        "All data for this travel will be permanently deleted.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra(REQKEY_TRAVEL, mTravel);
                                returnIntent.setAction(REQACTION_DEL_TRAVEL);
                                setResult(RESULT_OK, returnIntent);
                                finish();
                            }
                        }
                        , null);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
