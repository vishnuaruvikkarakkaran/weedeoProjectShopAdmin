package com.weedeo.shopadmin.modules.shopTimming;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.base.BaseActivity;
import com.weedeo.shopadmin.model.request.ShopTimingModel;
import com.weedeo.shopadmin.model.response.ShopTimmingUpdation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class ShopTimming extends BaseActivity implements iShopTiming.ActivityView {

    private Context mContext;

    @BindView(R.id.checkboxMonday)
    CheckBox checkboxMonday;
    @BindView(R.id.checkboxTuesday)
    CheckBox checkboxTuesday;
    @BindView(R.id.checkboxWednesday)
    CheckBox checkboxWednesday;
    @BindView(R.id.checkboxThursday)
    CheckBox checkboxThursday;
    @BindView(R.id.checkboxFriday)
    CheckBox checkboxFriday;
    @BindView(R.id.checkboxSaturday)
    CheckBox checkboxSaturday;
    @BindView(R.id.checkboxSunday)
    CheckBox checkboxSunday;


    /* Textview's for Initial OpenTime */
    @BindView(R.id.tvMondayOpenTime)
    TextView tvMondayOpenTime;
    @BindView(R.id.tvTuesdayOpenTime)
    TextView tvTuesdayOpenTime;
    @BindView(R.id.tvWednesdayOpenTime)
    TextView tvWednesdayOpenTime;
    @BindView(R.id.tvThursdayOpenTime)
    TextView tvThursdayOpenTime;
    @BindView(R.id.tvFridayOpenTime)
    TextView tvFridayOpenTime;
    @BindView(R.id.tvSaturdayOpenTime)
    TextView tvSaturdayOpenTime;
    @BindView(R.id.tvSundayOpenTime)
    TextView tvSundayOpenTime;

    /* Textview's for Initial CloseTime */
    @BindView(R.id.tvMondayCloseTime)
    TextView tvMondayCloseTime;
    @BindView(R.id.tvTuesdayCloseTime)
    TextView tvTuesdayCloseTime;
    @BindView(R.id.tvWednesdayCloseTime)
    TextView tvWednesdayCloseTime;
    @BindView(R.id.tvThursdayCloseTime)
    TextView tvThursdayCloseTime;
    @BindView(R.id.tvFridayCloseTime)
    TextView tvFridayCloseTime;
    @BindView(R.id.tvSaturdayCloseTime)
    TextView tvSaturdayCloseTime;
    @BindView(R.id.tvSundayCloseTime)
    TextView tvSundayCloseTime;


    /* Imageview's, To add Second time */
    @BindView(R.id.ivTimeEditMonday)
    ImageView ivTimeEditMonday;
    @BindView(R.id.ivTimeEditTuesday)
    ImageView ivTimeEditTuesday;
    @BindView(R.id.ivTimeEditWednesday)
    ImageView ivTimeEditWednesday;
    @BindView(R.id.ivTimeEditThursday)
    ImageView ivTimeEditThursday;
    @BindView(R.id.ivTimeEditFriday)
    ImageView ivTimeEditFriday;
    @BindView(R.id.ivTimeEditSaturday)
    ImageView ivTimeEditSaturday;
    @BindView(R.id.ivTimeEditSunday)
    ImageView ivTimeEditSunday;

    /* Linearlayout's for Second time*/
    @BindView(R.id.llMondaySecondTiming)
    LinearLayout llMondaySecondTiming;
    @BindView(R.id.llTuesdaySecondTiming)
    LinearLayout llTuesdaySecondTiming;
    @BindView(R.id.llWednesdaySecondTiming)
    LinearLayout llWednesdaySecondTiming;
    @BindView(R.id.llThursdaySecondTiming)
    LinearLayout llThursdaySecondTiming;
    @BindView(R.id.llFridaySecondTiming)
    LinearLayout llFridaySecondTiming;
    @BindView(R.id.llSaturdaySecondTiming)
    LinearLayout llSaturdaySecondTiming;
    @BindView(R.id.llSundaySecondTiming)
    LinearLayout llSundaySecondTiming;

    /* Textview's for Open Second Time */
    @BindView(R.id.tvMondayOpenSecondTime)
    TextView tvMondayOpenSecondTime;
    @BindView(R.id.tvTuesdayOpenSecondTime)
    TextView tvTuesdayOpenSecondTime;
    @BindView(R.id.tvWednesdayOpenSecondTime)
    TextView tvWednesdayOpenSecondTime;
    @BindView(R.id.tvThursdayOpenSecondTime)
    TextView tvThursdayOpenSecondTime;
    @BindView(R.id.tvFridayOpenSecondTime)
    TextView tvFridayOpenSecondTime;
    @BindView(R.id.tvSaturdayOpenSecondTime)
    TextView tvSaturdayOpenSecondTime;
    @BindView(R.id.tvSundayOpenSecondTime)
    TextView tvSundayOpenSecondTime;

    /* Textview's for Close Second Time */
    @BindView(R.id.tvMondayCloseSecondTime)
    TextView tvMondayCloseSecondTime;
    @BindView(R.id.tvTuesdayCloseSecondTime)
    TextView tvTuesdayCloseSecondTime;
    @BindView(R.id.tvWednesdaycloseSecondTime)
    TextView tvWednesdaycloseSecondTime;
    @BindView(R.id.tvThursdayCloseSecondTime)
    TextView tvThursdayCloseSecondTime;
    @BindView(R.id.tvFridayCloseSecondTime)
    TextView tvFridayCloseSecondTime;
    @BindView(R.id.tvSaturdayCloseSecondTime)
    TextView tvSaturdayCloseSecondTime;
    @BindView(R.id.tvSundayCloseSecondTime)
    TextView tvSundayCloseSecondTime;

    @BindView(R.id.updateTiming)
    Button updateTiming;

    String strMonday;
    String strTuesday;
    String strWednesday;
    String strThursday;
    String strFriday;
    String strSaturday;
    String strSunday;

    boolean firstTimeOpen = true;
    boolean firstTimeClose = true;

    ShopTimingPresenter shopTimingPresenter;

    ShopTimmingUpdation shopTimmingUpdation;
    List<ShopTimmingUpdation> shopTimmingUpdationListMonday = new ArrayList<>();
    ShopTimingModel shopTimingModel = new ShopTimingModel();

    ShopTimingModel.ActiveTimesBean.MondayBean mondayBean = new ShopTimingModel.ActiveTimesBean.MondayBean();
    ShopTimingModel.ActiveTimesBean.TuesdayBean tuesdayBean = new ShopTimingModel.ActiveTimesBean.TuesdayBean();
    ShopTimingModel.ActiveTimesBean.WednesdayBean wednesdayBean = new ShopTimingModel.ActiveTimesBean.WednesdayBean();
    ShopTimingModel.ActiveTimesBean.ThursdayBean thursdayBean = new ShopTimingModel.ActiveTimesBean.ThursdayBean();
    ShopTimingModel.ActiveTimesBean.FridayBean fridayBean = new ShopTimingModel.ActiveTimesBean.FridayBean();
    ShopTimingModel.ActiveTimesBean.SaturdayBean saturdayBean = new ShopTimingModel.ActiveTimesBean.SaturdayBean();
    ShopTimingModel.ActiveTimesBean.SundayBean sundayBean = new ShopTimingModel.ActiveTimesBean.SundayBean();

    List<ShopTimingModel.ActiveTimesBean.MondayBean.TimingBean> timingBeanList = new ArrayList<>();
    List<ShopTimingModel.ActiveTimesBean.TuesdayBean.TimingBeanX> timingBeanXArrayList = new ArrayList<>();
    List<ShopTimingModel.ActiveTimesBean.WednesdayBean.TimingBeanXX> timingBeanXXArrayList = new ArrayList<>();
    List<ShopTimingModel.ActiveTimesBean.ThursdayBean.TimingBeanXXX> timingBeanXXXArrayList = new ArrayList<>();
    List<ShopTimingModel.ActiveTimesBean.FridayBean.TimingBeanXXXX> timingBeanXXXXArrayList = new ArrayList<>();
    List<ShopTimingModel.ActiveTimesBean.SaturdayBean.TimingBeanXXXXX> timingBeanXXXXXArrayList = new ArrayList<>();
    List<ShopTimingModel.ActiveTimesBean.SundayBean.TimingBeanXXXXXX> timingBeanXXXXXXArrayList = new ArrayList<>();

    ShopTimingModel.ActiveTimesBean activeTimesBean = new ShopTimingModel.ActiveTimesBean();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUi();

    }

    private void initUi() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(R.color.white));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mContext = ShopTimming.this;

        shopTimingPresenter = new ShopTimingPresenter(this, mContext);
        shopTimingPresenter.getShopTiming();

        onclickForCheckBox();

        mondayTimePicker();

        tuesdayTimePicker();

        wednesdayTimePicker();

        thursdayTimePicker();

        fridayTimePicker();

        saturdayTimePicker();

        sundayTimePicker();

        onclickForImageViewToAddSecondTime();


        updateTiming.setOnClickListener(v -> {

            String shop_id = Utils.LoadPreferences(getApplicationContext(), Constants.KEY_SHOP_ID);

            // MONDAY
            setMondayTimming();

            // TUESDAY
            setTuesdayTimming();

            // WEDNESDAY
            setWednesdayTimming();

            // THURSDAY
            setThursdayTimming();

            // FRIDAY
            setFridayTimming();

            // SATURDAY
            setSaturdayTimming();

            // SUNDAY
            setSundayTimming();


            shopTimingModel.setShop_id(shop_id);
            shopTimingModel.setActive_times(activeTimesBean);

            // CALLING SHOPTIMING UPDATION API...
            //shopTimingPresenter.updateShopTiming(shopTimingModel);


            /*Intent intent = new Intent(ShopTimming.this, ProfileActivity.class);
            startActivity(intent);*/
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_shop_timming;
    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onHideProgress() {

    }

    @Override
    public void onShowToast(String message) {

    }

    @Override
    public void onShowSnackBar(String message) {

    }

    @Override
    public void onShowAlertDialog(String message) {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void shopTimingResponse(ShopTimingModel shopTimingModel) {

    }

    private boolean checkTimeIsEmpty(TextView editText) {

        return editText != null && !editText.getText().toString().equalsIgnoreCase("");
    }

    private void viewSecondTiming(LinearLayout linearLayout) {

        if (linearLayout != null) {
            if (linearLayout.getVisibility() == View.GONE) {
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.GONE);
            }
        }
    }

    private void onclickForCheckBox() {

        checkboxMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strMonday = buttonView.getText().toString();
            }
        });

        checkboxTuesday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strTuesday = buttonView.getText().toString();
            }
        });

        checkboxWednesday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strWednesday = buttonView.getText().toString();
            }
        });

        checkboxThursday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strThursday = buttonView.getText().toString();
            }
        });

        checkboxFriday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strFriday = buttonView.getText().toString();
            }
        });

        checkboxSaturday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strSaturday = buttonView.getText().toString();
            }
        });

        checkboxSunday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                strSunday = buttonView.getText().toString();
            }
        });

    }

    private void mondayTimePicker() {

        tvMondayOpenTime.setOnClickListener(v -> {

            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR);
            int minute = mcurrentTime.get(Calendar.MINUTE);

            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) -> {

                String am_pm;

                if (selectedHour == 0) {

                    selectedHour += 12;

                    am_pm = "AM";
                } else if (selectedHour == 12) {

                    am_pm = "PM";

                } else if (selectedHour > 12) {

                    selectedHour -= 12;

                    am_pm = "PM";

                } else {

                    am_pm = "AM";
                }

                tvMondayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);

                if (firstTimeOpen) {
                    tvTuesdayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                    tvWednesdayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                    tvThursdayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                    tvFridayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                    tvSaturdayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                    tvSundayOpenTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);

                    firstTimeOpen = false;
                }

                shopTimmingUpdation = new ShopTimmingUpdation();
                shopTimmingUpdation.setId(1);
                shopTimmingUpdation.setStart_time(tvMondayOpenTime.getText().toString());


            }, hour, minute, false);//No 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        });

        tvMondayCloseTime.setOnClickListener(v -> {

            if (!tvMondayOpenTime.getText().toString().isEmpty()) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) -> {

                    String am_pm;

                    if (selectedHour == 0) {

                        selectedHour += 12;

                        am_pm = "AM";
                    } else if (selectedHour == 12) {

                        am_pm = "PM";

                    } else if (selectedHour > 12) {

                        selectedHour -= 12;

                        am_pm = "PM";

                    } else {

                        am_pm = "AM";
                    }

                    tvMondayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                    if (firstTimeClose) {
                        tvTuesdayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                        tvWednesdayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                        tvThursdayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                        tvFridayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                        tvSaturdayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                        tvSundayCloseTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);

                        checkboxMonday.setChecked(true);
                        checkboxTuesday.setChecked(true);
                        checkboxWednesday.setChecked(true);
                        checkboxThursday.setChecked(true);
                        checkboxFriday.setChecked(true);
                        checkboxSaturday.setChecked(true);
                        checkboxSunday.setChecked(true);

                        firstTimeClose = false;
                    }

                    shopTimmingUpdation.setId(1);
                    shopTimmingUpdation.setEnd_time(tvMondayCloseTime.getText().toString());

                    shopTimmingUpdationListMonday.add(shopTimmingUpdation);


                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            } else {
                Utils.Toast(ShopTimming.this, "Choose Open Time");
            }
        });

        tvMondayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvMondayOpenSecondTime, 2));
        tvMondayCloseSecondTime.setOnClickListener(v -> {

            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR);
            int minute = mcurrentTime.get(Calendar.MINUTE);

            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) -> {

                String am_pm;

                if (selectedHour == 0) {

                    selectedHour += 12;

                    am_pm = "AM";
                } else if (selectedHour == 12) {

                    am_pm = "PM";

                } else if (selectedHour > 12) {

                    selectedHour -= 12;

                    am_pm = "PM";

                } else {

                    am_pm = "AM";
                }

                tvMondayCloseSecondTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
                //tvToDisplayTime.setBackgroundResource(0);

                shopTimmingUpdation.setId(2);
                shopTimmingUpdation.setEnd_time(tvMondayCloseSecondTime.getText().toString());

                shopTimmingUpdationListMonday.add(shopTimmingUpdation);

            }, hour, minute, false);//No 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });

    }

    private void tuesdayTimePicker() {

        tvTuesdayOpenTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvTuesdayOpenTime));
        tvTuesdayCloseTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvTuesdayCloseTime));

        tvTuesdayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvTuesdayOpenSecondTime, 2));
        tvTuesdayCloseSecondTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvTuesdayCloseSecondTime));
    }

    private void wednesdayTimePicker() {

        tvWednesdayOpenTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvWednesdayOpenTime));
        tvWednesdayCloseTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvWednesdayCloseTime));


        tvWednesdayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvWednesdayOpenSecondTime, 2));
        tvWednesdaycloseSecondTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvWednesdaycloseSecondTime));
    }

    private void thursdayTimePicker() {

        tvThursdayOpenTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvThursdayOpenTime));
        tvThursdayCloseTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvThursdayCloseTime));

        tvThursdayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvThursdayOpenSecondTime, 2));
        tvThursdayCloseSecondTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvThursdayCloseSecondTime));
    }

    private void fridayTimePicker() {

        tvFridayOpenTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvFridayOpenTime));
        tvFridayCloseTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvFridayCloseTime));

        tvFridayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvFridayOpenSecondTime, 2));
        tvFridayCloseSecondTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvFridayCloseSecondTime));
    }

    private void saturdayTimePicker() {

        tvSaturdayOpenTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvSaturdayOpenTime));
        tvSaturdayCloseTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvSaturdayCloseTime));

        tvSaturdayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvSaturdayOpenSecondTime, 2));
        tvSaturdayCloseSecondTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvSaturdayCloseSecondTime));
    }

    private void sundayTimePicker() {

        tvSundayOpenTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvSundayOpenTime));
        tvSundayCloseTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvSundayCloseTime));

        tvSundayOpenSecondTime.setOnClickListener(v -> showTimePickerDialogForSecondTimeOpen(ShopTimming.this, tvSundayOpenSecondTime, 2));
        tvSundayCloseSecondTime.setOnClickListener(v -> Utils.showTimePickerDialog(ShopTimming.this, tvSundayCloseSecondTime));
    }


    private void onclickForImageViewToAddSecondTime() {

        ivTimeEditMonday.setOnClickListener(v -> {

            if (checkTimeIsEmpty(tvMondayOpenTime)) {

                viewSecondTiming(llMondaySecondTiming);

            }
        });

        ivTimeEditTuesday.setOnClickListener(v -> {
            if (checkTimeIsEmpty(tvTuesdayOpenTime)) {
                viewSecondTiming(llTuesdaySecondTiming);
            }
        });

        ivTimeEditWednesday.setOnClickListener(v -> viewSecondTiming(llWednesdaySecondTiming));

        ivTimeEditThursday.setOnClickListener(v -> viewSecondTiming(llThursdaySecondTiming));

        ivTimeEditFriday.setOnClickListener(v -> viewSecondTiming(llFridaySecondTiming));

        ivTimeEditSaturday.setOnClickListener(v -> viewSecondTiming(llSaturdaySecondTiming));

        ivTimeEditSunday.setOnClickListener(v -> viewSecondTiming(llSundaySecondTiming));

    }

    // OPEN SECOND TIME
    public void showTimePickerDialogForSecondTimeOpen(Context mContext, TextView tvToDisplayTime, int id) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) -> {

            String am_pm;

            if (selectedHour == 0) {

                selectedHour += 12;

                am_pm = "AM";
            } else if (selectedHour == 12) {

                am_pm = "PM";

            } else if (selectedHour > 12) {

                selectedHour -= 12;

                am_pm = "PM";

            } else {

                am_pm = "AM";
            }

            tvToDisplayTime.setText(String.format(getString(R.string.str_converion), selectedHour, selectedMinute) + am_pm);
            //tvToDisplayTime.setBackgroundResource(0);

            shopTimmingUpdation = new ShopTimmingUpdation();
            shopTimmingUpdation.setId(id);
            shopTimmingUpdation.setStart_time(tvToDisplayTime.getText().toString());

        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    // SET MONDAY TIMING
    private void setMondayTimming() {

        String strMondayFirstOpenTime = tvMondayOpenTime.getText().toString();
        String strMondayFirstCloseTime = tvMondayCloseTime.getText().toString();

        String strMondaySecondOpenTime = tvMondayOpenSecondTime.getText().toString();
        String strMondaySecondCloseTime = tvMondayCloseSecondTime.getText().toString();

        Log.e("strMondayFirstOpenTime", "" + strMondayFirstOpenTime);
        Toast.makeText(getApplicationContext(), strMondayFirstOpenTime, Toast.LENGTH_LONG).show();

        if (strMondayFirstOpenTime.equalsIgnoreCase("")) {
            if (!checkboxMonday.isChecked()) {

                return;
            }
        }

        ShopTimingModel.ActiveTimesBean.MondayBean.TimingBean timingBean1 = new ShopTimingModel.ActiveTimesBean.MondayBean.TimingBean();

        timingBean1.setId(1);
        timingBean1.setStart_time(strMondayFirstOpenTime);
        timingBean1.setEnd_time(strMondayFirstCloseTime);
        timingBeanList.add(timingBean1);

        ShopTimingModel.ActiveTimesBean.MondayBean.TimingBean timingBean2 = new ShopTimingModel.ActiveTimesBean.MondayBean.TimingBean();
        timingBean2.setId(1);
        timingBean2.setStart_time(strMondaySecondOpenTime);
        timingBean2.setEnd_time(strMondaySecondCloseTime);
        timingBeanList.add(timingBean2);

        mondayBean.setTiming(timingBeanList);
        mondayBean.setIs_checked(true);

        activeTimesBean.setMonday(mondayBean);
    }

    // SET TUESDAY TIMING
    private void setTuesdayTimming() {

        String strTuesdayFirstOpenTime = tvTuesdayOpenTime.getText().toString();
        String strTuesdayFirstCloseTime = tvTuesdayCloseTime.getText().toString();

        String strTuesdaySecondOpenTime = tvTuesdayOpenSecondTime.getText().toString();
        String strTuesdaySecondCloseTime = tvTuesdayCloseSecondTime.getText().toString();


        ShopTimingModel.ActiveTimesBean.TuesdayBean.TimingBeanX timingBeanX1 = new ShopTimingModel.ActiveTimesBean.TuesdayBean.TimingBeanX();

        timingBeanX1.setId(2);
        timingBeanX1.setStart_time(strTuesdayFirstOpenTime);
        timingBeanX1.setEnd_time(strTuesdayFirstCloseTime);
        timingBeanXArrayList.add(timingBeanX1);

        ShopTimingModel.ActiveTimesBean.TuesdayBean.TimingBeanX timingBeanX2 = new ShopTimingModel.ActiveTimesBean.TuesdayBean.TimingBeanX();
        timingBeanX2.setId(2);
        timingBeanX2.setStart_time(strTuesdaySecondOpenTime);
        timingBeanX2.setEnd_time(strTuesdaySecondCloseTime);
        timingBeanXArrayList.add(timingBeanX2);

        tuesdayBean.setTiming(timingBeanXArrayList);

        tuesdayBean.setIs_checked(true);

        activeTimesBean.setTuesday(tuesdayBean);
    }

    // SET WEDNESDAY TIMING
    private void setWednesdayTimming() {

        String strWednesdayFirstOpenTime = tvWednesdayOpenTime.getText().toString();
        String strWednesdayFirstCloseTime = tvWednesdayCloseTime.getText().toString();

        String strWednesdaySecondOpenTime = tvWednesdayOpenSecondTime.getText().toString();
        String strWednesdaySecondCloseTime = tvWednesdaycloseSecondTime.getText().toString();


        ShopTimingModel.ActiveTimesBean.WednesdayBean.TimingBeanXX timingBeanX1 = new ShopTimingModel.ActiveTimesBean.WednesdayBean.TimingBeanXX();

        timingBeanX1.setId(3);
        timingBeanX1.setStart_time(strWednesdayFirstOpenTime);
        timingBeanX1.setEnd_time(strWednesdayFirstCloseTime);
        timingBeanXXArrayList.add(timingBeanX1);

        ShopTimingModel.ActiveTimesBean.WednesdayBean.TimingBeanXX timingBeanX2 = new ShopTimingModel.ActiveTimesBean.WednesdayBean.TimingBeanXX();
        timingBeanX2.setId(3);
        timingBeanX2.setStart_time(strWednesdaySecondOpenTime);
        timingBeanX2.setEnd_time(strWednesdaySecondCloseTime);
        timingBeanXXArrayList.add(timingBeanX2);

        wednesdayBean.setTiming(timingBeanXXArrayList);

        wednesdayBean.setIs_checked(true);

        activeTimesBean.setWednesday(wednesdayBean);
    }

    // SET THURSDAY TIMING
    private void setThursdayTimming() {

        String strThursdayFirstOpenTime = tvThursdayOpenTime.getText().toString();
        String strThursdayFirstCloseTime = tvThursdayCloseTime.getText().toString();

        String strThursdaySecondOpenTime = tvThursdayOpenSecondTime.getText().toString();
        String strThursdaySecondCloseTime = tvThursdayCloseSecondTime.getText().toString();


        ShopTimingModel.ActiveTimesBean.ThursdayBean.TimingBeanXXX timingBeanX1 = new ShopTimingModel.ActiveTimesBean.ThursdayBean.TimingBeanXXX();

        timingBeanX1.setId(4);
        timingBeanX1.setStart_time(strThursdayFirstOpenTime);
        timingBeanX1.setEnd_time(strThursdayFirstCloseTime);
        timingBeanXXXArrayList.add(timingBeanX1);

        ShopTimingModel.ActiveTimesBean.ThursdayBean.TimingBeanXXX timingBeanX2 = new ShopTimingModel.ActiveTimesBean.ThursdayBean.TimingBeanXXX();
        timingBeanX2.setId(4);
        timingBeanX2.setStart_time(strThursdaySecondOpenTime);
        timingBeanX2.setEnd_time(strThursdaySecondCloseTime);
        timingBeanXXXArrayList.add(timingBeanX2);

        thursdayBean.setTiming(timingBeanXXXArrayList);

        thursdayBean.setIs_checked(true);

        activeTimesBean.setThursday(thursdayBean);
    }

    // SET FRIDAY TIMING
    private void setFridayTimming() {

        String strFridayFirstOpenTime = tvFridayOpenTime.getText().toString();
        String strFridayFirstCloseTime = tvFridayCloseTime.getText().toString();

        String strFridaySecondOpenTime = tvFridayOpenSecondTime.getText().toString();
        String strFridaySecondCloseTime = tvFridayCloseSecondTime.getText().toString();


        ShopTimingModel.ActiveTimesBean.FridayBean.TimingBeanXXXX timingBeanX1 = new ShopTimingModel.ActiveTimesBean.FridayBean.TimingBeanXXXX();

        timingBeanX1.setId(5);
        timingBeanX1.setStart_time(strFridayFirstOpenTime);
        timingBeanX1.setEnd_time(strFridayFirstCloseTime);
        timingBeanXXXXArrayList.add(timingBeanX1);

        ShopTimingModel.ActiveTimesBean.FridayBean.TimingBeanXXXX timingBeanX2 = new ShopTimingModel.ActiveTimesBean.FridayBean.TimingBeanXXXX();
        timingBeanX2.setId(5);
        timingBeanX2.setStart_time(strFridaySecondOpenTime);
        timingBeanX2.setEnd_time(strFridaySecondCloseTime);
        timingBeanXXXXArrayList.add(timingBeanX2);

        fridayBean.setTiming(timingBeanXXXXArrayList);

        fridayBean.setIs_checked(true);

        activeTimesBean.setFriday(fridayBean);
    }

    // SET SATURDAY TIMING
    private void setSaturdayTimming() {

        String strSaturdayFirstOpenTime = tvSaturdayOpenTime.getText().toString();
        String strSaturdayFirstCloseTime = tvSaturdayCloseTime.getText().toString();

        String strSaturdaySecondOpenTime = tvSaturdayOpenSecondTime.getText().toString();
        String strSaturdaySecondCloseTime = tvSaturdayCloseSecondTime.getText().toString();


        ShopTimingModel.ActiveTimesBean.SaturdayBean.TimingBeanXXXXX timingBeanX1 = new ShopTimingModel.ActiveTimesBean.SaturdayBean.TimingBeanXXXXX();

        timingBeanX1.setId(6);
        timingBeanX1.setStart_time(strSaturdayFirstOpenTime);
        timingBeanX1.setEnd_time(strSaturdayFirstCloseTime);
        timingBeanXXXXXArrayList.add(timingBeanX1);

        ShopTimingModel.ActiveTimesBean.SaturdayBean.TimingBeanXXXXX timingBeanX2 = new ShopTimingModel.ActiveTimesBean.SaturdayBean.TimingBeanXXXXX();
        timingBeanX2.setId(6);
        timingBeanX2.setStart_time(strSaturdaySecondOpenTime);
        timingBeanX2.setEnd_time(strSaturdaySecondCloseTime);
        timingBeanXXXXXArrayList.add(timingBeanX2);

        saturdayBean.setTiming(timingBeanXXXXXArrayList);

        saturdayBean.setIs_checked(true);

        activeTimesBean.setSaturday(saturdayBean);
    }

    // SET SUNDAY TIMING
    private void setSundayTimming() {

        String strSundayFirstOpenTime = tvSundayOpenTime.getText().toString();
        String strSundayFirstCloseTime = tvSundayCloseTime.getText().toString();

        String strSundaySecondOpenTime = tvSundayOpenSecondTime.getText().toString();
        String strSundaySecondCloseTime = tvSundayCloseSecondTime.getText().toString();


        ShopTimingModel.ActiveTimesBean.SundayBean.TimingBeanXXXXXX timingBeanX1 = new ShopTimingModel.ActiveTimesBean.SundayBean.TimingBeanXXXXXX();

        timingBeanX1.setId(7);
        timingBeanX1.setStart_time(strSundayFirstOpenTime);
        timingBeanX1.setEnd_time(strSundayFirstCloseTime);
        timingBeanXXXXXXArrayList.add(timingBeanX1);

        ShopTimingModel.ActiveTimesBean.SundayBean.TimingBeanXXXXXX timingBeanX2 = new ShopTimingModel.ActiveTimesBean.SundayBean.TimingBeanXXXXXX();
        timingBeanX2.setId(7);
        timingBeanX2.setStart_time(strSundaySecondOpenTime);
        timingBeanX2.setEnd_time(strSundaySecondCloseTime);
        timingBeanXXXXXXArrayList.add(timingBeanX2);

        sundayBean.setTiming(timingBeanXXXXXXArrayList);

        sundayBean.setIs_checked(true);

        activeTimesBean.setSunday(sundayBean);
    }
}
