package com.ariefzuhri.movee.utils;

import androidx.paging.PagedList;

import org.jetbrains.annotations.NotNull;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagedListUtil {

    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> PagedList<T> mockPagedList(@NotNull List<T> list) {
        PagedList<T> pagedList = mock(PagedList.class);
        Answer<T> answer = invocation -> {
            Integer index = (Integer) invocation.getArguments()[0];
            return list.get(index);
        };

        when(pagedList.get(anyInt())).thenAnswer(answer);
        when(pagedList.size()).thenReturn(list.size());

        return pagedList;
    }
}
