package com.qwj.girl.dao;

import com.qwj.girl.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GirlDao extends JpaRepository<Girl, String> {

    Integer countByCode(String code);

}
