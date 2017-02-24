package gosemathraj.com.calendarviews.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import gosemathraj.com.calendarviews.R;
import gosemathraj.com.calendarviews.fragments.AddEventFragment;

/**
 * Created by RajeshG on 24-02-2017.
 */

public class HomeActivity extends AppCompatActivity{

    private String currentDate = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_base_container, new AddEventFragment()).commit();
        }
    }
}
