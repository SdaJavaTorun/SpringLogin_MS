package com.example.sqlstore.login;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> { // do obsługi zapytań na regułacj
    @Query("select r from Role r inner join r.users u where u.username=?1") //join pomiędzy encjami
    Set<Role> findRolesByUsername(String username);
}


