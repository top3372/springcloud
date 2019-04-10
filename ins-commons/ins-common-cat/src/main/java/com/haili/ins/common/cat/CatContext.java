package com.haili.ins.common.cat;

import com.dianping.cat.Cat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CatContext implements Cat.Context, Serializable {

    private Map<String, String> properties = new HashMap<>();

    @Override
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}