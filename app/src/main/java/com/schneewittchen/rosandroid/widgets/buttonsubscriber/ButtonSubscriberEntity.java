package com.schneewittchen.rosandroid.widgets.buttonSubscriber;

import com.schneewittchen.rosandroid.model.entities.widgets.SubscriberLayerEntity;
import com.schneewittchen.rosandroid.model.entities.widgets.SubscriberWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import org.ros.node.topic.Subscriber;

import std_msgs.Bool;
import std_msgs.Int8;

public class ButtonSubscriberEntity extends SubscriberWidgetEntity {
    public String text;
    public int rotation;

    public ButtonSubscriberEntity() {
        this.width = 3;
        this.height = 1;
        this.topic = new Topic("/optoSensor", Int8._TYPE);
        this.text = "Warning";
        this.rotation = 0;
    }
}


