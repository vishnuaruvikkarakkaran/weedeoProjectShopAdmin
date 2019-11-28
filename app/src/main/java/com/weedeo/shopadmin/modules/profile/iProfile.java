package com.weedeo.shopadmin.modules.profile;

import android.content.Context;
import android.net.Uri;

import com.weedeo.shopadmin.base.MvpBase;
import com.weedeo.shopadmin.model.response.Profile_info_responds;

import java.io.File;

public interface iProfile extends MvpBase {


    interface ActivityView extends MvpBase{

        void phoneNumberCheck(String number_for_otp);
        void signOutSuccess();

        void phonenumberValid(String number_for_otp);

        void successToShopTiming(String message);

        void numberUpdated(String message, String primary_mobile);

        void onSetUserImage(String path);

        void primaryImageUploadSuccesfully(String string, String toString);

        void pofileData(String string, String toString, int typeOfDoc);

        void profileDataSuccess(String fileName);

        void primaryUploaded(String primaryImage, String shopId);

        void setUpgradeadapter(ProfileAdapterClass profileAdapter);

        void ProfileData(Profile_info_responds profile_info_responds, Context context);

        void deleteProfileImage(String fileName, Context mContext);

        void profileSuccefullyDeleted(int count, Context context);

        void closePager();

        void imagePickUpMethodFromAdapter(Context context,int primaryPickUp, int limit);
    }

    interface PresenterView{

        void changenumberFromProfile(String otp);

        void logOut();

        void profileChangeNumber(String number_for_otp);

        void sendVerificationCode(String number_for_otp);

        void memberCheck(String mobile, String uid);

        void onCropImage(Uri imageUri, ProfileActivity profileActivity);

        String onCreateProfileFileName();

        void uploadFileToS3(File newFile, String type, int profileOrNot, String fileName, int i);

        void uploadPrimaryImage(String imageName);

        void profileUploadingImage(String fileName, int typeOfDoc);

        void ProfileInfoget(String loadPreferences,Context context);

        void deleteProfileData(String fileName, String token, String shopId, Context context);

    }


}
