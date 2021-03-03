/* Skenario Instrument Testing
 *
 * Menampilkan data movie:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memastikan recycler_view dalam keadaan tampil
 * - Gulir recycler_view ke posisi data terakhir
 *
 * Mnampilkan detail movie:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memberi tindakan klik pada data pertama di recycler_view
 * - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk score tampil sesuai dengan yang diharapkan
 *
 * Menampilkan data tv:
 * - Memberi tindakan klik pada menu tv di bottom navigation bar
 * - Memastikan recycler_view dalam keadaan tampil
 * - Gulir recycler_view ke posisi data terakhir
 *
 * Mnampilkan detail tv:
 * - Memberi tindakan klik pada menu tv di bottom navigation bar
 * - Memberi tindakan klik pada data pertama di recycler_view
 * - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk score tampil sesuai dengan yang diharapkan*/
package com.ariefzuhri.blu.home;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.model.Movie;
import com.ariefzuhri.blu.utils.DummyMovie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static org.hamcrest.Matchers.allOf;

public class MainActivityTest {
    private final ArrayList<Movie> dummyMovies = DummyMovie.generateMovies(TYPE_MOVIE);
    private final ArrayList<Movie> dummyTVs = DummyMovie.generateMovies(TYPE_TV);

    @Before
    public void setup(){
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void loadMovies(){
        onView(withId(R.id.menu_movie)).perform(click());
        onView(allOf(withId(R.id.recycler_view), isDisplayed())).perform(RecyclerViewActions
                .scrollToPosition(dummyMovies.size()));
    }

    @Test
    public void loadDetailMovie(){
        onView(withId(R.id.menu_movie)).perform(click());
        onView(allOf(withId(R.id.recycler_view), isDisplayed())).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies.get(0).getTitle())));
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovies.get(0).getScore())));
    }

    @Test
    public void loadTVs(){
        onView(withId(R.id.menu_tv)).perform(click());
        onView(allOf(withId(R.id.recycler_view), isDisplayed())).perform(RecyclerViewActions
                .scrollToPosition(dummyTVs.size()));
    }

    @Test
    public void loadDetailTV(){
        onView(withId(R.id.menu_tv)).perform(click());
        onView(allOf(withId(R.id.recycler_view), isDisplayed())).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTVs.get(0).getTitle())));
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_score)).check(matches(withText(dummyTVs.get(0).getScore())));
    }
}