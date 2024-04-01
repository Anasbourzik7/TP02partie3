package ma.enset.tp02partie3.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.tp02partie3.entities.Role;
import ma.enset.tp02partie3.entities.User;
import ma.enset.tp02partie3.repository.RoleRepository;
import ma.enset.tp02partie3.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {

        return roleRepository.FindByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user=this.findUserByUserName(userName);
        Role role=this.findRoleByRoleName(roleName);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public User authentificate(String userName, String password) {
        User user=userRepository.findByUserName(userName);

        if(user==null)  throw new RuntimeException("Bad credentials");

        if(user.getPassword().equals(password))
            return user;

        throw new RuntimeException("Bad credentials");
    }

}
