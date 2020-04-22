package Core;


import java.io.Serializable;

public class SaveFileCache implements Serializable {
    private String userEmail;
    private String userPassword;
    private Boolean status;
    public SaveFileCache(String userEmail, String userPassword){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.status = true;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
