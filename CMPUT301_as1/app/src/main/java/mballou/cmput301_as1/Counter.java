/*
 * Class Name: Counter
 *
 * Version: Version 1.0
 *
 * Date: October 1st, 2017
 *
 * Copyright (c) Team X, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Students Behavior at
 * University of Alberta
 */

package mballou.cmput301_as1;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


/**
 * Represents a Counter
 *
 * @author Mitchell Ballou
 * @version 1.0
 * @since 1.0
 */
public class Counter implements Parcelable{

    private String name;
    private Date date;
    private int currentValue;
    private int initialValue;
    private String comment;

    /**
     * Constructs a Counter with no comment
     *
     * @param name name of counter
     * @param initialValue initial value of the counter
     */
    public Counter(String name, int initialValue){
        this.name = name;
        this.date = new Date();
        this.initialValue = initialValue;
    }

    /**
     * Constructs a counter with a comment
     *
     * @param name name of counter
     * @param initialValue initial value of the counter
     * @param comment comment for the counter
     */
    public Counter(String name, int initialValue, String comment){
        this.name = name;
        this.date = new Date();
        this.initialValue = initialValue;
        this.comment = comment;
    }

    public void incrementCurrentValue(){
        currentValue++;
    }
    public void decrementCurrentValue(){
        currentValue--;
    }

    @Override
    public String toString(){
        return (name + "   Current Value: " + Integer.toString(this.currentValue));
    }

    @Override
    public int describeContents(){
        return 0;
    }


    /* getters */
    public String getName(){return name;}
    public Date detDate(){return date;}
    public int getInitialValue(){return initialValue;}
    public int getCurrentValue(){return currentValue;}
    public String getComment(){return comment;}

    /* setters */
    public void setDate(Date date){this.date = date;}
    public void setName(String name){this.name = name;}
    public void setInitialValue(int initialValue){this.initialValue = initialValue;}
    public void setCurrentValue(int currentValue){this.currentValue = currentValue;}
    public void setComment(String comment){this.comment = comment;}

    @Override
    public void writeToParcel(Parcel pc, int flags){
        pc.writeString(name);
        pc.writeInt(initialValue);
        pc.writeInt(currentValue);
        pc.writeLong(date.getTime());
        pc.writeString(comment != null ? comment: null);

    }
    public static final Parcelable.Creator<Counter> CREATOR = new Parcelable.Creator<Counter>(){
        public Counter createFromParcel(Parcel in){
            return new Counter(in);
        }
        public Counter[] newArray(int size){
            return new Counter[size];
        }
    };

    private Counter(Parcel in){
        name = in.readString();
        initialValue = in.readInt();
        comment = in.readString();

    }

}
