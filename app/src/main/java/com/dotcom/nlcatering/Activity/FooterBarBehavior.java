package com.dotcom.nlcatering.Activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.dotcom.nlcatering.Http_Uttils.FooterBarLayout;

public class FooterBarBehavior extends CoordinatorLayout.Behavior<FooterBarLayout> {
    //Required to instantiate as a default behavior
    public FooterBarBehavior() {
    }

    //Required to attach behavior via XML
    public FooterBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //This is called to determine which views this behavior depends on
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent,
                                   FooterBarLayout child,
                                   View dependency) {
        //We are watching changes in the AppBarLayout
        return dependency instanceof RecyclerView;
    }

    //This is called for each change to a dependent view
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent,
                                          FooterBarLayout child,
                                          View dependency) {
        int offset = 10;
        child.setTranslationY(offset);
        return true;
    }
}