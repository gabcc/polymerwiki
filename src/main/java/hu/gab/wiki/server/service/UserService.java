package hu.gab.wiki.server.service;

import hu.gab.wiki.server.DTO;
import hu.gab.wiki.server.dal.DBTemplate;
import hu.gab.wiki.server.dal.TransactionDBTemplate;
import hu.gab.wiki.server.dao.DAO_Role;
import hu.gab.wiki.server.dao.DAO_User;
import hu.gab.wiki.server.dao.DAO_UserVersion;
import hu.gab.wiki.server.entity.Role;
import hu.gab.wiki.server.entity.User;
import hu.gab.wiki.server.entity.UserVersion;
import hu.gab.wiki.shared.dto.useradmin.DTO_Role;
import hu.gab.wiki.shared.dto.useradmin.DTO_User;
import hu.gab.wiki.shared.status.UserStatus;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PG
 * @since 2016-05-11
 */
public class UserService {
    public static final UserService instance = new UserService();

    private SecureRandom rnd = new SecureRandom();

    public String createHash(String password) {
        return password;
    }

    public String createToken() {
        return new BigInteger(130, rnd).toString(32);
    }

    public User addNewUser(final String name, final String email, final String password) {
        User result = new DBTemplate<User>((session, template) -> {
            new TransactionDBTemplate<User>(session, (session1, transactionDBTemplate) -> {
                User userByMail = DAO_User.findByEmail(session, email);
                if (userByMail == null) {
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPasswordHash(createHash(password));
                    user.setCreated(new Date());
                    user.setStatus(UserStatus.DISABLED);

                    UserVersion userVersion = new UserVersion();
                    userVersion.setUser(user);
                    userVersion.setCreated(new Date());

                    user.getVersions().add(userVersion);

                    session.save(user);

                    template.setResult(user);
                } else {
                    throw new RuntimeException("Van már user ilyen email címmel.");
                }
            });
        }).getResult();

        return result;
    }

    public void updateUser(final DTO_User user) {
        new DBTemplate<Void>((session, template) -> {
            new TransactionDBTemplate<Void>(session, (session1, transactionDBTemplate) -> {
                User oldUser = DAO_User.get(session1, user.getId());
                if (oldUser == null) throw new RuntimeException("Nincs ilyen id-val rendelkező user.");

                oldUser.setName(user.getName());
                oldUser.setEmail(user.getEmail());

                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    oldUser.setPasswordHash(createHash(user.getPassword()));
                }

                UserVersion userVersion = new UserVersion();
                userVersion.setUser(oldUser);
                userVersion.setCreated(new Date());

                List<Role> newRoles = DAO_Role.list(session1, user.getRoles().stream().map(p -> p.getId()).collect(Collectors.toSet()));
                userVersion.setRoles(newRoles);

                DAO_User.update(session1, oldUser);
                DAO_UserVersion.add(session1, userVersion);
            });
        });
    }

    public List<DTO_Role> getRoles() {
        return new DBTemplate<List<DTO_Role>>((session, template) -> {
            List<Role> list = DAO_Role.list(session);
            template.setResult(list.stream().map(DTO.instance::dto).collect(Collectors.toList()));
        }).getResult();
    }
}
