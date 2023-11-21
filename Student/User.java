package 学生管理系统;

public class User {
    private String username;
    private String password;
    private String pensonID;
    private String phoneNumber;


    public User() {
    }

    public User(String username, String password, String pensonID, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.pensonID = pensonID;
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return pensonID
     */
    public String getPensonID() {
        return pensonID;
    }

    /**
     * 设置
     * @param pensonID
     */
    public void setPensonID(String pensonID) {
        this.pensonID = pensonID;
    }

    /**
     * 获取
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
