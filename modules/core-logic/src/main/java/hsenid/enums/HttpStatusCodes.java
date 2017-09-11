package hsenid.enums;

/**
 * Created by hsenid on 9/11/17.
 */
public enum HttpStatusCodes {


    OK(200),
    CREATED(201),
    INTERNAL_SERVER_ERROR(500),
    UNAUTHORIZED(401);

    private int value;

    HttpStatusCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
