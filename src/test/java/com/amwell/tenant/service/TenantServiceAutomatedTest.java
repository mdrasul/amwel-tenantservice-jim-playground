package com.amwell.tenant.service;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amwell.tenant.utils.Tenant;
import com.amwell.tenant.utils.TestDataUtils;

import java.io.IOException;

import static org.testng.Assert.assertNotNull;


public class TenantServiceAutomatedTest {

    /**
     * Verify that the Tenant Management Post (Create a new Tenant ) endpoint is working as expected
     */
    @Test ( priority = 1 )
    public void crateTenantUsingPostMethod ( ) throws IOException {

        TenantService tenantService = new TenantService ();
        SoftAssert softAssert = new SoftAssert ( );

        // ==>> Create a sample Random Tenant Data
        Tenant sampleTenant = TestDataUtils.createOneSampleTenant ( );
        assertNotNull ( sampleTenant.getId ( ), "Failed to generate a sample tenant... !!" );

        // ==> Validate we can use Post to create the tenant in Database
        Assert.assertTrue ( tenantService.createTenantUsingPostMethodVarification ( TestDataUtils.createRandom ( ) ), "Failed!! to Create Tenant Using Post Method.." );

        /** ##### Negative Test flow ##### */

        // ==> Validate we can not use Post to create the tenant in Databse with same id

        Assert.assertTrue ( tenantService.createTenantUsingPostMethodVarification ( sampleTenant ), "Failed!! to Create Tenant Using Post Methos.." );
        Assert.assertEquals ( 409, tenantService.createTenantUsingPostMethodStuscode ( sampleTenant ), "....Failed!! Shouldn't Create Tanant with Same ID !!!" );

        // ==> Validate we can not use Post to create the tenant with Empty Body
        softAssert.assertTrue ( tenantService.createTenantUsingNullValuePostMethod ( ), "Failed!! Shouldn't Retun response 202(accepted) for Empty Body Post Call !!!" );

        //==> Validate Tananet post call id shouldn't be more then 10 character long
        sampleTenant.setId ( "Ten" + TestDataUtils.setIdFor ( 2 ) );
        softAssert.assertTrue ( tenantService.createTenantUsingPostMethodVarificationForNegativeTest ( sampleTenant ), "Failed!! Shouldn't Create New Tanant using more then 10 character long Id" );
        softAssert.assertEquals ( 400, tenantService.createTenantUsingPostMethodStuscode ( sampleTenant ), "Failed!! Shouldn't Retun response 202(accepted) for more then 10 character long id Post Call !!!" );

        // ==> Validate Tananet id shouldn't be less then 4 character
        sampleTenant.setId ( "T" + TestDataUtils.setIdFor ( 2 ) );
        softAssert.assertTrue ( tenantService.createTenantUsingPostMethodVarificationForNegativeTest ( sampleTenant ), "Failed!! Shouldn't Create New Tanant using less then 4 character long Id" );
        softAssert.assertEquals ( 400, tenantService.createTenantUsingPostMethodStuscode ( sampleTenant ), "Failed!! Shouldn't Return response 202(accepted) for less then 4 character long id Post Call !!!" );

        // ==> Validate we can not use Post to create the tenant with with special character
        sampleTenant.setName ( "TenantNameJ--" + TestDataUtils.setName ( 5 ) );
        softAssert.assertTrue ( tenantService.createTenantUsingPostMethodVarificationForNegativeTest ( sampleTenant ), "Failed!! Shouldn't Create New Tanant using Special Character in name for Post Call !!! .." );
        softAssert.assertTrue ( tenantService.createTenantUsingPostMethod ( sampleTenant, "Validation errors were found", "400" ), "Failed!! Shouldn't Retrun Sucess post message for post call!! .." );
        softAssert.assertEquals ( 400, tenantService.createTenantUsingPostMethodStuscode ( sampleTenant ), "Failed!! Shouldn't Retun response 202(accepted) for musing Special Character in name for Post Call !!! .." );
        softAssert.assertAll ( );
    }

    /**
     * Verify that the Tenant Management Put (Update Tenant Info) endpoint is working as expected
     */
    @Test ( priority = 5 )
    public void updateTenantInfoUsingPutMethod ( ) throws IOException {

        TenantService tenantService = new TenantService ( );
        SoftAssert softAssert = new SoftAssert ( );

        // ==>> Create a sample Random Tenant
        Tenant sampleTenant = TestDataUtils.createOneSampleTenant ( );
        assertNotNull ( sampleTenant.getId ( ), "Failed to generate a sample tenant... !!" );

        // ==>> Support Code to Create First

        Assert.assertTrue ( tenantService.createTenantUsingPostMethodVarification ( sampleTenant ), "Faild to Create Tenant Using Post Methos.." );

        // ==>> Validate Make A change On Tenant Name & Hit the PUT command to Update the databse
        sampleTenant.setName ( "TenantName" + " Changed" );
        Assert.assertTrue ( tenantService.updateTenantInfoUsingPutMethodVarification ( sampleTenant ), "Faild to Update Tenant Using Put Methos.." );


        /** ##### Nagetive Test flow ##### */


        // ==> Validate we can not use Post to create the tenant with Empty Body
        softAssert.assertTrue ( tenantService.updateTenantUsingNullValuePutMethod ( ), "Failed!! Shouldn't Retun response 202(accepted) for Empty Body Post Call !!!" );

        //==> Validate Tananet Put call id shouldn't be more then 10 character long
        sampleTenant.setId ( "Ten" + TestDataUtils.setIdFor ( 2 ) );
        softAssert.assertTrue ( tenantService.updateTenantInfoUsingPutMethodVarificationNegativeTest ( sampleTenant ), "Failed!! Shouldn't Create New Tanant using more then 10 character long Id" );
        softAssert.assertEquals ( 400, tenantService.updateTenantUsingPostMethodStuscode ( sampleTenant ), "Failed!! Shouldn't Retun response 202(accepted) for more then 10 character long id Post Call !!!" );


        // ==> Validate Tananet id shouldn't be less then 4 character
        sampleTenant.setId ( "T" + TestDataUtils.setIdFor ( 2 ) );
        softAssert.assertTrue ( tenantService.updateTenantInfoUsingPutMethodVarificationNegativeTest ( sampleTenant ), "Failed!! Shouldn't Create New Tanant using less then 4 character long Id" );
        softAssert.assertEquals ( 400, tenantService.updateTenantInfoUsingPutMethodVarification ( sampleTenant ), "Failed!! Shouldn't Retun response 202(accepted) for less then 4 character long id Post Call !!!" );

        // ==> Validate we can not use Post to create the tenant with with special character
        sampleTenant.setName ( "TenantNameJ--" + TestDataUtils.setName ( 5 ) );
        softAssert.assertTrue ( tenantService.updateTenantInfoUsingPutMethodVarificationNegativeTest ( sampleTenant ), "Failed!! Shouldn't Create New Tanant using Special Character in name for Post Call !!! .." );
        //Assert.assertTrue(tenantService.createTenantUsingPostMethod(sampleTenant, "Validation errors were found", "400"),"Failed!! Shouldn't Retrun Sucess post message for post call!! ..");
        softAssert.assertEquals ( 400, tenantService.updateTenantUsingPostMethodStuscode ( sampleTenant ), "Failed!! Shouldn't Retun response 202(accepted) for musing Special Character in name for Post Call !!! .." );
    }

    /**
     * Verify the Tenant Query (GET One Tenant) endpoint is working as expected
     */
    @Test ( priority = 2 )
    public void getOneTenantInfoUsingGetMethod ( ) throws IOException {

        // ==>> Create a sample Random Tenant
        Tenant sampleTenant = TestDataUtils.createOneSampleTenant ( );
        assertNotNull ( sampleTenant.getId ( ), "Failed to generate a sample tenant... !!" );

        // ==>> Support Code to Create First
        TenantService tenantService = new TenantService ( );
        Assert.assertTrue ( tenantService.createTenantUsingPostMethodVarification ( sampleTenant ), "Faild to Create Tenant Using Post Methos.." );

        //==>> Validate Get One tenant status is 200
        Assert.assertEquals ( 200, tenantService.getOneTenantStatusCodeUsingGetMethod ( sampleTenant ), "Faild to get StatusCode 200 for Get One Tenant.." );

        //==>> Validate Get One Tenant Response Body Is Ok
        Assert.assertTrue ( tenantService.validateGetOneTenantApiResponseBody ( sampleTenant ), "Faild to vairify correcnt Body in get call.." );

        /** ##### Nagetive Test flow ##### */

        //==>> Validate Get One tenant with Wrong Id should show 404 not Found
        sampleTenant.setId ( "654asd564as6d" );
        Assert.assertEquals ( 404, tenantService.getOneTenantStatusCodeWithInvalidTenantId ( sampleTenant ), "Faild to get StatusCode 200 for Get One Tenant.." );


    }

    /**
     * Verify the Tenant Query (Delete Tenant) endpoint is working as expected
     */
    @Test ( priority = 3 )
    public void deleteOneTenantInfoUsingGetMethod ( ) throws IOException {

        // ==>> Create a sample Random Tenant
        Tenant sampleTenantForSoftDel = TestDataUtils.createOneSampleTenant ( );
        Tenant sampleTenantForHardDel = TestDataUtils.createOneSampleTenant ( );


        /** ##### Validate soft Delate ##### */


        // ==>> Support Code to Create First
        TenantService tenantService = new TenantService ( );
        Assert.assertTrue ( tenantService.createTenantUsingPostMethodVarification ( sampleTenantForSoftDel ), "Faild to Create Tenant Using Post Methos.." );

        //==>> Validate we can use Delete to soft delete the tenant from database
        Assert.assertTrue ( tenantService.softDeleteTenantInfoUsingDeleteMethod ( sampleTenantForSoftDel ), "Faild to soft delete Tenant using Delete Methord.." );


        //==>> Validate Deleted Tananet is not in the Database
        Assert.assertEquals ( 404, tenantService.getOneTenantStatusCodeUsingGetMethod ( sampleTenantForSoftDel ), "Deleted Tenant Still in database.." );


        /** ##### Validate Hard Delate ##### */


        // ==>> Support Code to Create First
        Assert.assertTrue ( tenantService.createTenantUsingPostMethodVarification ( sampleTenantForHardDel ), "Faild to Create Tenant Using Post Methos.." );

        //==>> Validate we can use Delete to Hard delete the tenant from Database
        Assert.assertTrue ( tenantService.hardDeleteTenantInfoUsingDeleteMethod ( sampleTenantForHardDel ), "Faild to hard delete Tenant using Delete Methord.." );

        //==>> Validate Deleted Tananet is not in the Database
        Assert.assertEquals ( 404, tenantService.getOneTenantStatusCodeUsingGetMethod ( sampleTenantForHardDel ), "Deleted Tenant Still in database.." );

    }

    /**
     * Verify the Tenant Query (Get All Tenant) endpoint is working as expected
     */
    @Test ( priority = 4 )
    public void getAllTenantInfoUsingGetMethod ( ) throws IOException {


        TenantService tenantService = new TenantService ( );
        ;

        //==>> Validate Get All tenant status is 200
        Assert.assertEquals ( 200, tenantService.getTenantVerifyAllDataBodyGetStausCode ( ), "Faild to get StatusCode 200 for Get all Tenant.." );

        //==>> Validate Get All Tenant Response Body Contain more then one User Data
        Assert.assertTrue ( tenantService.getTenantGetAllContainCount ( ), "Faild to vairify more then one data is contain.." );


        //==>> Validate Get All Tenant Response Body Atlest Match with 	one vaild User Data
        //Assert.assertTrue(tenantService.getTenantGetAllContainData("", ""), "Faild to vairify Get All Tenant Response Body match with one vaild User Data..");

        /** ##### Nagetive Test flow ##### */

    }
}
