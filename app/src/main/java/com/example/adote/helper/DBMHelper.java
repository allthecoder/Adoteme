package com.example.adote.helper;

import com.example.adote.models.user.InstitutionalUserInfo;
import com.example.adote.models.user.PrivateUserInfo;
import com.example.adote.models.user.User;
import com.example.adote.models.user.UserInfo;
import com.example.adote.models.user.UserType;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Auxiliar Database Manager class that deals with Firebase
 * and singletons all together.
 *
 */
public class DBMHelper {
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final UserDBM userDBM = UserDBM.getInstance();
    private static final AnimalDBM animalDBM = AnimalDBM.getInstance();

   public static User getCurrentUser() {
        if (mAuth.getCurrentUser() == null) {
            return null;
        }

        String uid = mAuth.getCurrentUser().getUid();
        return userDBM.getUser(uid);
    }

    private static PrivateUserInfo getPrivateUserInfo() {
        User user = getCurrentUser();

        if (user == null || !(user.getInfo() instanceof PrivateUserInfo)) {
            return null;
        }

        return (PrivateUserInfo) user.getInfo();
    }

    private static UserInfo getCurUserInfo() {
        User user = getCurrentUser();

        if (user == null) {
            return null;
        }

        return user.getInfo();
    }

    private static InstitutionalUserInfo getInstitutionalUserInfo() {
        User user = getCurrentUser();

        if (user == null || !(user.getInfo() instanceof InstitutionalUserInfo)) {
            return null;
        }

        return (InstitutionalUserInfo) user.getInfo();
    }

    public static String getCurUserSite() {
        InstitutionalUserInfo institutionalUserInfo = getInstitutionalUserInfo();

        if (institutionalUserInfo == null) {
            return null;
        }

        return institutionalUserInfo.getSite();
    }

    public static String getCurUserBirthday() {
        PrivateUserInfo privateUserInfo = getPrivateUserInfo();

        if (privateUserInfo == null) {
            return null;
        }

        return privateUserInfo.getBirthday();
    }

    public static String getCurUserNickname() {
        PrivateUserInfo privateUserInfo = getPrivateUserInfo();

        if (privateUserInfo == null) {
            return null;
        }

        return privateUserInfo.getNickname();
    }

    public static String getCurUserName() {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo == null) {
            return null;
        }

        return curUserInfo.getName();
    }

    public static String getCurUserContact() {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo == null) {
            return null;
        }

        return curUserInfo.getContact();
    }

    public static String getCurUserAddress() {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo == null) {
            return null;
        }

        return curUserInfo.getAddress();
    }


    public static void setCurUserSite(String site) {
        InstitutionalUserInfo institutionalUserInfo = getInstitutionalUserInfo();

        if (institutionalUserInfo != null) {
            institutionalUserInfo.setSite(site);
        }
    }

    public static void setCurUserBirthday(String birthday) {
        PrivateUserInfo privateUserInfo = getPrivateUserInfo();

        if (privateUserInfo != null) {
            privateUserInfo.setBirthday(birthday);
        }
    }

    public static void setCurUserNickname(String nickname) {
        PrivateUserInfo privateUserInfo = getPrivateUserInfo();

        if (privateUserInfo != null) {
            privateUserInfo.setNickname(nickname);
        }
    }

    public static void setCurUserName(String name) {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo != null) {
            curUserInfo.setName(name);
        }
    }

    public static void setCurUserContact(String contact) {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo != null) {
            curUserInfo.setContact(contact);
        }
    }

    public static void setCurUserAddress(String address) {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo != null) {
            curUserInfo.setAddress(address);
        }
    }

    public static UserType getCurUserType() {
        UserInfo curUserInfo = getCurUserInfo();

        if (curUserInfo instanceof PrivateUserInfo) {
            return UserType.PRIVATE;
        } else {
            return UserType.INSTITUTIONAL;
        }
    }
}
