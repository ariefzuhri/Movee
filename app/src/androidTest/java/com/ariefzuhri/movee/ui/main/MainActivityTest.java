/*
 * Skenario Instrument Testing
 *
 * Menampilkan data movie:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memastikan rv_horiz berisi 20 item
 * - Memastikan rv_vert berisi 20 item
 *
 * Menampilkan data tv:
 * - Memberi tindakan klik pada menu tv di bottom navigation bar
 * - Memastikan rv_horiz berisi 20 item
 * - Memastikan rv_vert berisi 20 item
 *
 * Menampilkan detail movie:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memberi tindakan klik pada data pertama di rv_vert
 * - Memastikan TextView untuk title tampil
 * - Memastikan TextView untuk release year tampil
 * - Memastikan TextView untuk release date tampil
 * - Memastikan TextView untuk studio tampil
 * - Memastikan TextView untuk score average tampil
 * - Memastikan TextView untuk score count tampil
 * - Memastikan TextView untuk type tampil
 * - Memastikan TextView untuk runtime tampil
 * - Memastikan rv_genre dalam keadaan tampil
 * - Memastikan TextView untuk synopsis tampil
 * - Memastikan rv_recommendation berisi lebih dari atau sama dengan 0 item
 * - Memastikan Button untuk trailer dalam keadaan tampil
 * - Memastikan Button untuk trailer dapat diklik
 * - Memastikan Button untuk favorit dalam keadaan tampil
 * - Memastikan Button untuk favorit dapat diklik
 * - Memastikan Button untuk back dalam keadaan tampil
 * - Memastikan Button untuk back dapat diklik
 *
 * Menampilkan detail tv:
 * - Memberi tindakan klik pada menu movie di bottom navigation bar
 * - Memberi tindakan klik pada data pertama di rv_vert
 * - Memastikan TextView untuk title tampil
 * - Memastikan TextView untuk release year tampil
 * - Memastikan TextView untuk release date tampil
 * - Memastikan TextView untuk studio tampil
 * - Memastikan TextView untuk score average tampil
 * - Memastikan TextView untuk score count tampil
 * - Memastikan TextView untuk type tampil
 * - Memastikan TextView untuk runtime tampil
 * - Memastikan rv_genre dalam keadaan tampil
 * - Memastikan TextView untuk synopsis tampil
 * - Memastikan rv_recommendation berisi lebih dari atau sama dengan 0 item
 * - Memastikan Button untuk trailer dalam keadaan tampil
 * - Memastikan Button untuk trailer dapat diklik
 * - Memastikan Button untuk favorit dalam keadaan tampil
 * - Memastikan Button untuk favorit dapat diklik
 * - Memastikan Button untuk back dalam keadaan tampil
 * - Memastikan Button untuk back dapat diklik
 *
 * Menampilkan discover:
 * - Memberi tindakan klik pada menu discover di bottom navigation bar
 * - Memastikan chip_group dalam keadaan tampil
 * - Memastikan chip_popular dalam keadaan tampil
 * - Memberi tindakan klik pada chip_popular
 * - Memastikan visibilitas rv_movie dalam keadaan terlihat
 * - Memastikan rv_movie berisi 20 item
 * - Memastikan visibilitas rv_tv dalam keadaan terlihat
 * - Memastikan rv_tv berisi 20 item
 * - Memastikan chip_upcoming dalam keadaan tampil
 * - Memberi tindakan klik pada chip_upcoming
 * - Memastikan visibilitas rv_movie dalam keadaan terlihat
 * - Memastikan rv_movie berisi 20 item
 * - Memastikan visibilitas rv_tv dalam keadaan tidak terlihat
 * - Memastikan chip_latest_release dalam keadaan tampil
 * - Memberi tindakan klik pada chip_latest_release
 * - Memastikan visibilitas rv_movie dalam keadaan terlihat
 * - Memastikan rv_movie berisi lebih dari atau sama dengan 0 item
 * - Memastikan visibilitas rv_tv dalam keadaan terlihat
 * - Memastikan rv_tv berisi lebih dari atau sama dengan 0 item
 * - Memastikan chip_top_rated dalam keadaan tampil
 * - Memberi tindakan klik pada chip_top_rated
 * - Memastikan visibilitas rv_movie dalam keadaan terlihat
 * - Memastikan rv_movie berisi 20 item
 * - Memastikan visibilitas rv_tv dalam keadaan terlihat
 * - Memastikan rv_tv berisi 20 item
 *
 * Menampilkan pencarian:
 * - Memberi tindakan klik pada menu discover di bottom navigation bar
 * - Memastikan search_view dalam keadaan tampil
 * - Memberi tindakan klik pada search_view
 * - Memberi tindakan mengetik string "the promised neverland" dan menekan enter pada search view yang ada
 * - Memastikan recycler_view pada hasil pencarian dalam keadaan tampil
 * - Memastikan recycler_view pada hasil pencarian berisi lebih dari atau sama dengan 2 item
 * - Memastikan search_view pada hasil pencarian dalam keadaan tampil
 * - Memberi tindakan klik pada search_view pada hasil pencarian
 * - Memberi tindakan mengetik string "maquia" dan menekan enter pada search view yang ada pada hasil pencarian
 * - Memastikan recycler_view pada hasil pencarian dalam keadaan tampil
 * - Memastikan recycler_view pada hasil pencarian berisi lebih dari atau sama dengan 1 item
 *
 * Menampilkan favorit:
 * - Memberi tindakan klik pada menu movie
 * - Memberi tindakan klik pada data pertama di rv_vert menu movie
 * - Memastikan fab_favorite dalam keadaan tampil
 * - Memberi tindakan klik pada fab_favorite
 * - Memastikan fab_back dalam keadaan tampil
 * - Memberi tindakan klik pada fab_back
 * - Memberi tindakan klik pada menu tv
 * - Memberi tindakan klik pada data pertama di rv_vert menu tv
 * - Memastikan fab_favorite dalam keadaan tampil
 * - Memberi tindakan klik pada fab_favorite
 * - Memastikan fab_back dalam keadaan tampil
 * - Memberi tindakan klik pada fab_back
 * - Memberi tindakan klik pada menu favorite
 * - Memastikan chip_all pada menu favorit dalam keadaan tampil
 * - Memberi tindakan klik pada chip_all
 * - Memastikan recycler_view pada menu favorit dalam keadaan tampil
 * - Memastikan recycler_view berisi lebih dari atau sama dengan 0 item
 * - Memastikan chip_movie pada menu favorit dalam keadaan tampil
 * - Memberi tindakan klik pada chip_movie
 * - Memastikan recycler_view pada menu favorit dalam keadaan tampil
 * - Memastikan recycler_view berisi lebih dari atau sama dengan 0 item
 * - Memastikan chip_tv pada menu favorit dalam keadaan tampil
 * - Memberi tindakan klik pada chip_tv
 * - Memastikan recycler_view pada menu favorit dalam keadaan tampil
 * - Memastikan recycler_view berisi lebih dari atau sama dengan 0 item
 * - Memastikan fab_option pada menu favorit dalam keadaan tampil
 * - Memberi tindakan klik pada fab_option
 */

package com.ariefzuhri.movee.ui.main;

import android.view.KeyEvent;
import android.widget.AutoCompleteTextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.core.utils.EspressoIdlingResource;
import com.ariefzuhri.movee.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.ariefzuhri.movee.ui.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class MainActivityTest {

    @Before
    public void setup() {
        ActivityScenario.launch(MainActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.menu_movie)).perform(click());
        onView(allOf(withId(R.id.rv_horiz), isDescendantOfA(withId(R.id.fragment_movie))))
                .check(withItemCount(equalTo(20)));
        onView(allOf(withId(R.id.rv_vert), isDisplayed()))
                .check(withItemCount(equalTo(20)));
    }

    @Test
    public void loadTVs() {
        onView(withId(R.id.menu_tv)).perform(click());
        onView(allOf(withId(R.id.rv_horiz), isDescendantOfA(withId(R.id.fragment_tv))))
                .check(withItemCount(equalTo(20)));
        onView(allOf(withId(R.id.rv_vert), isDisplayed()))
                .check(withItemCount(equalTo(20)));
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.menu_movie)).perform(click());
        onView(allOf(withId(R.id.rv_vert), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.tv_header), isDisplayed()));
        onView(allOf(withId(R.id.tv_release_year), isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_studio)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tv_score_average), isDisplayed()));
        onView(withId(R.id.tv_score_count)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_synopsis)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_recommendation)).check(withItemCount(greaterThanOrEqualTo(0)));
        onView(withId(R.id.btn_trailer)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_trailer)).check(matches(isClickable()));
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_favorite)).check(matches(isClickable()));
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_back)).check(matches(isClickable()));
    }

    @Test
    public void loadDetailTV() {
        onView(withId(R.id.menu_tv)).perform(click());
        onView(allOf(withId(R.id.rv_vert), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.tv_header), isDisplayed()));
        onView(allOf(withId(R.id.tv_release_year), isDisplayed()));
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_studio)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.tv_score_average), isDisplayed()));
        onView(withId(R.id.tv_score_count)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_recommendation)).check(withItemCount(greaterThanOrEqualTo(0)));
        onView(withId(R.id.tv_synopsis)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.btn_trailer)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_trailer)).check(matches(isClickable()));
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_favorite)).check(matches(isClickable()));
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_back)).check(matches(isClickable()));
    }

    @Test
    public void loadDiscover() {
        onView(withId(R.id.menu_discover)).perform(click());
        onView(withId(R.id.chip_group)).check(matches(isDisplayed()));

        onView(withId(R.id.chip_popular)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_popular)).perform(click());
        onView(withId(R.id.rv_movie)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_movie)).check(withItemCount(equalTo(20)));
        onView(withId(R.id.rv_tv)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_tv)).check(withItemCount(equalTo(20)));

        onView(withId(R.id.chip_upcoming)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_upcoming)).perform(click());
        onView(withId(R.id.rv_movie)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_movie)).check(withItemCount(equalTo(20)));
        onView(withId(R.id.rv_tv)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        onView(withId(R.id.chip_latest_release)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_latest_release)).perform(click());
        onView(withId(R.id.rv_movie)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_movie)).check(withItemCount(greaterThanOrEqualTo(0)));
        onView(withId(R.id.rv_tv)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_tv)).check(withItemCount(greaterThanOrEqualTo(0)));

        onView(withId(R.id.chip_top_rated)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_top_rated)).perform(click());
        onView(withId(R.id.rv_movie)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_movie)).check(withItemCount(equalTo(20)));
        onView(withId(R.id.rv_tv)).check(matches(withEffectiveVisibility(VISIBLE)));
        onView(withId(R.id.rv_tv)).check(withItemCount(equalTo(20)));
    }

    @Test
    public void loadSearch() {
        onView(withId(R.id.menu_discover)).perform(click());
        onView(withId(R.id.search_view)).check(matches(isDisplayed()));
        onView(withId(R.id.search_view)).perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class)).perform(typeText("the promised neverland"), pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(withItemCount(greaterThanOrEqualTo(2)));

        onView(withId(R.id.search_view)).check(matches(isDisplayed()));
        onView(withId(R.id.search_view)).perform(click());
        onView(isAssignableFrom(AutoCompleteTextView.class)).perform(clearText(), typeText("maquia"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(withItemCount(greaterThanOrEqualTo(1)));
    }

    @Test
    public void loadFavorite() {
        onView(withId(R.id.menu_movie)).perform(click());
        onView(allOf(withId(R.id.rv_vert), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_back)).perform(click());

        onView(withId(R.id.menu_tv)).perform(click());
        onView(allOf(withId(R.id.rv_vert), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_back)).perform(click());

        onView(withId(R.id.menu_favorite)).perform(click());

        onView(withId(R.id.chip_all)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_all)).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(withItemCount(greaterThanOrEqualTo(0)));

        onView(withId(R.id.chip_movie)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_movie)).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(withItemCount(greaterThanOrEqualTo(0)));

        onView(withId(R.id.chip_tv)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chip_tv)).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(withItemCount(greaterThanOrEqualTo(0)));

        onView(withId(R.id.fab_option)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_option)).perform(click());
    }
}