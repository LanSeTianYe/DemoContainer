package com.xiaotian.datasource.dynamic.repository;

import com.xiaotian.datasource.dynamic.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
