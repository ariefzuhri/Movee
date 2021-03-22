package com.ariefzuhri.blu.data;

import android.os.Parcel;
import android.os.Parcelable;

public class AiredDate implements Parcelable {
    private final String startDate;
    private final String endDate;

    public AiredDate(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AiredDate(String date) {
        this.startDate = date;
        this.endDate = date;
    }

    protected AiredDate(Parcel in) {
        startDate = in.readString();
        endDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startDate);
        dest.writeString(endDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AiredDate> CREATOR = new Creator<AiredDate>() {
        @Override
        public AiredDate createFromParcel(Parcel in) {
            return new AiredDate(in);
        }

        @Override
        public AiredDate[] newArray(int size) {
            return new AiredDate[size];
        }
    };

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}