package com.schneewittchen.rosandroid.ui.views.details;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.entities.widgets.IPositionEntity;
import com.schneewittchen.rosandroid.ui.general.Position;
import com.schneewittchen.rosandroid.utility.Utils;

/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.0
 * @created on 17.03.21
 */
class WidgetViewHolder implements IBaseViewHolder, TextView.OnEditorActionListener{

    protected EditText xEdittext, yEdittext, widthEditText, heightEdittext;


    @Override
    public void baseInitView(View view) {

        xEdittext       = view.findViewById(R.id.x_edit_text);
        yEdittext       = view.findViewById(R.id.y_edit_text);
        widthEditText   = view.findViewById(R.id.width_edit_text);
        heightEdittext  = view.findViewById(R.id.height_edit_text);

        xEdittext.setOnEditorActionListener(this);
        yEdittext.setOnEditorActionListener(this);
        widthEditText.setOnEditorActionListener(this);
        heightEdittext.setOnEditorActionListener(this);
    }

    @Override
    public void baseBindEntity(BaseEntity entity) {
        Position position = ((IPositionEntity)entity).getPosition();

        xEdittext.setText(String.valueOf(position.x));
        yEdittext.setText(String.valueOf(position.y));
        widthEditText.setText(String.valueOf(position.width));
        heightEdittext.setText(String.valueOf(position.height));
    }

    @Override
    public void baseUpdateEntity(BaseEntity entity) {
        Position position = new Position();
        position.x = Integer.parseInt(xEdittext.getText().toString());
        position.y = Integer.parseInt(yEdittext.getText().toString());
        position.width = Integer.parseInt(widthEditText.getText().toString());
        position.height = Integer.parseInt(heightEdittext.getText().toString());

        ((IPositionEntity)entity).setPosition(position);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId){
            case EditorInfo.IME_ACTION_DONE:
            case EditorInfo.IME_ACTION_NEXT:
            case EditorInfo.IME_ACTION_PREVIOUS:
                Utils.hideSoftKeyboard(v);
                v.clearFocus();
                return true;
        }

        return false;
    }
}
