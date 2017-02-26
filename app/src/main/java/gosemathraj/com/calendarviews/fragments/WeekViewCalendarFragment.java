package gosemathraj.com.calendarviews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.List;

import butterknife.BindView;
import gosemathraj.com.calendarviews.R;

/**
 * Created by iamsparsh on 26/2/17.
 */

public class WeekViewCalendarFragment extends Fragment implements MonthLoader.MonthChangeListener{

    @BindView(R.id.weekView)
    WeekView weekView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_week_view,container,false);

        try{
            init();
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private void init() {

        setOnClickListener();
    }

    private void setOnClickListener() {
        weekView.setMonthChangeListener(this);
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return null;
    }
}
