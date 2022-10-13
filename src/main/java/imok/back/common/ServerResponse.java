package imok.back.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Setter
@Getter
public class ServerResponse<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    private ServerResponse(int code){
        this.code = code;
    }

    private ServerResponse(int code,T data){
        this.code = code;
        this.data = data;
    }

    private ServerResponse(int code,String message){
        this.code = code;
        this.message = message;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }


    public static <T> ServerResponse<T> createSuccess(){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createSuccessMessage(String message){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T> ServerResponse<T> createSuccessDate(T date){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),date);
    }

    public static <T> ServerResponse<T> createSuccessMessage(String message,T date){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),message,date);
    }



    public static <T> ServerResponse<T> createError(){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }


    public static <T> ServerResponse<T> createError(String errorMessage){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),errorMessage);
    }


    public static <T> ServerResponse<T> createError(int code,String errorMessage){
        return new ServerResponse<>(code,errorMessage);
    }
}
