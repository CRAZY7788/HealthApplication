package com.example.HealthApplication.application.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.HealthApplication.R;

/**
 *
 */
public class MyChartView extends View {
    public static final int RECT_SIZE = 10;

    public static enum Mstyle {
        Line
    }

    private Mstyle mstyle=Mstyle.Line;
    private Point[] mPoints = new Point[100];


    int bheight=0;
    HashMap<Integer, Double> map;
    ArrayList<Integer> dlk;
    int totalvalue=30;
    int pjvalue=5;
    String xstr,ystr="";
    int margint=15;
    int marginb=40;
    int c=0;
    int resid=0;
    Boolean isylineshow;

    Context context;
    public MyChartView(Context ct) {
        super(ct);
        this.context = ct;
    }

    public MyChartView(Context ct, AttributeSet attrs)
    {
        super( ct, attrs );
        this.context=ct;
    }

    public MyChartView(Context ct, AttributeSet attrs, int defStyle)
    {
        super( ct, attrs, defStyle );
        this.context=ct;
    }


    public void SetTuView(HashMap<Integer, Double> map,int totalvalue,int pjvalue,String xstr,String ystr,Boolean isylineshow)
    {
        this.map=map;
        this.totalvalue=totalvalue;
        this.pjvalue=pjvalue;
        this.xstr=xstr;
        this.ystr=ystr;
        this.isylineshow=isylineshow;
        //屏幕横向
//        act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(c!=0)
            this.setbg(c);
        if(resid!=0)
            this.setBackgroundResource(resid);
        dlk=getintfrommap(map);
        int height=getHeight();
        if(bheight==0)
            bheight=height-marginb;

        int width=getWidth();
        int blwidh=dip2px(context,50);
        int pjsize=totalvalue/pjvalue;//界面布局的尺寸的比例
        // set up paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        for(int i=0;i<pjsize+1;i++)//将顶点的线变为红色的  警戒线
        {
            if(i==pjsize)
                paint.setColor(Color.RED);
            canvas.drawLine(blwidh,bheight-(bheight/pjsize)*i+margint,width,bheight-(bheight/pjsize)*i+margint, paint);//Y坐标
            drawline(pjvalue*i+ystr, blwidh/2, bheight-(bheight/pjsize)*i+margint, canvas);
        }
        ArrayList<Integer> xlist=new ArrayList<>();//记录每个x的值
        paint.setColor(Color.GRAY);
        if(dlk==null)
            return;
        for(int i=0;i<dlk.size();i++)
        {
            xlist.add(blwidh+(width-blwidh)/dlk.size()*i);
            if(isylineshow)
            {
                canvas.drawLine(blwidh+(width-blwidh)/dlk.size()*i,margint,blwidh+(width-blwidh)/dlk.size()*i,bheight+margint, paint);
            }
            drawline(dlk.get(i)+xstr, blwidh+(width-blwidh)/dlk.size()*i, bheight+40, canvas);//X坐标
        }
        mPoints=getpoints(dlk, map, xlist, totalvalue, bheight);

        paint.setColor(Color.GREEN);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(0);

        drawline(mPoints, canvas, paint);

        paint.setColor(Color.RED);
        paint.setStyle(Style.FILL);
        for (int i=0; i<mPoints.length; i++)
        {
            canvas.drawRect(pointToRect(mPoints[i]),paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return true;

    }

    private RectF pointToRect(Point p)
    {
        return new RectF(p.x -RECT_SIZE/2, p.y - RECT_SIZE/2,p.x + RECT_SIZE/2, p.y + RECT_SIZE/2);
    }

    private void drawline(Point[] ps,Canvas canvas,Paint paint)
    {
        Point startp=new Point();
        Point endp=new Point();
        for(int i=0;i<ps.length-1;i++)
        {
            startp=ps[i];
            endp=ps[i+1];
            canvas.drawLine(startp.x,startp.y,endp.x,endp.y, paint);
        }
    }


    private Point[] getpoints(ArrayList<Integer> dlk,HashMap<Integer, Double> map,ArrayList<Integer> xlist,int max,int h)
    {
        Point[] points=new Point[dlk.size()];
        for(int i=0;i<dlk.size();i++)
        {
            int ph=h-(int)(h*(map.get(dlk.get(i))/max));

            points[i]=new Point(xlist.get(i),ph+margint);
        }
        return points;
    }


    private void drawline(String text,int x,int y,Canvas canvas)
    {
        Paint p = new Paint();
        p.setAlpha(0x0000ff);
        p.setTextSize(30);
        p.setColor(getResources().getColor(R.color.white));
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName,Typeface.ITALIC);
        p.setTypeface(font);
        p.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, x, y, p);
    }


    public  int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @SuppressWarnings("rawtypes")
    public ArrayList<Integer> getintfrommap(HashMap<Integer, Double> map)
    {
        ArrayList<Integer> dlk=new ArrayList<>();
        int position=0;
        if(map==null)
            return null;
        Set set= map.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext())
        {
            @SuppressWarnings("rawtypes")
            Map.Entry mapentry  = (Map.Entry)iterator.next();
            dlk.add((Integer)mapentry.getKey());
        }
        for(int i=0;i<dlk.size();i++)
        {
            int j=i+1;
            position=i;
            Integer temp=dlk.get(i);
            for(;j<dlk.size();j++)
            {
                if(dlk.get(j)<temp)
                {
                    temp=dlk.get(j);
                    position=j;
                }
            }

            dlk.set(position,dlk.get(i));
            dlk.set(i,temp);
        }
        return dlk;

    }


    public void setbg(int c)
    {
        this.setBackgroundColor(c);
    }

    public HashMap<Integer, Double> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Double> map) {
        this.map = map;
    }

    public int getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(int totalvalue) {
        this.totalvalue = totalvalue;
    }

    public int getPjvalue() {
        return pjvalue;
    }

    public void setPjvalue(int pjvalue) {
        this.pjvalue = pjvalue;
    }

    public String getXstr() {
        return xstr;
    }

    public void setXstr(String xstr) {
        this.xstr = xstr;
    }

    public String getYstr() {
        return ystr;
    }

    public void setYstr(String ystr) {
        this.ystr = ystr;
    }

    public void setMargint(int margint) {
        this.margint = margint;
    }

    public void setMarginb(int marginb) {
        this.marginb = marginb;
    }

    public void setMstyle(Mstyle mstyle) {
        this.mstyle = mstyle;
    }
}

