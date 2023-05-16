package com.schneewittchen.rosandroid.widgets.buttonSubscriber;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.entities.widgets.SubscriberLayerEntity;
import com.schneewittchen.rosandroid.ui.views.details.SubscriberWidgetViewHolder;
import com.schneewittchen.rosandroid.ui.views.widgets.SubscriberLayerView;
import com.schneewittchen.rosandroid.utility.Utils;
import com.schneewittchen.rosandroid.widgets.button.ButtonEntity;

import java.util.Collections;
import java.util.List;

import std_msgs.Int8;

public class ButtonSubscriberDetailVH extends SubscriberWidgetViewHolder {

    private EditText textText;
    private Spinner rotationSpinner;
    private ArrayAdapter<CharSequence> rotationAdapter;


    @Override
    public void initView(View view) {
        textText = view.findViewById(R.id.btnTextTypeText);
        rotationSpinner = view.findViewById(R.id.btnTextRotation);

        // Init spinner
        rotationAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.button_rotation, android.R.layout.simple_spinner_dropdown_item);

        rotationSpinner.setAdapter(rotationAdapter);
    }

    @Override
    protected void bindEntity(BaseEntity entity) {
        ButtonSubscriberEntity buttonSEntity = (ButtonSubscriberEntity) entity;

        textText.setText(buttonSEntity.text);
        String degrees = Utils.numberToDegrees(buttonSEntity.rotation);
        rotationSpinner.setSelection(rotationAdapter.getPosition(degrees));
    }

    @Override
    protected void updateEntity(BaseEntity entity) {
        ButtonSubscriberEntity buttonSEntity = (ButtonSubscriberEntity) entity;

        buttonSEntity.text = String.valueOf(Integer.parseInt(textText.getText().toString()));
        String degrees = rotationSpinner.getSelectedItem().toString();
        buttonSEntity.rotation = Utils.degreesToNumber(degrees);
    }

    @Override
    public List<String> getTopicTypes() {
        return Collections.singletonList(Int8._TYPE);
    }

}
