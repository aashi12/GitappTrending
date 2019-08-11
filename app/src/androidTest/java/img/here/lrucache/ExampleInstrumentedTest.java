package img.here.lrucache;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import espressohelper.EspressoIdling;
import espressohelper.RecyclerViewMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest
{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public  void  inti()
    {

        IdlingRegistry.getInstance().register(EspressoIdling.getIdlingResource());
        Intents.init();
    }

    @Test
    public void useAppContext() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdling.getIdlingResource());
        Intents.release();
    }





    @Test
    public  void click_beyond_limit()
    {

        mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();
        onView(ViewMatchers.withId(R.id.recyy)).perform(RecyclerViewActions.actionOnItemAtPosition(100, click()));



    }

    @Test
    public  void click_in_limit()
    {

        if(getRVcount()>0)
        mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();
       onView(ViewMatchers.withId(R.id.recyy)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));








    }


    @Test

    public  void  first_is_GridStudio()   // it is only applicable if we are using some sort of sorting logic in our App
    {
        if(getRVcount()>0)
            mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();
        onView(new RecyclerViewMatcher(R.id.recyy)
                .atPositionOnView(0, R.id.tv_username))
                .check(matches(withText("gridstudio")));




    }



    private int getRVcount(){
        RecyclerView recyclerView = (RecyclerView) mActivityTestRule.getActivity().findViewById(R.id.recyy);
        return recyclerView.getAdapter().getItemCount();
    }

    @Test
    public void chkProgressBarGone() throws InterruptedException {


        mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();

        //Thread.sleep(3000);

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
       // onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

    }







    @Test
    public  void DetailsActivtyLaunched()
    {
        if(getRVcount()>0)
            mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();
        onView(ViewMatchers.withId(R.id.recyy)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


        intended(hasComponent(DetialsActivity.class.getName()));




    }


 @Test
    public  void DetailsActivtyLaunchedWithCorrectdata()
    {
       DetailsActivtyLaunched();
        onView(ViewMatchers.withId(R.id.tv_name)).check(matches(withText("hackathon-starter")));


    }






    @Test
      public  void chkIntentData()
      {


          if(getRVcount()>0)
              mActivityTestRule.getActivity().lvm.getGitDataFromNetwork();
          onView(ViewMatchers.withId(R.id.recyy)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

          intended(allOf(
                  toPackage("img.here.lrucache"),
                  hasExtra("detail", "j")));
      }


}
