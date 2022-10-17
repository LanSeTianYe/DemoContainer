package main.com.xiaotian.demo.come.back;

public class UserBo implements IUserBo {

    private User user;


    @Override
    public String getName() {
        return this.user.getName();
    }

    @Override
    public int getAge() {
        return this.user.getAge();
    }
}
