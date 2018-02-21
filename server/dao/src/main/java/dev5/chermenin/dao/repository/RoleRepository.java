//package dev5.chermenin.dao.repository;
//
//import dev5.chermenin.model.entity.impl.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Set;
//
///**
// * Created by Ancarian on 01.12.2017.
// */
//
//@Transactional
//@Repository
//public interface RoleRepository extends JpaRepository<Role, Long> {
//
//    @Query("SELECT role FROM Role role WHERE role.role = :name")
//    Role findByName(@Param("name") String name);
//
//    @Query(value = "SELECT role.* FROM roles role\n" +
//            "        JOIN user_information_roles ON roles_id = role.id WHERE users_id = :userId", nativeQuery = true)
//    Set<Role> getRolesByUser(@Param("userId") long id);
//
//}
