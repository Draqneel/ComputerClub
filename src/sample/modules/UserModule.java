package sample.modules;

import sample.exceptions.UserDataIncorrect;
import sample.exceptions.UserNotFoundException;
import sample.models.User;

import java.util.ArrayList;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class UserModule {
    private static String fileName = "users.txt";

    public static void saveData(ArrayList<User> users) {
        for (User user : users)
            saveData(user);
    }

    public static void saveData(User user) {
        FileWorker.saveData(user.toString(), fileName);
    }

    public static ArrayList<User> loadData() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> strings = FileWorker.loadData(fileName);
        for (String string : strings) {
            users.add(new User(
                    Integer.parseInt(string.split(" ")[0]),
                    string.split(" ")[1],
                    string.split(" ")[2],
                    string.split(" ")[3]
            ));
        }
        return users;
    }

    public static User getUserById(long id) throws UserNotFoundException {
        for (User user :
                loadData()) {
            if (id == user.getId()) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public static User getUser(String login, String password) throws UserNotFoundException, UserDataIncorrect {
        for (User user :
                loadData()) {
            if (login.equals(user.getLogin())) {
                if (!password.equals(user.getPassword()))
                    throw new UserDataIncorrect();
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public static ArrayList<String> loadDescriptions(ArrayList<User> users) {
        ArrayList<String> descriptions = new ArrayList<>();
        users.forEach(user -> descriptions.add(user.getLogin() + " " + user.getName()));
        return descriptions;
    }

    public static boolean delete(int id){
        return FileWorker.delete(id,fileName);
    }
}
