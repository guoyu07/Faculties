package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ancarian on 14.11.2017.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT user.* FROM users user WHERE user.groups_id IS NOT NULL " +
            "AND user.id NOT IN " +
            "(SELECT USER_INFORMATION_ID FROM user_information_roles WHERE user.id = USER_INFORMATION_ID AND ROLES = 2)" +
            "LIMIT 0, 100"
            , nativeQuery = true)
    List<User> getRequests();

    @Query(value = "SELECT user.*  FROM users user\n" +
            "            JOIN users_subjects po \n" +
            "            ON user.id = po.users_id\n" +
            "            JOIN user_information_roles " +
            "            ON user.id = user_information_roles.USER_INFORMATION_ID" +

            "            WHERE user.groups_id = :group AND roles = 2" +
            "            GROUP BY user.id\n" +
            "            ORDER BY sum(mark) DESC" +
            "            LIMIT 0, 100", nativeQuery = true)
    List<User> getVerifiedUsersInGroup(@Param("group") long group);

    @Query(value = "SELECT Users.*\n" +
            "FROM USERS_SUBJECTS po\n" +
            "  JOIN GROUPS ON GROUPS.ID = :groupId\n" +
            "  JOIN USERS ON USERS.ID = po.USERS_ID\n" +
            "  JOIN user_information_roles\n" +
            "    ON USERS.id = user_information_roles.USER_INFORMATION_ID\n" +
            "WHERE GROUPS_ID = :groupId AND USER_INFORMATION_ID = 2\n" +
            "\n" +
            "GROUP BY po.USERS_ID\n" +
            "ORDER BY sum(po.mark) DESC\n" +
            "LIMIT (SELECT GROUPS.COUNT FROM GROUPS WHERE GROUPS.ID = :groupId)", nativeQuery = true)
    List<User> getEnrollUsers(@Param("groupId") long groupId);

    @Query(value = "SELECT sum(po.mark) AS total_quantity FROM users_subjects po\n" +
            "WHERE po.users_id = :id", nativeQuery = true)
    Integer getAmount(@Param("id") long id);

}
