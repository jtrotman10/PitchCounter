package com.westglenn.www.pitchcounter;

import java.util.ArrayList;

/**
 * Created by jtrotman on 9/14/2014.
 */
public class StateSingleton
{
    private static StateSingleton instance;

    private ArrayList<Pitcher> list;
    private int currentPitcherId;

    public StateSingleton() {
        this.list = new ArrayList<Pitcher>();
        // must have 1 pitcher
        Pitcher p = new Pitcher();
        p.setName("Pitcher 1");
        this.list.add(p);

        currentPitcherId = 0;
    }

    public ArrayList<Pitcher> getList()
    {
        return this.list;
    }

    public int getCurrentPitcherId()
    {
        return this.currentPitcherId;
    }

    public void setCurrentPitcherId(int newValue)
    {
        this.currentPitcherId = newValue;
    }

    public Pitcher getCurrentPitcher() {
        for (Pitcher p : this.list) {
            if (p.getId() == this.currentPitcherId) {
                return p;
            }
        }
        return null;
    }

    public static StateSingleton getInstance()
    {
        if (instance == null)
        {
            instance = new StateSingleton();
        }
        return instance;
    }
}