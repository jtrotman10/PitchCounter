package com.westglenn.www.pitchcounter;

/**
 * Created by jtrotman on 9/13/2014.
 */
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;

public class Pitcher implements Parcelable {
    private int _id;
    private String _name;
    private int _pitchCount;
    private Date _lastPitch;
    private static int _nextId = 1;

    public Pitcher()
    {
        this._id = _nextId++;
        this._name = "New Pitcher";
        this._pitchCount = 0;
        this._lastPitch = null;
    }

    private Pitcher(Parcel in) {
        this._id = in.readInt();
        this._name = in.readString();
        this._pitchCount = in.readInt();
        String dt = in.readString();
        if (dt == "") {
            this._lastPitch = null;
        }
        else {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                this._lastPitch = format.parse(dt);
            } catch (Exception e) {
                ;
            }
        }
    }

    public String toString()
    {
        return this._name + " - " + String.format("%d pitches", this._pitchCount) + " - Last pitch: " + this.getFormattedLastPitch() ;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this._id);
        out.writeString(this._name);
        out.writeInt(this._pitchCount);
        if (this._lastPitch != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            out.writeString(format.format(this._lastPitch));
        }
        else {
            out.writeString("");
        }
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Pitcher> CREATOR = new Parcelable.Creator<Pitcher>() {
        public Pitcher createFromParcel(Parcel in) {
            return new Pitcher(in);
        }

        public Pitcher[] newArray(int size) {
            return new Pitcher[size];
        }
    };

    public int getId() {
        return this._id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String newValue)
    {
        this._name = newValue;
    }

    public int getPitchCount() {
        return this._pitchCount;
    }

    public void setPitchCount(int newValue) {
        this._pitchCount = newValue;
    }

    public void Increment() {
        this._pitchCount++;
        this._lastPitch = new Date();
    }

    public void Decrement() {
        this._pitchCount--;
        this._lastPitch = new Date();
    }

    public void Reset() {
        this._pitchCount = 0;
    }

    public Date getLastPitch() {
        return this._lastPitch;
    }

    public String getFormattedLastPitch() {
        if (this._lastPitch == null)
            return "";
        else {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            return format.format(this._lastPitch);
        }
    }


    public void setLastPitch(Date newValue) {
        this._lastPitch = newValue;
    }

    public void setLastPitch() {
        // default to current datetime
        this.setLastPitch(new Date());
    }

}

