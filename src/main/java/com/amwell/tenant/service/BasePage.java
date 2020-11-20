package com.amwell.tenant.service;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amwell.tenant.utils.Tenant;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BasePage {

    public static Properties prop;

    public BasePage ( ) throws IOException {

//	prop = new Properties();
//	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/BasicTenantAPIInputData/TenantApiAsset.properties");
//	prop.load(ip);

    }
    /**
     * ##### Static variable dedecaration #####
     */

    private final Logger log = LoggerFactory.getLogger ( MethodHandles.lookup ( ).lookupClass ( ) );
    public static final String BASE_URL = "http://jeremy-bigrio-02-int.dev.americanwell.com:8094";
    public static final String jsonFileLocation = System.getProperty ( "user.dir" ) + "\\src\\test\\resources\\BasicTenantAPIInputData\\tenant.json";
    ObjectMapper mapper = new ObjectMapper ( );


    /**
     * ##### Reusable Service Methds #####
     */

    public Response createTenantUsingPostMethod (Tenant sampleTenant) {
        Response response = null;
        try {
            RestAssured.baseURI = BASE_URL;
            String accessToken = getAccessTokenForAdmin ( );

            RequestSpecification requestSpec = RestAssured.given ( );
            requestSpec.auth ( ).preemptive ( ).oauth2 ( accessToken );
            requestSpec.contentType ( "application/json" );
            requestSpec.body ( mapper.writeValueAsString ( sampleTenant ) );

            response = requestSpec.post ( "api/v1/tenant" );
            System.out.println ( response.getBody ( ).asString ( ) );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return response;
    }

    public Response createTenantUsingPostMethod (String body) {
        Response response = null;
        try {
            RestAssured.baseURI = BASE_URL;
            String accessToken = getAccessTokenForAdmin ( );

            RequestSpecification requestSpec = RestAssured.given ( );
            requestSpec.auth ( ).preemptive ( ).oauth2 ( accessToken );
            requestSpec.contentType ( "application/json" );
            requestSpec.body ( body );

            response = requestSpec.post ( "api/v1/tenant" );
            System.out.println ( response.getBody ( ).asString ( ) );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return response;
    }

    public boolean createTenantUsingPostMethod (Tenant sampleTenant, String title, String status) {
        Response response = createTenantUsingPostMethod ( sampleTenant );
        String responseBody = response.getBody ( ).asString ( );
        JsonPath js = new JsonPath ( responseBody );

        String varifyTitle = js.get ( "title" );
        String varifyStatus = js.getString ( "status" );
        try {
            if (varifyTitle.contentEquals ( title ) && varifyStatus.contentEquals ( varifyStatus )) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace ( );
            throw new RuntimeException ( "Failed to post Tenant" );
        }
    }

    public Response updateTenantUsingPutMethod (Tenant sampleTenant) {
        Response response = null;
        try {
            RestAssured.baseURI = BASE_URL;
            String accessToken = getAccessTokenForAdmin ( );

            RequestSpecification requestSpec = RestAssured.given ( );
            requestSpec.auth ( ).preemptive ( ).oauth2 ( accessToken );
            requestSpec.contentType ( "application/json" );
            requestSpec.body ( mapper.writeValueAsString ( sampleTenant ) );

            response = requestSpec.put ( "api/v1/tenant/" + sampleTenant.getId ( ) );
            System.out.println ( response.getBody ( ).asString ( ) );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return response;
    }

    public Response updateTenantUsingPutMethod (String body) {
        Response response = null;
        try {
            RestAssured.baseURI = BASE_URL;
            String accessToken = getAccessTokenForAdmin ( );

            RequestSpecification requestSpec = RestAssured.given ( );
            requestSpec.auth ( ).preemptive ( ).oauth2 ( accessToken );
            requestSpec.contentType ( "application/json" );
            requestSpec.body ( body );

            response = requestSpec.put ( "api/v1/tenant" );
            System.out.println ( response.getBody ( ).asString ( ) );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return response;
    }

    public Response getOneTenantMethod (String endPram) {
        Response response = null;
        try {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification httpRequest = RestAssured.given ( );
            response = httpRequest.get ( "api/v1/tenant/" + endPram );

        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return response;
    }

    public ArrayList<String> tenantAllDataGetMethod ( ) {

        RestAssured.baseURI = BASE_URL;
        ArrayList<String> testArrayList = null;
        try {
            RequestSpecification httpRequest = RestAssured.given ( );
            httpRequest.header ( "Content-Type", "application/json" );
            Response response = httpRequest.get ( "/api/v1/tenant" );
            System.out.println ( response.statusCode ( ) );
            @SuppressWarnings ( "rawtypes" )
            ResponseBody body = response.getBody ( );
            String Jbody = body.asString ( );
            JSONArray jsonArray = new JSONArray ( Jbody );
            testArrayList = toStringArrayList ( jsonArray );
        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return testArrayList;


    }

    public Response tenantAllDataGetMethodStatusCode ( ) {
        Response response = null;
        RestAssured.baseURI = BASE_URL;

        try {
            RequestSpecification httpRequest = RestAssured.given ( );
            httpRequest.header ( "Content-Type", "application/json" );
            response = httpRequest.get ( "/api/v1/tenant" );

        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return response;
    }


    /**
     * ##### Reusable Page helper Methored #####
     */

    public String readJsonFile (String path) throws IOException {

        return new String ( Files.readAllBytes ( Paths.get ( path ) ) );

    }

    @SuppressWarnings ( {"unused", "deprecation"} )
    public String getAccessTokenForAdmin ( ) {
        String response =
                given ( )
                        .parameters ( "username", "soauser",
                                "password", "admin",
                                "grant_type", "password",
                                "scope", "service/tenant/tenant.admin",
                                "client_id", "soa-tenant-service-client",
                                "client_secret", "e4f108fe-7199-4c85-961c-932024fb8994" )
                        .auth ( )
                        .preemptive ( )
                        .basic ( "soa-tenant-service-client", "e4f108fe-7199-4c85-961c-932024fb8994" )
                        .when ( )
                        .post ( "https://keycloak.dev.americanwell.com/auth/realms/dev-realm/protocol/openid-connect/token" )
                        .asString ( );

        JsonPath jsonPath = new JsonPath ( response );
        String accessToken = jsonPath.getString ( "access_token" );
        return accessToken;
    }

    public static ArrayList<String> toStringArrayList (JSONArray jsonArray) {

        ArrayList<String> stringArray = new ArrayList<String> ( );
        int arrayIndex;
        JSONObject jsonArrayItem;
        String tanentID;
        String tanentName;

        for (arrayIndex = 0; arrayIndex < jsonArray.length ( ); arrayIndex++) {

            try {
                jsonArrayItem = jsonArray.getJSONObject ( arrayIndex );

                tanentID = jsonArrayItem.getString ( "id" );
                tanentName = jsonArrayItem.getString ( "name" );

                stringArray.add ( "tanentID is:- " + tanentID );
                stringArray.add ( "tanentName is :- " + tanentName );
            } catch (JSONException e) {
                e.printStackTrace ( );
            }
        }

        return stringArray;
    }
//	@SuppressWarnings("deprecation")
//	public String getAccessTokenForClient() {    
//			String response =
//		            given()
//		                .parameters("username", "soauser",
//		                			"password", "admin", 
//		                           "grant_type", "client credentials",
//		                           "scope", "service/tenant/tenant.admin", 
//		                           "client_id", "soa-tenant-service-client",
//		                           "client_secret", "e4f108fe-7199-4c85-961c-932024fb8994")
//		                .auth()
//		                .preemptive()
//		                .basic("soa-tenant-service-client","e4f108fe-7199-4c85-961c-932024fb8994")
//		                .when()
//		                .post("https://keycloak.dev.americanwell.com/auth/realms/dev-realm/protocol/openid-connect/token")
//		                .asString();
//	
//		  JsonPath jsonPath = new JsonPath(response);
//		  String  accessToken = jsonPath.getString("access_token");
//		  System.out.println(accessToken);
//		  return accessToken;
//		}


}
