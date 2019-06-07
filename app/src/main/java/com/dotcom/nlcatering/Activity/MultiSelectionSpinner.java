package com.dotcom.nlcatering.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectionSpinner extends android.support.v7.widget.AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener {
    String[] _items = null;
    boolean[] mSelection = null;
    int selectedCount;
    ArrayAdapter<String> simple_adapter;
    Context mContext;

    public MultiSelectionSpinner(Context context) {
        super(context);
        this.mContext = context;
        simple_adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(simple_adapter);
    }

    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        simple_adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(simple_adapter);
    }

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        selectedCount = 0;
        for (int i = 0; i < mSelection.length; i++) {
            if (mSelection[i]) {
                selectedCount++;
            }
        }
        if (selectedCount <= 2) {

            if (mSelection != null && which < mSelection.length) {
                mSelection[which] = isChecked;

                simple_adapter.clear();
                simple_adapter.add(buildSelectedItemString());
            } else {
                throw new IllegalArgumentException(
                        "Argument 'which' is out of bounds.");
            }
        } else {
            mSelection[which] = false;
            Toast.makeText(mContext, "Please select any two ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean performClick() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(_items, mSelection, this);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int arg1) {
                Log.e("selection ", "onClick: " + new GsonBuilder().create().toJson(mSelection));

               /* if (selectedCount == 4) {
                    dialogInterface.dismiss();
                } else {
                    dialogInterface.cancel();
                   // builder.show();
                    Toast.makeText(mContext, "Enter something", Toast.LENGTH_SHORT).show();
                }*/

            }
        });


        builder.show();
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.");
    }


    public void setItems(List<String> items) {
        _items = items.toArray(new String[items.size()]);
        mSelection = new boolean[_items.length];
        simple_adapter.clear();
        simple_adapter.add(_items[0]);
        Arrays.fill(mSelection, false);
    }

    public void setSelection(int index) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
        }
        if (index >= 0 && index < mSelection.length) {
            mSelection[index] = true;
        } else {
            throw new IllegalArgumentException("Index " + index
                    + " is out of bounds.");
        }
        simple_adapter.clear();
        simple_adapter.add(buildSelectedItemString());
    }


    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;
        selectedCount = 0;

        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                //selectedCount++;
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;

                sb.append(_items[i]);
            }
        }


        return sb.toString();
    }

}