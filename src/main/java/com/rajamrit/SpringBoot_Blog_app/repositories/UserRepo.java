package com.rajamrit.SpringBoot_Blog_app.repositories;

import com.rajamrit.SpringBoot_Blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/* JpaRepository provides all functionality database opertion on User
JpaRepository is particularly a JPA specific extension for Repository.
It has full API CrudRepository and PagingAndSortingRepository. So, basically,
 Jpa Repository contains the APIs for basic CRUD operations, the APIS for pagination,
 and the APIs for sorting.
 */
public interface UserRepo extends JpaRepository<User, Integer>{
}
