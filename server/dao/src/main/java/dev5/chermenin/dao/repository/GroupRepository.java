package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ancarian on 14.11.2017.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "SELECT gr.* FROM groups gr" +
            " JOIN users ON gr.id = users.groups_id " +
            " JOIN users_subjects ON users.id = users_subjects.users_id" +
            " GROUP BY gr.id" +
            " ORDER BY AVG(users_subjects.mark)", nativeQuery = true)
    List<Group> getTopGroupsByAvgMark();

    @Query("SELECT gr FROM Group gr WHERE gr.information like :info")
    Group findByInformation(@Param("info") String info);

    @Query(value =
            "SELECT\n" +
                    "  sum(po.mark) AS sum\n" +
                    "FROM USERS_SUBJECTS po\n" +
                    "  JOIN USERS ON USERS.ID = po.USERS_ID\n" +
                    "  JOIN user_information_roles\n" +
                    "    ON USERS.id = user_information_roles.USER_INFORMATION_ID\n" +
                    "WHERE GROUPS_ID = :groupId AND USER_INFORMATION_ID = 2\n" +
                    "\n" +
                    "GROUP BY po.USERS_ID\n" +
                    "ORDER BY sum DESC\n" +
                    "LIMIT 1", nativeQuery = true)
    Integer getEnrollMark(@Param("groupId") long groupId);


    @Query(value = "SELECT count(*) from USERS\n" +
            "  JOIN USER_INFORMATION_ROLES ON USERS.ID = USER_INFORMATION_ROLES.USER_INFORMATION_ID\n" +
            "  WHERE GROUPS_ID = :groupId AND USER_INFORMATION_ID = 2", nativeQuery = true)
    Integer getCountUsersInGroup(@Param("groupId") long groupId);


}
