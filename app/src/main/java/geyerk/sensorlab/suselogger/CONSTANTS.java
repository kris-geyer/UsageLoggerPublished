package geyerk.sensorlab.suselogger;

import java.util.Set;

class CONSTANTS {

    static int RETURN_GENERAL_DATA_SOURCE(int specificData){
        switch (specificData){
            case INSTALLED_AND_PERMISSION_AND_RESPONSE:
            case INSTALLED_AND_PERMISSION:
            case INSTALLED_AND_RESPONSE:
            case ONLY_INSTALLED:
            case RESPONSE_AND_PERMISSION:
            case ONLY_PERMISSION:
            case ONLY_RESPONSE:
                return CONSTANTS.COLLECTING_CONTEXTUAL_DATA;
            default:
                return CONSTANTS.NO_DATA_COLLECTED;
        }
    }

    static int RETURN_CONTEXT_TYPE(Set input){
        if(input.contains("installed")){
            if(input.contains("permission")){
                if(input.contains("response")){
                    return CONSTANTS.INSTALLED_AND_PERMISSION_AND_RESPONSE;
                }else{
                    return CONSTANTS.INSTALLED_AND_PERMISSION;
                }
            }else{
                if(input.contains("response")){
                    return CONSTANTS.INSTALLED_AND_RESPONSE;
                }else{
                    return CONSTANTS.ONLY_INSTALLED;
                }
            }
        }else if(input.contains("permission")){
            if(input.contains("response")){
                return CONSTANTS.RESPONSE_AND_PERMISSION;
            }else{
                return CONSTANTS.ONLY_PERMISSION;
            }
        }else if(input.contains("response")){
            return CONSTANTS.ONLY_RESPONSE;
        }else{
            return CONSTANTS.NO_DATA_COLLECTED;
        }
    }

    static final String
    CONTEXT_FILE = "context.pdf",
    USAGE_FILE = "usage.pdf",
    PROSPECTIVE_FILE = "prospective.pdf",
    ADDITIONAL_FILE = "additional.pdf";

    static final int
            NOT_ASSESSED = 0,
            QR_CODE_ACTIVITY = 1,
            ALERT_DIALOG_CAMERA_PERMISSION = 2,
            ALERT_DIALOG_USAGE_PERMISSION = 3,
            ALERT_DIALOG_NOTIFICATION_PERMISSION = 4,
            GENERAL_USAGE_PERMISSION_REQUEST = 5,
            ALL_PERMISSIONS_GRANTED = 6,
            SEND_EMAIL = 7,

            PERMISSION_NOT_ASSESSED = 1,
            COLLECTING_CONTEXTUAL_DATA = 1,
            COLLECTING_PAST_USAGE = 2,
            COLLECTING_PROSPECTIVE_DATA = 3,
            READY_FOR_EMAIL = 4,
            FILE_SENT = 5,

            NO_DATA_COLLECTED = 1,
            ONLY_RESPONSE = 2,
            ONLY_PERMISSION = 3,
            RESPONSE_AND_PERMISSION = 4,
            ONLY_INSTALLED = 5,
            INSTALLED_AND_RESPONSE = 6,
            INSTALLED_AND_PERMISSION = 7,
            INSTALLED_AND_PERMISSION_AND_RESPONSE = 8,

            PUTTING_CONTEXTUAL_DATA_IN_PDF = 1,
            PUTTING_USAGE_DATA_IN_PDF = 2,
            ERROR_EXPERIENCED_IN_ASYNC = 3,
            PUTTING_PROSPECTIVE_DATA_IN_PDF = 4;
}
