package com.example.planlist;

import android.os.Parcel;
import android.os.Parcelable;

public class PlanInfo implements Parcelable, Comparable<PlanInfo> {

	private int id;
	private String content;
	private long startTime = -1L;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getStartTime() {
		return startTime;
	}


	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	public long getCompleteTime() {
		return completeTime;
	}


	public void setCompleteTime(long completeTime) {
		this.completeTime = completeTime;
	}

	private long completeTime = -1L;

	public PlanInfo(String ct) {
		setContent(ct);
		startTime = System.currentTimeMillis();
	}


	public boolean isComplete() {
		return completeTime > 0L;
	}

	public void setComplete(boolean cpl) {
		if (cpl) {
			completeTime = System.currentTimeMillis();
		}else{
			completeTime = -1L;
		}
			
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("conent=").append(content);
		sb.append(", startTime=").append(startTime);
		sb.append(", completeTime=").append(completeTime);
		return sb.toString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(content);
		dest.writeLong(startTime);
		dest.writeLong(completeTime);
	}

	private PlanInfo(Parcel in) {
		content = in.readString();
		startTime = in.readLong();
		completeTime = in.readLong();
	}

	public static final Parcelable.Creator<PlanInfo> CREATOR = new Parcelable.Creator<PlanInfo>() {
		public PlanInfo createFromParcel(Parcel in) {
			return new PlanInfo(in);
		}

		public PlanInfo[] newArray(int size) {
			return new PlanInfo[size];
		}
	};

	@Override
	public int compareTo(PlanInfo another) {
		if (this.completeTime <= 0L && another.completeTime <= 0L) {
			return (int) (this.startTime - another.startTime);
		} else if (this.completeTime > 0L) {
			return 1;
		} else {
			return -1;
		}
	}

}
