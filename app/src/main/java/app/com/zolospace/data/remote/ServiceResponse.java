package app.com.zolospace.data.remote;


public class ServiceResponse {
    int code;
    Object data;
    ServiceError ServiceError;

    public ServiceResponse(int code, Object response) {
        this.code = code;
        this.data = response;
    }

    public ServiceResponse(ServiceError ServiceError) {
        this.ServiceError = ServiceError;
    }

    public ServiceResponse(Object response) {
        this.data = response;
    }

    public int getCode() {
        return code;
    }

    public ServiceError getServiceError() {
        return ServiceError;
    }

    public Object getData() {

        return data;
    }
}
