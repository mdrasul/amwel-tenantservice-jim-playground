package com.amwell.tenant.service;

import com.amwell.tenant.utils.Tenant;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.ArrayList;

public class TenantService extends BasePage {

    //### object
    ObjectMapper mapper = new ObjectMapper ( );


    // ### Constructor
    public TenantService ( ) throws IOException {
        super ( );
    }

    // ### Base method
    public boolean createTenantUsingPostMethodVarification (Tenant sampleTenant) {
        Response response = createTenantUsingPostMethod ( sampleTenant );
        try {
            // == >> Return true / false based on response status..
            if ( response.getStatusCode ( ) == 202 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Faild to post Tenant" );
        }
    }

    public boolean createTenantUsingPostMethodVarificationForNegativeTest (Tenant sampleTenant) {
        Response response = createTenantUsingPostMethod ( sampleTenant );
        try {
            // == >> Return true / false based on response status..
            if ( response.getStatusCode ( ) != 202 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Faild to post Tenant" );
        }
    }

    public int createTenantUsingPostMethodStuscode (Tenant sampleTenant) {

        Response response = createTenantUsingPostMethod ( sampleTenant );
        try {

            return response.getStatusCode ( );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return response.getStatusCode ( );
        }


    }

    public int updateTenantUsingPostMethodStuscode (Tenant sampleTenant) {

        Response response = updateTenantUsingPutMethod ( sampleTenant );
        try {

            return response.getStatusCode ( );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return response.getStatusCode ( );
        }


    }

    public boolean updateTenantInfoUsingPutMethodVarificationNegativeTest (Tenant sampleTenant) {
        Response response = updateTenantUsingPutMethod ( sampleTenant );
        try {
            // == >> Return true / false based on response status..
            if ( response.getStatusCode ( ) != 202 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Faild to put Tenant" );
        }
    }

    public boolean updateTenantInfoUsingPutMethodVarification (Tenant sampleTenant) {
        Response response = updateTenantUsingPutMethod ( sampleTenant );
        try {
            // == >> Return true / false based on response status..
            if ( response.getStatusCode ( ) == 202 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Faild to put Tenant" );
        }
    }

    public int getOneTenantStatusCodeUsingGetMethod (Tenant sampleTenant) {
        Response response = null;
        try {
            response = getOneTenantMethod ( sampleTenant.getId ( ) );
            return response.getStatusCode ( );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return response.getStatusCode ( );
        }

    }

    public boolean validateGetOneTenantApiResponseBody (Tenant sampleTenant) {

        Response response = getOneTenantMethod ( sampleTenant.getId ( ) );
        String responseBody = response.getBody ( ).asString ( );

        JsonPath js = new JsonPath ( responseBody );
        String varifyId = js.get ( "id" );
        String varifyName = js.getString ( "name" );

        try {
            if ( varifyId.contentEquals ( sampleTenant.getId ( ) ) && varifyName.contentEquals ( sampleTenant.getName ( ) ) ) {
                return true;
            } else {
                throw new RuntimeException ( "Data dont match" );
            }

        } catch ( Exception e ) {
            e.printStackTrace ( );
            return false;
        }
    }

    public boolean getTenantGetAllContainData (String id, String name) throws IOException {

        ArrayList < String > arrayList = tenantAllDataGetMethod ( );
        try {
            if ( arrayList.contains ( id ) && arrayList.contains ( name ) ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
        return false;

    }

    public boolean getTenantGetAllContainCount ( ) throws IOException {

        ArrayList < String > arrayList = tenantAllDataGetMethod ( );
        try {
            if ( arrayList.size ( ) > 0 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
        return false;

    }

    public boolean softDeleteTenantInfoUsingDeleteMethod (Tenant sampleTenant) {
        Response response = null;

        try {
            RestAssured.baseURI = BASE_URL;
            String accessToken = getAccessTokenForAdmin ( );
            RequestSpecification requestSpec = RestAssured.given ( );
            requestSpec.auth ( ).preemptive ( ).oauth2 ( accessToken );
            requestSpec.contentType ( "application/json" );
            response = requestSpec.delete ( "/api/v1/tenant/" + sampleTenant.getId ( ) );

        } catch ( Exception e ) {
            e.printStackTrace ( );
            return false;
        }

        // == >> Return true / false based on response status..
        if ( response.getStatusCode ( ) == 202 ) {
            System.out.println ( "soft Delete Status code is " + response.getStatusCode ( ) );
            return true;
        } else {
            return false;
        }
    }

    public boolean hardDeleteTenantInfoUsingDeleteMethod (Tenant sampleTenant) {
        Response response = null;

        try {
            RestAssured.baseURI = BASE_URL;
            String accessToken = getAccessTokenForAdmin ( );
            RequestSpecification requestSpec = RestAssured.given ( );
            requestSpec.auth ( ).preemptive ( ).oauth2 ( accessToken );
            requestSpec.contentType ( "application/json" );
            requestSpec.param ( "isHardDelete", true );
            response = requestSpec.delete ( "/api/v1/tenant/" + sampleTenant.getId ( ) );

        } catch ( Exception e ) {
            e.printStackTrace ( );
            return false;
        }

        // == >> Return true / false based on response status..
        if ( response.getStatusCode ( ) == 202 ) {
            System.out.println ( "hard Delete Status code is " + response.getStatusCode ( ) );
            return true;
        } else {
            return false;
        }
    }

    public boolean createTenantUsingNullValuePostMethod ( ) {

        Response response = createTenantUsingPostMethod ( "{}" );
        try {
            // == >> Return true / false based on response status..
            if ( response.getStatusCode ( ) != 202 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Shouldnot post data with empty BOdy" );
        }
    }

    public boolean updateTenantUsingNullValuePutMethod ( ) {

        Response response = updateTenantUsingPutMethod ( "{}" );
        try {
            // == >> Return true / false based on response status..
            if ( response.getStatusCode ( ) != 202 ) {
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Shouldnot post data with empty BOdy" );
        }
    }

    public int getOneTenantStatusCodeWithInvalidTenantId (Tenant sampleTenant) {

        Response response = null;
        try {
            response = getOneTenantMethod ( sampleTenant.getId ( ) );
            return response.getStatusCode ( );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return response.getStatusCode ( );
        }
    }

    public int getTenantVerifyAllDataBodyGetStausCode ( ) {

        Response response = null;
        try {
            response = tenantAllDataGetMethodStatusCode ( );
            return response.getStatusCode ( );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            return response.getStatusCode ( );
        }

    }

}
 
 

