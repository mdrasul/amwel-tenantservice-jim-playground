/**
 * Copyright 2019 American Well Systems
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.amwell.tenant.service.entity;

import com.amwell.tenant.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stubbed data for enrolling consumer in Caretalks enrollment endpoint. This is an example only
 * and is NOT used in this project. This example serves to showcase how we create entity objects/payloads
 * for our resource classes to consume. You would use this for a POST or PUT method.
 *
 * This entity contains all the properties that the server would accept for the enrollment endpoint in
 * Caretalks. Initialize properties you want as the default object. If you would like additional fields,
 * call the setter methods after instantiating this object. For a negative testing example, you may set
 * a property to null to remove that property from the object/payload. This is useful for testing required
 * fields/properties. With that said, do not set a property to null unless you intend to exclude the
 * property from the object upon instantiation.
 *
 * Make sure to add logging to every method to assist us in debugging failed tests or assist in development in general.
 *
 * @author Chan.Suom on 2/8/19
 */
@Slf4j
public class ConsumerEnrollmentEntity {

    private String firstName;
    private String middleInitial;
    private String lastName;
    private String dob;
    private String gender;
    private String address1;
    private String address2;
    private String city;
    private String zipCode;
    private String phoneNumber; //10 digit, e.g. 6175551234
    private String email;
    private String password;
    private String preferredLocale; //e.g. "en_US"
    private String addressState; //e.g. "MA"
    private String addressCountry; //e.g. "US"
    private String governmentId; //e.g. 1234
    private String currentLocationState; //e.g. "MA"
    private String currentLocationCountry; //e.g. "US"
    private String sourceId;
    private String sdkUserId;
    private String sdkUserAuthKey;
    private String sdkApiKey;
    private boolean acceptedMemberDisclaimer;
    private boolean sendWelcomeEmail;
    private String timeZone;
    private String mrnId;
    private String deviceModel;
    private String deviceOS;
    private String connectionType;
    private List<String> brandings;
    //extra variables for patient information
    private String healthPlan;
    private String subscriberId;
    private String suffix;

    public ConsumerEnrollmentEntity() throws IOException {

        long timestamp = System.currentTimeMillis() % 100000;
        String randomString = RandomStringUtils.randomAlphabetic(6).toLowerCase().concat(String.valueOf(timestamp));

        this.firstName = "Daenerys";
        this.middleInitial = "S";
        this.lastName = "Targaryen" + randomString;
        this.gender = "FEMALE";
        this.dob = Helper.createDateStringPlusYears(-40);
        this.address1 = "House Targaryen";
        this.address2 = "Dragon Tower";
        this.city = "Dragonstone";
        this.zipCode = "02109";
        this.phoneNumber = "6175551234";
        this.email = this.lastName + "@dragon.glass";
        this.password = "Password1";
        this.preferredLocale = "en_US";
        this.addressState = "MA";
        this.addressCountry = "US";
        this.governmentId = "1234";
        this.currentLocationState = "MA";
        this.currentLocationCountry = "US";
        this.sourceId = randomString;
        this.sdkUserId = randomString;
        this.sdkApiKey = randomString;
        this.sdkUserAuthKey = randomString;
        this.acceptedMemberDisclaimer = true;
        this.sendWelcomeEmail = false;
        this.timeZone = "America/New_York";
        this.mrnId = randomString;
        this.deviceModel = "Dragonglass REST Automation";
        this.deviceOS = "Dragonglass OS";
        this.connectionType = "Dragonglass Wifi";
        this.brandings = new ArrayList<>(Arrays.asList("Breaker of Chains", "Mother of Dragons", "First of Her Name"));

    }

    /**
     * Get consumer's first name
     *
     * @return String
     */
    public String getFirstName() {

        log.debug("Consumer first name: \"{}\"", this.firstName);
        return this.firstName;

    }

    /**
     * Set consumer's first name
     *
     * @param firstName String
     */
    public void setFirstName(String firstName) {

        log.debug("Setting consumer first name to \"{}\"...", firstName);
        this.firstName = firstName;

    }

    /**
     * Get consumer's middle initial
     *
     * @return String
     */
    public String getMiddleInitial() {

        log.debug("Consumer middle initial: \"{}\"", this.middleInitial);
        return this.middleInitial;

    }

    /**
     * Set consumer's middle initial; can be more than one character as server will handle this
     *
     * @param middleInitial String
     */
    public void setMiddleInitial(String middleInitial) {

        log.debug("Setting consumer middle initial to \"{}\"...", middleInitial);
        this.middleInitial = middleInitial;

    }

    /**
     * Get consumer's last name
     *
     * @return String
     */
    public String getLastName() {

        log.debug("Consumer last name: \"{}\"", this.lastName);
        return this.lastName;

    }

    /**
     * Set consumer's last name
     *
     * @param lastName String
     */
    public void setLastName(String lastName) {

        log.debug("Setting consumer last name to \"{}\"...", lastName);
        this.lastName = lastName;

    }

    /**
     * Get consumer's gender
     *
     * @return String
     */
    public String getGender() {

        log.debug("Consumer gender: \"{}\"", this.gender);
        return this.gender;

    }

    /**
     * Set consumer's gender
     *
     * @param gender String MALE or FEMALE
     */
    public void setGender(String gender) {

        log.debug("Setting consumer gender to \"{}\"...", gender.toUpperCase());
        this.gender = gender.toUpperCase();

    }

    /**
     * Get consumer's date of birth
     *
     * @return String
     */
    public String getDob() {

        log.debug("Consumer date of birth: \"{}\"", this.dob);
        return this.dob;

    }

    /**
     * Set consumer's date of birth
     *
     * @param dob String yyyy-MM-dd format
     */
    public void setDob(String dob) {

        log.debug("Setting consumer date of birth to \"{}\"...", dob);
        this.dob = dob;

    }

    /**
     * Get consumer's address1
     *
     * @return String
     */
    public String getAddress1() {

        log.debug("Consumer address1: \"{}\"", this.address1);
        return this.address1;

    }

    /**
     * Set consumer's address1
     *
     * @param address1 String
     */
    public void setAddress1(String address1) {

        log.debug("Setting consumer address1 to \"{}\"...", address1);
        this.address1 = address1;

    }

    /**
     * Get consumer's address2
     *
     * @return String
     */
    public String getAddress2() {

        log.debug("Consumer address2: \"{}\"", this.address2);
        return this.address2;

    }

    /**
     * Set consumer's address2
     *
     * @param address2 String
     */
    public void setAddress2(String address2) {

        log.debug("Setting consumer address2 to \"{}\"...", address2);
        this.address2 = address2;

    }

    /**
     * Get consumer's city
     *
     * @return String
     */
    public String getCity() {

        log.debug("Consumer city: \"{}\"", this.city);
        return this.city;

    }

    /**
     * Set consumer's city
     *
     * @param city String
     */
    public void setCity(String city) {

        log.debug("Setting consumer city to \"{}\"...", city);
        this.city = city;

    }

    /**
     * Get consumer's zip code
     *
     * @return String
     */
    public String getZipCode() {

        log.debug("Consumer zip code: \"{}\"", this.zipCode);
        return this.zipCode;

    }

    /**
     * Set consumer's zip code
     *
     * @param zipCode String
     */
    public void setZipCode(String zipCode) {

        log.debug("Setting consumer zip code to \"{}\"...", zipCode);
        this.zipCode = zipCode;

    }

    /**
     * Get consumer's phone number
     *
     * @return String
     */
    public String getPhoneNumber() {

        log.debug("Consumer phone number: \"{}\"", this.phoneNumber);
        return this.phoneNumber;

    }

    /**
     * Set consumer's phone number
     *
     * @param phoneNumber String
     */
    public void setPhoneNumber(String phoneNumber) {

        log.debug("Setting consumer phone number to \"{}\"...", phoneNumber);
        this.phoneNumber = phoneNumber;

    }

    /**
     * Get consumer's email
     *
     * @return String
     */
    public String getEmail() {

        log.debug("Consumer email: \"{}\"", this.email);
        return this.email;

    }

    /**
     * Set consumer's email
     *
     * @param email String
     */
    public void setEmail(String email) {

        log.debug("Setting consumer email to \"{}\"...", email);
        this.email = email;

    }

    /**
     * Get consumer's password
     *
     * @return String
     */
    public String getPassword() {

        log.debug("Consumer password: \"{}\"", this.password);
        return this.password;

    }

    /**
     * Set consumer's password
     *
     * @param password String
     */
    public void setPassword(String password) {

        log.debug("Setting consumer password to \"{}\"...", password);
        this.password = password;

    }

    /**
     * Get consumer's preferred locale
     *
     * @return String
     */
    public String getPreferredLocale() {

        log.debug("Consumer preferred locale: \"{}\"", this.preferredLocale);
        return this.preferredLocale;

    }

    /**
     * Set consumer's preferred locale
     *
     * @param preferredLocale String
     */
    public void setPreferredLocale(String preferredLocale) {

        log.debug("Setting consumer preferred locale to \"{}\"...", preferredLocale);
        this.preferredLocale = preferredLocale;

    }

    /**
     * Get consumer's address state
     *
     * @return String
     */
    public String getAddressState() {

        log.debug("Consumer address state: \"{}\"", this.addressState);
        return this.addressState;

    }

    /**
     * Set consumer's address state
     *
     * @param addressState String
     */
    public void setAddressState(String addressState) {

        log.debug("Setting consumer address state to \"{}\"...", addressState);
        this.addressState = addressState;

    }

    /**
     * Get consumer's address country
     *
     * @return String
     */
    public String getAddressCountry() {

        log.debug("Consumer address country: \"{}\"", this.addressCountry);
        return this.addressCountry;

    }

    /**
     * Set consumer's address country
     *
     * @param addressCountry String
     */
    public void setAddressCountry(String addressCountry) {

        log.debug("Setting consumer address country to \"{}\"...", addressCountry);
        this.addressCountry = addressCountry;

    }

    /**
     * Get consumer's government ID
     *
     * @return String
     */
    public String getGovernmentId() {

        log.debug("Consumer Government ID: \"{}\"", this.governmentId);
        return this.governmentId;

    }

    /**
     * Set consumer's government ID
     *
     * @param governmentId String
     */
    public void setGovernmentId(String governmentId) {

        log.debug("Setting consumer government ID to \"{}\"...", governmentId);
        this.governmentId = governmentId;

    }

    /**
     * Get consumer's current location state
     *
     * @return String
     */
    public String getCurrentLocationState() {

        log.debug("Consumer current location state: \"{}\"", this.currentLocationState);
        return this.currentLocationState;

    }

    /**
     * Set consumer's current location state
     *
     * @param currentLocationState String 2-letter code
     */
    public void setCurrentLocationState(String currentLocationState) {

        log.debug("Setting consumer current location state to \"{}\"...", currentLocationState);
        this.currentLocationState = currentLocationState;

    }

    /**
     * Get consumer's current location country
     *
     * @return String
     */
    public String getCurrentLocationCountry() {

        log.debug("Consumer current location country: \"{}\"", this.currentLocationCountry);
        return this.currentLocationCountry;

    }

    /**
     * Set consumer's current location country
     *
     * @param currentLocationCountry String 2-letter code
     */
    public void setCurrentLocationCountry(String currentLocationCountry) {

        log.debug("Setting consumer current location country to \"{}\"...", currentLocationCountry);
        this.currentLocationCountry = currentLocationCountry;

    }

    /**
     * Get consumer's sourceId
     *
     * @return String
     */
    public String getSourceId() {

        log.debug("Consumer sourceId: \"{}\"", this.sourceId);
        return this.sourceId;

    }

    /**
     * Set consumer's sourceId
     *
     * @param sourceId String
     */
    public void setSourceId(String sourceId) {

        log.debug("Setting consumer sourceId to \"{}\"...", sourceId);
        this.sourceId = sourceId;

    }

    /**
     * Get consumer's sdkUserId
     *
     * @return String
     */
    public String getSdkUserId() {

        log.debug("Consumer sdkUserId: \"{}\"", this.sdkUserId);
        return this.sdkUserId;

    }

    /**
     * Set consumer's sdkUserId
     *
     * @param sdkUserId String
     */
    public void setSdkUserId(String sdkUserId) {

        log.debug("Setting consumer sdkUserId to \"{}\"...", sdkUserId);
        this.sdkUserId = sdkUserId;

    }

    /**
     * Get consumer's sdkUserAuthKey
     *
     * @return String
     */
    public String getSdkUserAuthKey() {

        log.debug("Consumer sdkUserAuthKey: \"{}\"", this.sdkUserAuthKey);
        return this.sdkUserAuthKey;

    }

    /**
     * Set consumer's sdkUserAuthKey
     *
     * @param sdkUserAuthKey String
     */
    public void setSdkUserAuthKey(String sdkUserAuthKey) {

        log.debug("Setting consumer sdkUserAuthKey to \"{}\"...", sdkUserAuthKey);
        this.sdkUserAuthKey = sdkUserAuthKey;

    }

    /**
     * Get consumer's sdkApiKey
     *
     * @return String
     */
    public String getSdkApiKey() {

        log.debug("Consumer sdkApiKey: \"{}\"", this.sdkApiKey);
        return this.sdkApiKey;

    }

    /**
     * Set consumer's sdkApiKey
     *
     * @param sdkApiKey String
     */
    public void setSdkApiKey(String sdkApiKey) {

        log.debug("Setting consumer sdkApiKey to \"{}\"...", sdkApiKey);
        this.sdkApiKey = sdkApiKey;

    }

    /**
     * Get consumer's acceptedMemberDisclaimer flag
     *
     * @return boolean
     */
    public boolean isAcceptedMemberDisclaimer() {

        log.debug("Consumer acceptedMemberDisclaimer flag: \"{}\"", this.acceptedMemberDisclaimer);
        return this.acceptedMemberDisclaimer;

    }

    /**
     * Set consumer's acceptedMemberDisclaimer flag
     *
     * @param acceptedMemberDisclaimer boolean
     */
    public void setAcceptedMemberDisclaimer(boolean acceptedMemberDisclaimer) {

        log.debug("Setting consumer acceptedMemberDisclaimer flag to \"{}\"...", acceptedMemberDisclaimer);
        this.acceptedMemberDisclaimer = acceptedMemberDisclaimer;

    }

    /**
     * Get consumer's sendWelcomeEmail flag
     *
     * @return boolean
     */
    public boolean isSendWelcomeEmail() {

        log.debug("Consumer sendWelcomeEmail flag: \"{}\"", this.sendWelcomeEmail);
        return this.sendWelcomeEmail;

    }

    /**
     * Set consumer's sendWelcomeEmail flag
     *
     * @param sendWelcomeEmail boolean
     */
    public void setSendWelcomeEmail(boolean sendWelcomeEmail) {

        log.debug("Setting consumer sendWelcomeEmail flag to \"{}\"...", sendWelcomeEmail);
        this.sendWelcomeEmail = sendWelcomeEmail;

    }

    /**
     * Get consumer's time zone
     *
     * @return String
     */
    public String getTimeZone() {

        log.debug("Consumer time zone: \"{}\"", this.timeZone);
        return this.timeZone;

    }

    /**
     * Set consumer's time zone
     *
     * @param timeZone String
     */
    public void setTimeZone(String timeZone) {

        log.debug("Setting consumer time zone to \"{}\"...", timeZone);
        this.timeZone = timeZone;

    }

    /**
     * Get consumer's mrnId
     *
     * @return String
     */
    public String getMrnId() {

        log.debug("Consumer mrnId: \"{}\"", this.mrnId);
        return this.mrnId;

    }

    /**
     * Set consumer's mrnId
     *
     * @param mrnId String
     */
    public void setMrnId(String mrnId) {

        log.debug("Setting consumer mrnId to \"{}\"...", mrnId);
        this.mrnId = mrnId;

    }

    /**
     * Get consumer's device model
     *
     * @return String
     */
    public String getDeviceModel() {

        log.debug("Consumer device model: \"{}\"", this.deviceModel);
        return this.deviceModel;

    }

    /**
     * Set consumer's device model
     *
     * @param deviceModel String
     */
    public void setDeviceModel(String deviceModel) {

        log.debug("Setting consumer device model to \"{}\"...", deviceModel);
        this.deviceModel = deviceModel;

    }

    /**
     * Get consumer's device OS
     *
     * @return String
     */
    public String getDeviceOS() {

        log.debug("Consumer device OS: \"{}\"", this.deviceOS);
        return this.deviceOS;

    }

    /**
     * Set consumer's device OS
     *
     * @param deviceOS String
     */
    public void setDeviceOS(String deviceOS) {

        log.debug("Setting consumer device OS to \"{}\"...", deviceOS);
        this.deviceOS = deviceOS;

    }

    /**
     * Get consumer's connectionType
     *
     * @return String
     */
    public String getConnectionType() {

        log.debug("Consumer connectionType: \"{}\"", this.connectionType);
        return this.connectionType;

    }

    /**
     * Set consumer's connectionType
     *
     * @param connectionType String
     */
    public void setConnectionType(String connectionType) {

        log.debug("Setting consumer connectionType to \"{}\"...", connectionType);
        this.connectionType = connectionType;

    }

    /**
     * Get consumer's brandings list
     *
     * @return List
     */
    public List<String> getBrandings() {

        log.debug("Consumer brandings: \"{}\"", this.brandings);
        return this.brandings;

    }

    /**
     * Set consumer's brandings list
     *
     * @param brandingsList List
     */
    public void setBrandings(List<String> brandingsList) {

        log.debug("Setting consumer brandings to \"{}\"...", brandingsList);
        this.brandings = brandingsList;

    }

    /**
     * Get consumer's health plan
     *
     * @return String
     */
    public String getHealthPlan() {

        log.debug("Consumer health plan: \"{}\"", this.healthPlan);
        return this.healthPlan;

    }

    /**
     * Set consumer's health plan
     *
     * @param healthPlan String
     */
    public void setHealthPlan(String healthPlan) {

        log.debug("Setting consumer health plan to \"{}\"...", healthPlan);
        this.healthPlan = healthPlan;

    }

    /**
     * Get consumer's subscriber ID
     *
     * @return String
     */
    public String getSubscriberId() {

        log.debug("Consumer subscriber ID: \"{}\"", this.subscriberId);
        return this.subscriberId;

    }

    /**
     * Set consumer's subscriber ID
     *
     * @param subscriberId String
     */
    public void setSubscriberId(String subscriberId) {

        log.debug("Setting consumer subscriber ID to \"{}\"...", subscriberId);
        this.subscriberId = subscriberId;

    }

    /**
     * Get consumer's suffix
     *
     * @return String
     */
    public String getSuffix() {

        log.debug("Consumer suffix: \"{}\"", this.suffix);
        return this.suffix;

    }

    /**
     * Set consumer's suffix
     *
     * @param suffix String
     */
    public void setSuffix(String suffix) {

        log.debug("Setting consumer suffix to \"{}\"...", suffix);
        this.suffix = suffix;

    }

}
