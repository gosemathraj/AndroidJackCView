package gosemathraj.com.calendarviews.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import gosemathraj.com.calendarviews.R;

/**
 * Created by RajeshG on 24-02-2017.
 */

public class AddEventFragment extends Fragment {

    @BindView(R.id.appointmentType)
    Spinner appointmentType;

    @BindView(R.id.selectEndTime)
    TextView selectEndTime;

    @BindView(R.id.selectStartTime)
    TextView selectStartTime;

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

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
    }

    private void setAppointmentTypeSpinner() {
        ArrayAdapter<String> appointmentTypeAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,new String[]{"Consultation","Follow up"});
        appointmentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        appointmentType.setAdapter(appointmentTypeAdapter);
    }


}
