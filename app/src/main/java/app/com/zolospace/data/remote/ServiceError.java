package app.com.zolospace.data.remote;


public class ServiceError {
    public static final String NETWORK_ERROR = "Unknown ServiceError";
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 400;
     static final int GROUP_200 = 2;
     static final int GROUP_400 = 4;
     static final int GROUP_500 = 5;
     static final int VALUE_100 = 100;
     String description;
     int code;

    public ServiceError() {
    }

    public ServiceError(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public static boolean isSuccess(int responseCode) {
        return responseCode / VALUE_100 == GROUP_200;
    }

    public static boolean isClientError(int errorCode) {
        return errorCode / VALUE_100 == GROUP_400;
    }

    public static boolean isServerError(int errorCode) {
        return errorCode / VALUE_100 == GROUP_500;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
