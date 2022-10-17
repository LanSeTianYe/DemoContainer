package main.com.xiaotian.demo.come.back;

public class UserBiz implements IUserBiz {

    private User user;

    public UserBiz(User user) {
        this.user = user;
    }

    @Override
    public boolean changeUserName(String newUserName) {
        this.user.setName(newUserName);
        return true;
    }

    @Override
    public boolean changeAge(int age) {
        this.user.setAge(age);
        return false;
    }
}
