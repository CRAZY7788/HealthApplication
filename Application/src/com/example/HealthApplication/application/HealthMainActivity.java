package com.example.HealthApplication.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.HealthApplication.R;
import com.example.HealthApplication.application.fragment.ExpandView;

import java.util.List;

import static com.example.HealthApplication.R.*;

public class HealthMainActivity extends Activity {
    private LinearLayout mLeftPanel;
    private LinearLayout mRightPanel;
    List<ExpandableLayoutData> mExpandableLayoutDataList;
    private LinearLayout mCyclingLayout;
    private ScrollView mScrollView;
    private ExpandView mExpandableLayout;



    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.main);
    }
    private void init(){
        mLeftPanel = (LinearLayout) findViewById(id.ll_home_page_left_panel);
        mRightPanel = (LinearLayout) findViewById(id.ll_home_page_right_panel);
    }

    private void setupExpandableList() {
        for(int i = 0;i<4;i++){
//        for (final ExpandableLayoutData layoutData : mExpandableLayoutDataList) {
            final LinearLayout workflowSelectionLayout = (LinearLayout) findViewById(R.id.recyclerView);
            ExpandView layout = new ExpandView(this);
            View mainView = View.inflate(this, R.layout.sub_expand_container, null);
            LinearLayout linearLayout = (LinearLayout) mainView.findViewById(R.id.sub);

            layout.addView(mainView);

            //Don't show an icon
            layout.setStartingDrawable(0);

            layout.setExpanderListener(new ExpandView.IExpandListener()  {
                @Override
                public void onExpanded(ExpandView layout) {
                    mExpandableLayout = layout;

                    for (int i = 0; i < workflowSelectionLayout.getChildCount(); i++) {
                        if (!workflowSelectionLayout.getChildAt(i).equals(layout)) {
                            ((ExpandView) workflowSelectionLayout.getChildAt(i)).collapse();
                        }
                    }

                    Animation cardExpanding = layout.findViewById(R.id.container).getAnimation();
                    cardExpanding.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Rect scrollBounds = new Rect();
//                            mScrollView.getGlobalVisibleRect(scrollBounds);
                            Rect buttonBounds = new Rect();
//                            mExpandableLayout.getGlobalVisibleRect(buttonBounds);
//                            if(scrollBounds.bottom <= buttonBounds.bottom) {
//                                mScrollView.smoothScrollTo(0, mExpandableLayout.getBottom());
//                            }
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                @Override
                public void onCollapsed(ExpandView layout) {
                }
            });

            workflowSelectionLayout.addView(layout);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.setMargins(0, 0, 0, 10);
            }
        }
    }


    private static final class ExpandableLayoutData {
        private int mResourceId;
        private int mHeaderStringId;
        private int mChildStringId;
        private int mTask;

        public ExpandableLayoutData(int resourceId, int headerTitleId, int childTextId,int task) {
            this.mResourceId = resourceId;
            this.mHeaderStringId = headerTitleId;
            this.mChildStringId = childTextId;
            this.mTask = task;
        }
        private int getResourceId() {
            return mResourceId;
        }
        private int getHeaderStringId() {
            return mHeaderStringId;
        }
        private int getChildStringId() {
            return mChildStringId;
        }
    }
}
