package Classes;

public class Credential {
    private String user;        
    private String password;     

    public Credential(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
