package com.example.humors.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.humors.Humors;

import java.util.prefs.Preferences;

public class SharedPrefs {

    private Context context;
    private final SharedPreferences sharedPreferences;

    public SharedPrefs(@NonNull Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUserName() {
        return sharedPreferences.getString(Constants.USER_NAME, "");
    }

    public void setUserName(String userName) {
        sharedPreferences.edit().putString(Constants.USER_NAME, userName).apply();
    }

    public int getUserStatus() {
        return sharedPreferences.getInt(Constants.USER_STATUS, -1);
    }

    public void setUserStatus(int userStatus) {
        sharedPreferences.edit().putInt(Constants.USER_STATUS, userStatus).apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(Constants.USER_EMAIL, "");
    }

    public void setUserEmail(String userEmail) {
        sharedPreferences.edit().putString(Constants.USER_EMAIL, userEmail).apply();
    }

    public String getUserGender() {
        return sharedPreferences.getString(Constants.USER_GENDER, "");
    }

    public void setUserGender(String userGender) {
        sharedPreferences.edit().putString(Constants.USER_GENDER, userGender).apply();
    }

    public String getUserDob() {
        return sharedPreferences.getString(Constants.USER_DOB, "");
    }

    public void setUserDob(String userDob) {
        sharedPreferences.edit().putString(Constants.USER_DOB, userDob).apply();
    }

    public String getUerSleepDuration() {
        return sharedPreferences.getString(Constants.USER_SLEEP_DURATION, "");
    }

    public void setUserSleepDuration(String userSleepDuration) {
        sharedPreferences.edit().putString(Constants.USER_SLEEP_DURATION, userSleepDuration).apply();
    }

    public String getUserSmokingStatus() {
        return sharedPreferences.getString(Constants.USER_SMOKING_STATUS, "");
    }

    public void setUserSmokingStatus(String userSmokingStatus) {
        sharedPreferences.edit().putString(Constants.USER_SMOKING_STATUS, userSmokingStatus).apply();
    }

    public String getUserAlcoholStatus() {
        return sharedPreferences.getString(Constants.USER_ALCOHOL_STATUS, "");
    }

    public void setUserAlcoholStatus(String userAlcoholStatus) {
        sharedPreferences.edit().putString(Constants.USER_ALCOHOL_STATUS, userAlcoholStatus).apply();
    }

    public String getUserExerciseStatus() {
        return sharedPreferences.getString(Constants.USER_EXERCISE_STATUS, "");
    }

    public void setUserExerciseStatus(String userExerciseStatus) {
        sharedPreferences.edit().putString(Constants.USER_EXERCISE_STATUS, userExerciseStatus).apply();
    }

    public String getUserVegStatus() {
        return sharedPreferences.getString(Constants.USER_VEG_STATUS, "");
    }

    public void setUserVegStatus(String userVegStatus) {
        sharedPreferences.edit().putString(Constants.USER_VEG_STATUS, userVegStatus).apply();
    }

    public String getUserJunkFoodStatus() {
        return sharedPreferences.getString(Constants.USER_JUNK_FOOD_STATUS, "");
    }

    public void setUserJunkFoodStatus(String userJunkFoodStatus) {
        sharedPreferences.edit().putString(Constants.USER_JUNK_FOOD_STATUS, userJunkFoodStatus).apply();
    }

    public String getUserWaterStatus() {
        return sharedPreferences.getString(Constants.USER_WATER_STATUS, "");
    }

    public void setUserWaterStatus(String userWaterStatus) {
        sharedPreferences.edit().putString(Constants.USER_WATER_STATUS, userWaterStatus).apply();
    }

    public String getUserDisease() {
        return sharedPreferences.getString(Constants.USER_DISEASE, "");
    }

    public void setUserDisease(String userDisease) {
        sharedPreferences.edit().putString(Constants.USER_DISEASE, userDisease).apply();
    }

    public String getUserOtherDisease() {
        return sharedPreferences.getString(Constants.USER_OTHER_DISEASE, "");
    }

    public void setUserOtherDisease(String userOtherDisease) {
        sharedPreferences.edit().putString(Constants.USER_OTHER_DISEASE, userOtherDisease).apply();
    }

    public String getUserDiseaseLevel() {
        return sharedPreferences.getString(Constants.USER_DISEASE_LEVEL, "");
    }

    public void setUserDiseaseLevel(String userDiseaseLevel) {
        sharedPreferences.edit().putString(Constants.USER_DISEASE_LEVEL, userDiseaseLevel).apply();
    }

    public int getUserId() {
        return sharedPreferences.getInt(Constants.USER_ID, -1);
    }

    public void setUserId(int userId) {
        sharedPreferences.edit().putInt(Constants.USER_ID, userId).apply();
    }

    public int getUserAge() {
        return sharedPreferences.getInt(Constants.USER_AGE, -1);
    }

    public void setUserAge(int userAge) {
        sharedPreferences.edit().putInt(Constants.USER_AGE, userAge).apply();
    }

    public String getUserHeight() {
        return sharedPreferences.getString(Constants.USER_HEIGHT, "");
    }

    public void setUserHeight(String userHeight) {
        sharedPreferences.edit().putString(Constants.USER_HEIGHT, userHeight).apply();
    }

    public String getUserWeight() {
        return sharedPreferences.getString(Constants.USER_WEIGHT, "");
    }

    public void setUserWeight(String userWeight) {
        sharedPreferences.edit().putString(Constants.USER_WEIGHT, userWeight).apply();
    }

}
