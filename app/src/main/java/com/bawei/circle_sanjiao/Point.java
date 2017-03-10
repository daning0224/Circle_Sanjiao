package com.bawei.circle_sanjiao;

/**
 * 作    者：云凯文
 * 时    间：2017/3/10
 * 描    述：
 * 修改时间：
 */

public class Point {

    private float x;
    private float y;

    public Point() {
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float setX(float x) {
        this.x = x;
        return x;
    }

    public float getY() {
        return y;
    }

    public int setY(float y) {
        this.y = y;
        return 0;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
