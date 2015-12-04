package tw.edu.hk.db_list;

/**
 * Created by Lin on 12/3/2015.
 */
public class UserItem {
    String name;
    Integer code;
    String phone;
    int id;

    public UserItem(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Integer getCode() {
        return code;
    }

    public String getPhone() {
        return phone;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
