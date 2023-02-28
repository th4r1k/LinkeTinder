package linketinder.DAO

import linketinder.Entity.User

interface UserDAOInterface {
    void getUsers()
    void create(User newUser)
    void deleteUser(int id, String category)
    User getUserInfo(String name)

}