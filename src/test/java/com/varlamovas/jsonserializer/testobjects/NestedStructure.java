package com.varlamovas.jsonserializer.testobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedStructure {
    Map<String, D> nestedField;

    private void setFields() {
        nestedField = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            nestedField.put("nfkey" + i, D.getInstance());
        }
    }

    public static String getJson() {
        return "{\"nestedField\":{\"nfkey0\":{\"d\":[{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}},{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}}]},\"nfkey1\":{\"d\":[{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}},{\"dkey1\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}},\"dkey0\":{\"c_map\":{\"ckey1\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}],\"ckey0\":[{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]},{\"b\":[{\"fieldA\":\"a0\"},{\"fieldA\":\"a1\"}]}]}}}]}}}";
    }

    public static NestedStructure getInstance() {
        NestedStructure instance = new NestedStructure();
        instance.setFields();
        return instance;
    }
}

class D {
    List<Map<String, C>> d;

    private void setFields() {
        d = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Map<String, C> d_map = new HashMap<>();
            for (int j = 0; j < 2; j++) {
                d_map.put("dkey" + j, C.getInstance());
            }
            d.add(d_map);
        }
    }

    static D getInstance() {
        D instance = new D();
        instance.setFields();
        return instance;
    }
}

class C {

    Map<String, B[]> c_map;

    private void setFields() {
        c_map = new HashMap<>();
        for (int j = 0; j < 2; j++) {
            B[] c;
            c = new B[2];
            for (int i = 0; i < 2; i++) {
                c[i] = B.getInstance();
            }
            c_map.put("ckey" + j, c);
        }
    }

    static C getInstance() {
        C instance = new C();
        instance.setFields();
        return instance;
    }
}

class B {

    List<A> b;

    private void setFields() {
        b = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            b.add(A.getInstance("a" + i));
        }
    }

    static B getInstance() {
        B instance = new B();
        instance.setFields();
        return instance;
    }
}

class A {

    String fieldA;

    static A getInstance(String value) {
        A instance = new A();
        instance.setFields(value);
        return instance;
    }

    private void setFields(String value) {
        fieldA = value;
    }
}
