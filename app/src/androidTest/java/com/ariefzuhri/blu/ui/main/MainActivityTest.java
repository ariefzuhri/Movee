/* Skenario Instrument Testing
 *
 * Menampilkan data movie:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memastikan recycler_view dalam keadaan tampil
 * - Gulir recycler_view ke posisi data terakhir
 *
 * Menampilkan detail movie:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memberi tindakan klik pada data pertama di recycler_view
 * - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk release year tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk release date tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk runtime tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk synopsis tampil sesuai dengan yang diharapkan
 * - Memastikan btn_trailer dalam keadaan tampil
 * - Memastikan btn_trailer dapat diklik
 * - Memastikan rv_genre dalam keadaan tampil
 * - Gulir rv_genre ke posisi data terakhir
 * - Memastikan TextView untuk type tampil sesuai dengan yang diharapkan
 *
 * Menampilkan data tv:
 * - Memberi tindakan klik pada menu tv di bottom navigation bar
 * - Memastikan recycler_view dalam keadaan tampil
 * - Gulir recycler_view ke posisi data terakhir
 *
 * Menampilkan detail tv:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memberi tindakan klik pada data pertama di recycler_view
 * - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk release year tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk release date tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk runtime tampil sesuai dengan yang diharapkan
 * - Memastikan TextView untuk synopsis tampil sesuai dengan yang diharapkan
 * - Memastikan btn_trailer dalam keadaan tampil
 * - Memastikan btn_trailer dapat diklik
 * - Memastikan rv_genre dalam keadaan tampil
 * - Gulir rv_genre ke posisi data terakhir
 * - Memastikan TextView untuk type tampil sesuai dengan yang diharapkan*/
package com.ariefzuhri.blu.ui.main;

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
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ariefzuhri.blu.utils.Constants.STATUS_CURRENTLY;
import static com.ariefzuhri.blu.utils.Constants.STATUS_FINISHED;
import static com.ariefzuhri.blu.utils.Constants.STATUS_NOT_YET;
import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static com.ariefzuhri.blu.utils.DateUtils.getDateWithoutYear;
import static com.ariefzuhri.blu.utils.DateUtils.getYearOfDate;
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
        onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_year)).check(matches(withText(getYearOfDate(
                dummyMovies.get(0).getAiredDate().getStartDate()))));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(withText("| " + getDateWithoutYear(
                dummyMovies.get(0).getAiredDate().getStartDate()))));
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime)).check(matches(withText(
                dummyMovies.get(0).getRuntime() + " m")));
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyMovies.get(0).getSynopsis())));
        onView(withId(R.id.btn_trailer)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_trailer)).check(matches(isClickable()));
        onView(withId(R.id.rv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_genre)).perform(RecyclerViewActions.scrollToPosition(
                dummyMovies.get(0).getGenres().split(", ").length));

        String status = null;
        switch (dummyMovies.get(0).getStatus()) {
            case STATUS_FINISHED: status = "Selesai"; break;
            case STATUS_CURRENTLY: status = "Tayang"; break;
            case STATUS_NOT_YET: status = "Segera"; break;
        }
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_type)).check(matches(withText("Film, " +
                dummyMovies.get(0).getEpisodes() + " eps - " + status)));
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
        onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_year)).check(matches(withText(getYearOfDate(
                dummyTVs.get(0).getAiredDate().getStartDate()))));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(withText("| " + getDateWithoutYear(
                dummyTVs.get(0).getAiredDate().getStartDate()))));
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime)).check(matches(withText(
                dummyTVs.get(0).getRuntime() + " m/eps")));
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyTVs.get(0).getSynopsis())));
        onView(withId(R.id.btn_trailer)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_trailer)).check(matches(isClickable()));
        onView(withId(R.id.rv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_genre)).perform(RecyclerViewActions.scrollToPosition(
                dummyTVs.get(0).getGenres().split(", ").length));

        String status = null;
        switch (dummyTVs.get(0).getStatus()) {
            case STATUS_FINISHED: status = "Selesai"; break;
            case STATUS_CURRENTLY: status = "Tayang"; break;
            case STATUS_NOT_YET: status = "Segera"; break;
        }
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_type)).check(matches(withText("Serial, " +
                dummyTVs.get(0).getEpisodes() + " eps - " + status)));
    }
}