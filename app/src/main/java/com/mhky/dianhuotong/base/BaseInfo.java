package com.mhky.dianhuotong.base;

public class BaseInfo {
    private boolean isFirstOpen=true;
    private int pictureNumber=0;

    public boolean isFirstOpen() {
        return isFirstOpen;
    }

    public void setFirstOpen(boolean firstOpen) {
        isFirstOpen = firstOpen;
    }

    public int getPictureNumber() {
        return pictureNumber;
    }

    public void setPictureNumber(int pictureNumber) {
        this.pictureNumber = pictureNumber;
    }
}
