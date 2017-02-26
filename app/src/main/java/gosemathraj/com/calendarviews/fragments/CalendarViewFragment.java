package gosemathraj.com.calendarviews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import butterknife.BindView;
import gosemathraj.com.calendarviews.R;

/**
 * Created by iamsparsh on 26/2/17.
 */

public class CalendarViewFragment extends Fragment {

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    private String currentDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar,container,false);

        try{
            //init();
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    /*private void init() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                currentDate = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
                openAddEventFragment();
            }
        });
    }

    private void openAddEventFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_base_container,new AddEventFragment()).commit();
    }*/
}
