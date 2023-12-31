package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAllByOrderByCompanyTitleAscRoleDescriptionAsc();

    List<User> findAllByRoleDescriptionOrderByCompanyTitleAscRoleDescriptionAsc(String roleDescription);

    List<User> findAllByCompanyTitleOrderByCompanyTitleAscRoleDescriptionAsc(String companyTitle);

    int countByCompanyTitleAndRoleDescription(String title, String roleDescription);
}
