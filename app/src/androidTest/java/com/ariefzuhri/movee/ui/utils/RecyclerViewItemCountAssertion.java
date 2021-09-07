package com.ariefzuhri.movee.ui.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.is;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final Matcher<Integer> matcher;

    @NotNull
    public static RecyclerViewItemCountAssertion withItemCount(int expectedCount) {
        return withItemCount(is(expectedCount));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static RecyclerViewItemCountAssertion withItemCount(Matcher<Integer> matcher) {
        return new RecyclerViewItemCountAssertion(matcher);
    }

    private RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) throw noViewFoundException;
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            assertThat(adapter.getItemCount(), matcher);
        }
    }
}