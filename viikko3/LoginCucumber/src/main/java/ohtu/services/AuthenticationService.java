package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if (username.length() < 3 || password.length() < 8) return true;
        char[] uname = username.toCharArray();
        for (int i = 0; i < uname.length; i++) {
            if (!Character.isLetter(uname[i]) && !Character.isLowerCase(uname[i])) {
                return true;
            }
        }
        boolean eipelkkakirjaimia = false;
        char[] pw = password.toCharArray();
        for (int i = 0; i < pw.length; i++) {
            if (!Character.isLetter(pw[i])) {
                eipelkkakirjaimia = true;
                System.out.println("detected notLetters");
            }
        }
        if (eipelkkakirjaimia) return false;
        return true;
    }
}
