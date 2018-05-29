package com.event.hostevent.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class LoginPageUtil {
    private static final String EMPTY_FIELDS = "EMPTY_FIELDS";
    private static final String INVALID_EMAIL_PATTERN = "INVALID_EMAIL_PATTERN";


    public static List<String>  validate(String emailId, String password){
        List <String> faultCode = new ArrayList<>();
        if(isFieldEmpty(emailId, password)){
            faultCode.add(EMPTY_FIELDS);
        }else if(!isAnEmailAddress(emailId)){
            faultCode.add(INVALID_EMAIL_PATTERN);
        }
        return faultCode;
    }

    private static boolean isAnEmailAddress(String emailId) {
        return Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
    }

    private static boolean isFieldEmpty(String emailId, String password) {
        if(TextUtils.isEmpty(emailId) || TextUtils.isEmpty(password)){
            return true;
        }
        return false;
    }



    public static void createAndShowToast(Context context, List<String> faultCodes) {
        String errorText=null;
        if(faultCodes.get(0).equalsIgnoreCase(EMPTY_FIELDS)){
            errorText = EnumErrorCode.EMPTY_FIELDS.getErrorDesc();
        }else if(faultCodes.get(0).equalsIgnoreCase(INVALID_EMAIL_PATTERN)){
            errorText = EnumErrorCode.INVALID_EMAIL_PATTERN.getErrorDesc();
        }

        Toast.makeText(context,errorText, Toast.LENGTH_LONG).show();

    }
}
