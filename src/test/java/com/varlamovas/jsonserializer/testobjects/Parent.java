package com.varlamovas.jsonserializer.testobjects;

public class Parent implements BaseObject {
    public String parentPublicString = "parentPublicStringValue";
    String parentPackagePrivateString = "parentPackagePrivateStringValue";
    protected String parentProtectedString = "parentProtectedStringValue";
    private String parentPrivateString = "parentPrivateStringValue";

    final public String finalParentPublicString = "finalParentPublicString";
    final String finalParentPackagePrivateString = "finalParentPackagePrivateString";
    final protected String finalParentProtectedString = "finalParentProtectedString";
    final private String finalParentPrivateString = "finalParentPrivateString";

    static public String staticParentPublicString = "staticParentPublicString";
    static String staticParentPackagePrivateString = "staticParentPackagePrivateString";
    static protected String staticParentProtectedString = "staticParentProtectedString";
    static private String staticParentPrivateString = "staticParentPrivateString";
}
