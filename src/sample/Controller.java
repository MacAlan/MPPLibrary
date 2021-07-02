package sample;

import java.lang.reflect.Member;

public class Controller {
    private Member user;

    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public boolean validate(String user, String password){

        return false;
    }
}
