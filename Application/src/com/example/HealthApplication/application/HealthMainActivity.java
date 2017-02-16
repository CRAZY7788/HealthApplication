package com.example.HealthApplication.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.util.ArrayList;
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
        init();
    }
    private void init(){
        mLeftPanel = (LinearLayout) findViewById(id.ll_home_page_left_panel);
        mRightPanel = (LinearLayout) findViewById(id.ll_home_page_right_panel);
        mExpandableLayoutDataList = new ArrayList<>();

        showButtons();
        setupExpandableList();
    }

    private void setupExpandableList() {

        for (final ExpandableLayoutData layoutData : mExpandableLayoutDataList) {
            final LinearLayout workflowSelectionLayout = (LinearLayout) findViewById(R.id.recyclerView);
            ExpandView layout = new ExpandView(this);
            View mainView = View.inflate(this, R.layout.sub_expand_container, null);
            LinearLayout linearLayout = (LinearLayout) mainView.findViewById(R.id.sub);
            TextView childTextView = (TextView) linearLayout.findViewById(id.tv_task_description);
            Button button = (Button) linearLayout.findViewById(id.bt_task_open);
            layout.addView(mainView);

            childTextView.setText(layoutData.getChildStringId());

            button.setText("button");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(layoutData.getTask());
                }
            });


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


    private ExpandableLayoutData getLayoutDataForTask(int task){
        ExpandableLayoutData layoutData;
        switch(task){
            case 0 :
                layoutData = new ExpandableLayoutData(id.personal_info_resource_id,
                        getString(string.personal_info_header),
                        getString(string.personal_info_child_text),
                        task);
                break;

            case 1 :
                layoutData = new ExpandableLayoutData(id.diet_record_resource_id,
                        getString(string.diet_record_header),
                        getString(string.diet_record_child_text),
                        task);
                break;
            case 2 :
                layoutData = new ExpandableLayoutData(id.fitness_resource_id,
                        getString(string.fitness_header),
                        getString(string.fitness_child_text),
                        task);
                break;
            case 3 :
                layoutData = new ExpandableLayoutData(id.self_test_resource_id,
                        getString(string.self_test_header),
                        getString(string.self_test_child_text),
                        task);
                break;
            case 4 :
                layoutData = new ExpandableLayoutData(id.medical_test_resource_id,
                        getString(string.medical_test_header),
                        getString(string.medical_test_child_text),
                        task);
                break;
            case 5 :
                layoutData = new ExpandableLayoutData(id.line_chart_resource_id,
                        getString(string.line_chart_header),
                        getString(string.line_chart_child_text),
                        task);
                break;

            default:
                layoutData = new ExpandableLayoutData(id.personal_info_resource_id,
                    getString(string.personal_info_header),
                    getString(string.personal_info_child_text),
                    task);
                break;

        }
        return layoutData;
    }

    private void onSelected(int task){
        Intent intent;
        switch(task){
            case 0:
                intent = new Intent();
//                intent.putExtra();
        }

    }

    public void showButtons() {
        clearExpandableListView();
        mExpandableLayoutDataList.clear();
        mExpandableLayoutDataList.add(getLayoutDataForTask(0));
        mExpandableLayoutDataList.add(getLayoutDataForTask(1));
        mExpandableLayoutDataList.add(getLayoutDataForTask(2));
        mExpandableLayoutDataList.add(getLayoutDataForTask(3));
        mExpandableLayoutDataList.add(getLayoutDataForTask(4));
        mExpandableLayoutDataList.add(getLayoutDataForTask(5));
    }

    private void clearExpandableListView(){
        mExpandableLayoutDataList.clear();
    }



    private static final class ExpandableLayoutData {
        private int mResourceId;
        private String mHeaderStringId;
        private String mChildStringId;
        private int mTask;

        public ExpandableLayoutData(int resourceId, String headerTitleId, String childTextId,int task) {
            this.mResourceId = resourceId;
            this.mHeaderStringId = headerTitleId;
            this.mChildStringId = childTextId;
            this.mTask = task;
        }

        private int getResourceId() {
            return mResourceId;
        }
        private String getHeaderStringId() {
            return mHeaderStringId;
        }
        private String getChildStringId() {
            return mChildStringId;
        }

        private int getTask(){
            return mTask;
        }
    }
}
