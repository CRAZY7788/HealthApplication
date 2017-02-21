package com.example.HealthApplication.application.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.HealthApplication.R;

/**
 *
 */
public class ExpandView extends LinearLayout {

    private ViewGroup mContainer;
    private ViewGroup mHeaderContainer;
    private TextView mTextView;
    private ImageView mImageView;
    private TransitionDrawable mExpandDrawable;
    private int mSizeMultiplier = 4;
    private IExpandListener mListener;
    private int mAnimationDuration = -1;
    private int DURATION = 100;
    private boolean isAnimationCollapsedComplete = false;
    private boolean isAnimationExpandedComplete = false;
    private boolean isOnCollapseComplete = false;
    private boolean isOnExpandComplete = false;

    public ExpandView(Context context) {
        this(context,null);
    }

    public ExpandView(Context context, AttributeSet attrs) {
        this(context,attrs, R.layout.expand_view_layout);
    }

    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }


    protected void init(final Context context, AttributeSet attrs, int style) {

        setBackground(null);
        final View rootView = View.inflate(context, R.layout.expand_view_layout, this);
        mContainer = (ViewGroup) rootView.findViewById(R.id.container);
        mImageView= (ImageView) rootView.findViewById(R.id.expandable_layout_arrow);
        ViewGroup header = (ViewGroup) rootView.findViewById(R.id.expandable_layout_header);
        setHeaderView(context, header);
        mTextView = (TextView) rootView.findViewById(R.id.title);
        mHeaderContainer = (ViewGroup) findViewById(R.id.expandable_layout_header_container);
        int[] m = new int[3];

        TypedArray a = context.obtainStyledAttributes(attrs, m,
                style, 0);
        mContainer.setBackground(a.getDrawable(10));

        if(mTextView != null) {
            mTextView.setTextSize(20);

//            ColorStateList list = a.getColorStateList(10);
//            if(list != null) {
                mTextView.setTextColor(100);
//            }
            mHeaderContainer.setBackgroundColor(200);

            if(context.getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                mTextView.setGravity(Gravity.END);
            }
            else {
                mTextView.setGravity(Gravity.START);
            }
        }
        a.recycle();

        mHeaderContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClick(v);

            }
        });

        mExpandDrawable = new TransitionDrawable(new Drawable[] {
                getResources().getDrawable(R.drawable.arrow_down),
                getResources().getDrawable(R.drawable.arrow_up)
        });

        mExpandDrawable.setCrossFadeEnabled(true);
        mImageView.setImageDrawable(mExpandDrawable);
    }

    protected void handleOnClick(View v) {
        if (isSelected()) {
            collapse();
        } else {
            expand();
        }

    }

    protected void onExpanded(){

    }

    protected void onCollapsed() {

    }

    public void setShowExpandIndicator(boolean showIndicator) {
        mImageView.setVisibility(showIndicator ? VISIBLE : INVISIBLE);
    }

    protected void setHeaderView(Context context, ViewGroup container) {
    }

    protected ViewGroup getHeaderContainer() {
        return mHeaderContainer;
    }


    public void setStartingDrawable(int src) {
        if(mTextView != null) {
//            mTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(src, 0, 0, 0);
        }
    }

    public void setText(String str) {
        if(mTextView != null) {
            mTextView.setText(str);
        }
    }

    public boolean isChildContainerVisible(){
        return mContainer.getVisibility() == View.VISIBLE;
    }

    public void setChildContainerVisibility(int visibility) {
        mContainer.setVisibility(visibility);
    }

    public void expand() {
        if(isEnabled() && !isChildContainerVisible()) {
            final View v = mContainer;

            int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
            int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
            final int targetHeight = v.getMeasuredHeight();

            // Older versions of android (pre API 21) cancel animations for views with a height of 0.
            ViewGroup.LayoutParams params = v.getLayoutParams();
            if (params != null) {
                params.height = 1;
            }
            v.setVisibility(View.VISIBLE);
            v.setAlpha(0);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    ViewGroup.LayoutParams params = v.getLayoutParams();
                    if (params != null) {
                        params.height = interpolatedTime >= 1
                                ? LayoutParams.WRAP_CONTENT
                                : (int) (targetHeight * interpolatedTime);
                        v.setAlpha(interpolatedTime);
                        v.requestLayout();
                    }

                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            if(mAnimationDuration == -1) {
                mAnimationDuration = Math.min(500,
                        (int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density) * mSizeMultiplier);
            }

            a.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnimationExpandedComplete = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            a.setDuration(mAnimationDuration);
            v.startAnimation(a);

            onExpanded();

            setSelected(true);

            if (isAnimationExpandedComplete) {
                if(isOnCollapseComplete) {
                    expandingOccurs();
                    isOnCollapseComplete = false;
                }
                isAnimationExpandedComplete = false;
            }else{
                expandingOccurs();
            }
        }

        isOnExpandComplete = true;
    }

    public void collapse() {
        if(isEnabled() && isChildContainerVisible()) {
            final View v = mContainer;
            final int initialHeight = v.getMeasuredHeight();

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime >= 1) {
                        v.setVisibility(View.GONE);
                    } else {
                        ViewGroup.LayoutParams params = v.getLayoutParams();
                        if (params != null) {
                            params.height = initialHeight - (int) (initialHeight * interpolatedTime);
                            v.requestLayout();
                            v.setAlpha(1 - interpolatedTime);
                        }
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            if(mAnimationDuration == -1) {
                mAnimationDuration = (int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) * mSizeMultiplier;
            }
            a.setDuration(mAnimationDuration);
            v.startAnimation(a);

            a.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnimationCollapsedComplete = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            onCollapsed();

            if(isAnimationCollapsedComplete){
                if(isOnExpandComplete){
                    collapseOccurs();
                    isOnExpandComplete = false;
                }
                isAnimationExpandedComplete = false;
            }else{
                collapseOccurs();
            }
        }

        isOnCollapseComplete = true;
    }

    private void collapseOccurs(){
        mExpandDrawable.reverseTransition(DURATION);
        setSelected(false);
        if (mListener != null) {
            mListener.onCollapsed(this);
        }
    }

    private void expandingOccurs(){
        if (mListener != null) {
            mListener.onExpanded(this);
        }
        if (mExpandDrawable != null) {
            mExpandDrawable.startTransition(DURATION);
        }
    }

    public void setAnimationDuration(int milliseconds) {
        mAnimationDuration = milliseconds;
    }

    @Override
    public void addView(View child) {
        if (mContainer != null) {
            mContainer.addView(child);
        } else {
            super.addView(child);
        }
    }

    public void clearContainer() {
        if(mContainer != null) {
            mContainer.removeAllViews();
        }
    }

    @Override
    public void addView(View child, int index) {
        if (mContainer != null) {
            mContainer.addView(child, index);
        } else {
            super.addView(child, index);
        }
    }

    @Override
    public void addView(View child, int width, int height) {
        if (mContainer != null) {
            mContainer.addView(child, width, height);
        } else {
            super.addView(child, width, height);
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (mContainer != null) {
            mContainer.addView(child, params);
        } else {
            super.addView(child, params);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mContainer != null) {
            mContainer.addView(child, index, params);
        } else {
            super.addView(child, index, params);
        }
    }

    public void setExpanderListener(IExpandListener listener) {
        mListener = listener;
    }



    public interface IExpandListener {

        void onExpanded(ExpandView layout);


        void onCollapsed(ExpandView layout);
    }
}

