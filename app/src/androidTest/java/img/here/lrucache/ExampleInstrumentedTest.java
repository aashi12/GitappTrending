package img.here.lrucache;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest
{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public  void  inti()
    {

        IdlingRegistry.getInstance().register(EspressoIdling.getIdlingResource());
    }

    @Test
    public void useAppContext() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdling.getIdlingResource());
    }


    @Test
    public void chkProgress_Bar_gone() throws InterruptedException {


        mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();

        //Thread.sleep(3000);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
       // onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

    }
}
