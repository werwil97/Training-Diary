package pl.coderslab.security;


public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

}