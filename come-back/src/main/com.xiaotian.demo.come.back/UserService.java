package main.com.xiaotian.demo.come.back;

public class UserService {
    private final IUserBo userBo;
    private final IUserBiz userBiz;

    public UserService(IUserBo userBo, IUserBiz userBiz) {
        this.userBo = userBo;
        this.userBiz = userBiz;
    }


    public boolean changeUserName(String newUserName) {
        return userBiz.changeUserName(newUserName);
    }

    public boolean changeUserAge(int newUserAge) {
        return userBiz.changeAge(newUserAge);
    }
}
