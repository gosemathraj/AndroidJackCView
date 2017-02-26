package gosemathraj.com.calendarviews.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import gosemathraj.com.calendarviews.R;
import gosemathraj.com.calendarviews.data.Contract;

import static gosemathraj.com.calendarviews.R.style.Button;

/**
 * Created by RajeshG on 24-02-2017.
 */

public class AddEventFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.appointmentType)
    Spinner appointmentType;

    @BindView(R.id.selectEndTime)
    TextView selectEndTime;

    @BindView(R.id.selectStartTime)
    TextView selectStartTime;

    @BindView(R.id.selectDate)
    TextView selectDate;

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.patientName)
    EditText patientName;

    @BindView(R.id.patientEmail)
    EditText patientEmail;

    @BindView(R.id.patientContactNo)
    EditText patientContact;

    @BindView(R.id.addEvent)
    Button addEvent;

    private String currentDate;
    private String appType;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_event,container,false);
        ButterKnife.bind(this,view);

        try{
            init();
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private void init() {
        setAppointmentTypeSpinner();
        setOnClickListener();
    }

    private void setOnClickListener() {
        selectStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                         startTime.setText(String.valueOf(hour) + ":" + String.valueOf(minutes));
                    }
                },3,60,false);
                timePickerDialog.show();
            }
        });

        selectEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                         endTime.setText(String.valueOf(hour) + ":" + String.valueOf(minutes));
                    }
                },3,60,false);
                timePickerDialog.show();
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),AddEventFragment.this, 2013, 2, 18);
                dialog.show();
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.AppointmentEntry.COLUMN_APPOINTMENT_TYPE,appType);
                contentValues.put(Contract.AppointmentEntry.COLUMN_PATIENT_NAME,patientName.getText().toString());
                contentValues.put(Contract.AppointmentEntry.COLUMN_PATIENT_EMAIL,patientEmail.getText().toString());
                contentValues.put(Contract.AppointmentEntry.COLUMN_PATIENT_MOBILE,Integer.parseInt(patientContact.getText().toString()));
                contentValues.put(Contract.AppointmentEntry.COLUMN_START_TIME,startTime.getText().toString());
                contentValues.put(Contract.AppointmentEntry.COLUMN_END_TIME,endTime.getText().toString());
                contentValues.put(Contract.AppointmentEntry.COLUMN_DATE,currentDate);


            }
        });

        appointmentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                appType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAppointmentTypeSpinner() {
        ArrayAdapter<String> appointmentTypeAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,new String[]{"Consultation","Follow up"});
        appointmentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        appointmentType.setAdapter(appointmentTypeAdapter);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        currentDate = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
    }
}
