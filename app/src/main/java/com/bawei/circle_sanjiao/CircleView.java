package com.bawei.circle_sanjiao;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作    者：云凯文
 * 时    间：2017/3/10
 * 描    述：
 * 修改时间：
 */

public class CircleView extends View {

    private int radius;//圆的半径
    private Paint mpaint;//圆的半径

    private Point cPoint;//中心点

    private Point tPoint;
    private Point l_tPoint;
    private Point l_bPoint;
    private Point r_tPoint;
    private Point r_bPoint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //实例化画笔
        mpaint = new Paint();
        //设置画笔的颜色
        mpaint.setColor(Color.RED);
        //设置空心圆
        mpaint.setStyle(Paint.Style.STROKE);
        //设置画笔的宽度
        mpaint.setStrokeWidth(3);
        //抗锯齿
        mpaint.setAntiAlias(true);

        /**获取自定义属性*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        //圆的半径
        radius = (int) typedArray.getDimension(R.styleable.CircleView_circle_radius, 50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置中心点的位置
        cPoint = new Point(getWidth() / 2, getHeight() / 2);

        // 上
        tPoint = new Point(cPoint.getX(), cPoint.getY() - radius);

        //左上
        // 计算CF的长度
        double gen5 = Math.sqrt(5);
        double du36 = Math.toRadians(36);
        float cf = (float) Math.sqrt((gen5 - 1) * radius / 2 * (gen5 - 1) * radius / 2 + radius * radius);
        float cfx = (float) (cf * Math.cos(du36));
        float cfy = (float) (cf * Math.sin(du36));
        l_tPoint = new Point(tPoint.getX() - cfx, tPoint.getY() + cfy);

        //右上
        r_tPoint = new Point(tPoint.getX() + cfx, tPoint.getY() + cfy);

        //左下
        // 计算CM的长度
        double du18 = Math.toRadians(18);
        double cm = radius * 2 * Math.cos(du18);
        float cmx = (float) (cm * Math.sin(du18));
        float cmy = (float) (cm * Math.cos(du18));
        l_bPoint = new Point(tPoint.getX() - cmx, tPoint.getY() + cmy);

        //右下
        r_bPoint = new Point(tPoint.getX() + cmx, tPoint.getY() + cmy);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        canvas.drawCircle(cPoint.getX(), cPoint.getY(), radius, mpaint);

        canvas.drawLine(tPoint.getX(), tPoint.getY(), r_bPoint.getX(), r_bPoint.getY(), mpaint);
        canvas.drawLine(r_bPoint.getX(), r_bPoint.getY(), l_tPoint.getX(), l_tPoint.getY(), mpaint);
        canvas.drawLine(l_tPoint.getX(), l_tPoint.getY(), r_tPoint.getX(), r_tPoint.getY(), mpaint);
        canvas.drawLine(r_tPoint.getX(), r_tPoint.getY(), l_bPoint.getX(), l_bPoint.getY(), mpaint);
        canvas.drawLine(l_bPoint.getX(), l_bPoint.getY(), tPoint.getX(), tPoint.getY(), mpaint);
    }
}
