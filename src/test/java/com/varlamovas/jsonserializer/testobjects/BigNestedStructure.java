package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigNestedStructure {
    Map<String, BigD> nestedField;

    private void setFields() {
        nestedField = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            nestedField.put("nfkey" + i, BigD.getInstance());
        }
    }

    public static String getJson() {
        return "{\"nestedField\":{\"nfkey0\":{\"d\":[{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}},{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}}]},\"nfkey1\":{\"d\":[{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}},{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}}]}}}";
    }

    public static BigNestedStructure getInstance() {
        BigNestedStructure instance = new BigNestedStructure();
        instance.setFields();
        return instance;
    }
}

class BigD {
    List<Map<String, BigC>> d;

    private void setFields() {
        d = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, BigC> d_map = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                d_map.put("dkey" + j, BigC.getInstance());
            }
            d.add(d_map);
        }
    }

    static BigD getInstance() {
        BigD instance = new BigD();
        instance.setFields();
        return instance;
    }
}

class BigC {

    Map<String, BigB[]> c_map;

    private void setFields() {
        c_map = new HashMap<>();
        for (int j = 0; j < 10; j++) {
            BigB[] c;
            c = new BigB[10];
            for (int i = 0; i < 10; i++) {
                c[i] = BigB.getInstance();
            }
            c_map.put("ckey" + j, c);
        }
    }

    static BigC getInstance() {
        BigC instance = new BigC();
        instance.setFields();
        return instance;
    }
}

class BigB {

    List<BigA> b;

    private void setFields() {
        b = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            b.add(BigA.getInstance("a" + i));
        }
    }

    static BigB getInstance() {
        BigB instance = new BigB();
        instance.setFields();
        return instance;
    }
}

class BigA {

    String fieldA;

    static BigA getInstance(String value) {
        BigA instance = new BigA();
        instance.setFields(value);
        return instance;
    }

    private void setFields(String value) {
        fieldA = value;
    }
}
