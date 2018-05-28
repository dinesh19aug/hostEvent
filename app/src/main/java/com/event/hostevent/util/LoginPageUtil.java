package com.event.hostevent.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginPageUtil {
    private static final String EMPTY_FIELDS = "EMPTY_FIELDS";
    private static HashMap<String, String> errorMap = new HashMap<>();


    public static List<String>  validate(String emailId, String password){
        List <String> faultCode = new ArrayList<>();
        if(isFieldEmpty(emailId, password)){
            faultCode.add(EMPTY_FIELDS);
            errorMap.put(EMPTY_FIELDS,"Email or Password field is empty");
        }
        return faultCode;
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
            errorText = errorMap.get(EMPTY_FIELDS);
        }

        Toast.makeText(context,errorText, Toast.LENGTH_LONG).show();

    }
}
