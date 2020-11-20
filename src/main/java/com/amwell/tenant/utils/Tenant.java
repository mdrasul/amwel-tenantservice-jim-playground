package com.amwell.tenant.utils;



public class Tenant extends TestDataUtils {
    private String id;
    private String name;
    private String enterpriseUri;
    private String callbackUrl;

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getEnterpriseUri ( ) {
        return enterpriseUri;
    }

    public void setEnterpriseUri ( String enterpriseUri ) {
        this.enterpriseUri = enterpriseUri;
    }

    public String getCallbackUrl ( ) {
        return callbackUrl;
    }

    public void setCallbackUrl ( String callbackUrl ) {
        this.callbackUrl = callbackUrl;
    }


}
