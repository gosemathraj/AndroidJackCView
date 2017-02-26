package gosemathraj.com.calendarviews.activities;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gosemathraj.com.calendarviews.R;
import gosemathraj.com.calendarviews.fragments.AddEventFragment;
import gosemathraj.com.calendarviews.fragments.CalendarViewFragment;
import gosemathraj.com.calendarviews.fragments.WeekViewCalendarFragment;

/**
 * Created by RajeshG on 24-02-2017.
 */

public class HomeActivity extends AppCompatActivity implements MonthLoader.MonthChangeListener,WeekView.EventClickListener,
                                WeekView.EventLongPressListener,WeekView.EmptyViewClickListener{

    @BindView(R.id.weekView)
    WeekView weekView;

    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

    private String currentDate = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_base_container,new WeekViewCalendarFragment()).commit();

        weekView.setMonthChangeListener(this);
        weekView.setEmptyViewClickListener(this);
        weekView.setOnEventClickListener(this);
        weekView.setEventLongPressListener(this);
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, "New Event", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        return events;
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this,"onEvent Clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewClicked(Calendar time) {
        Toast.makeText(this,"onEmptyEvent Clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this,"onEventLongPress Clicked",Toast.LENGTH_SHORT).show();
    }
}
