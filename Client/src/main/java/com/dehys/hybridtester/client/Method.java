package com.dehys.hybridtester.client;

public enum Method {
    DISABLE("disable"),
    ENABLE("enable"),
    LOAD("load"),
    RELOAD("reload"),
    UNLOAD("unload");

    private final String method;

    Method(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return method;
    }

    public static Method fromString(String method) {
        for (Method m : Method.values()) {
            if (m.toString().equalsIgnoreCase(method)) {
                return m;
            }
        }
        return null;
    }
}
