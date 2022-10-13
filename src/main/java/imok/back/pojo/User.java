package imok.back.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String role;
    private String name;
    private String password;
    private String idCard;
    private String phone;
    private String gender;
    private String entryTime;
    private String status;


}
