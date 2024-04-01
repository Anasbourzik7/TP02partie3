package ma.enset.tp02partie3.service;

import ma.enset.tp02partie3.entities.Role;
import ma.enset.tp02partie3.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User authentificate(String userName,String password);
}
