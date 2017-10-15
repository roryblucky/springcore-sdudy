package com.rory.springcore.study;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue value) {
        this.propertyValueList.add(value);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
