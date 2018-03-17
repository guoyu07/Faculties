package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ancarian on 14.11.2017.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByInformation(@Param("info") String info);

    @Modifying
    @Query(value = "UPDATE groups AS gr SET gr.FACULTIES_ID = :facultiesId WHERE gr.id = :groupId", nativeQuery = true)
    void updateFaculty(@Param("groupId") long groupId, @Param("facultiesId") long facultiesId);

    @Query(value =
            "SELECT\n" +
                    "  sum(po.mark) AS sum\n" +
                    "FROM USERS_SUBJECTS po\n" +
                    "  JOIN USERS ON USERS.ID = po.USERS_ID\n" +
                    "  JOIN user_information_roles\n" +
                    "    ON USERS.id = user_information_roles.USER_INFORMATION_ID\n" +
                    "WHERE GROUPS_ID = :groupId AND USER_INFORMATION_ID = 2\n" +
                    "GROUP BY po.USERS_ID\n" +
                    "ORDER BY sum DESC\n" +
                    "LIMIT 1", nativeQuery = true)
    Integer getEnrollMark(@Param("groupId") long groupId);

}
