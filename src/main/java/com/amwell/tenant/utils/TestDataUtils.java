package com.amwell.tenant.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataUtils {

    public static Tenant createOneSampleTenant ( ) {
        Tenant tenant = new Tenant ( );
        tenant.setId ( "ID" + RandomStringUtils.randomAlphabetic ( 2 ) );
        tenant.setName ( "TenantName" + RandomStringUtils.randomAlphabetic ( 20 ) );
        tenant.setEnterpriseUri ( "http://abc.com" );
        tenant.setCallbackUrl ( "http://desf.com" );
        return tenant;
    }

    public static Tenant setIdFor ( int idLength ) {
        Tenant tenant = new Tenant ( );
        tenant.setId ( "TID-" + RandomStringUtils.randomAlphabetic ( idLength ) );
        return tenant;
    }

    public static Tenant setName ( int nameLength ) {
        Tenant tenant = new Tenant ( );
        tenant.setName ( "TenantName" + RandomStringUtils.randomAlphabetic ( nameLength ) );
        ;
        return tenant;
    }

    public static Tenant createRandom ( ) {
        return createOneSampleTenant ( );
    }
}
