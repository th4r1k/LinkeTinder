package linketinder.DAO

import linketinder.Entity.User

interface UserDAOInterface {
    void getUsers()
    User create(User newUser)
    void deleteUser(int id, String category)
    User getUserInfo(String name)

}