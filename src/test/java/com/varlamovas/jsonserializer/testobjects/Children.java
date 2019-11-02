package com.varlamovas.jsonserializer.testobjects;

public class Children extends Parent implements BaseObject {

    public String childrenPublicString = "childrenPublicStringValue";
    String childrenPackagePrivateString = "childrenPackagePrivateStringValue";
    protected String childrenProtectedString = "childrenProtectedStringValue";
    private String childrenPrivateString = "childrenPrivateStringValue";

    final public String finalChildrenPublicString = "finalChildrenPublicString";
    final String finalChildrenPackagePrivateString = "finalChildrenPackagePrivateString";
    final protected String finalChildrenProtectedString = "finalChildrenProtectedString";
    final private String finalChildrenPrivateString = "finalChildrenPrivateString";

    static public String staticChildrenPublicString = "staticChildrenPublicString";
    static String staticChildrenPackagePrivateString = "staticChildrenPackagePrivateString";
    static protected String staticChildrenProtectedString = "staticChildrenProtectedString";
    static private String staticChildrenPrivateString = "staticChildrenPrivateString";

    public static String getJson() {
        return "{\"childrenPublicString\":\"childrenPublicStringValue\",\"childrenPackagePrivateString\":\"childrenPackagePrivateStringValue\",\"childrenProtectedString\":\"childrenProtectedStringValue\",\"childrenPrivateString\":\"childrenPrivateStringValue\",\"finalChildrenPublicString\":\"finalChildrenPublicString\",\"finalChildrenPackagePrivateString\":\"finalChildrenPackagePrivateString\",\"finalChildrenProtectedString\":\"finalChildrenProtectedString\",\"finalChildrenPrivateString\":\"finalChildrenPrivateString\",\"parentPublicString\":\"parentPublicStringValue\",\"parentPackagePrivateString\":\"parentPackagePrivateStringValue\",\"parentProtectedString\":\"parentProtectedStringValue\",\"parentPrivateString\":\"parentPrivateStringValue\",\"finalParentPublicString\":\"finalParentPublicString\",\"finalParentPackagePrivateString\":\"finalParentPackagePrivateString\",\"finalParentProtectedString\":\"finalParentProtectedString\",\"finalParentPrivateString\":\"finalParentPrivateString\"}";
    }

    public static Children getInstance() {
        Children instance = new Children();
        return instance;
    }

}
