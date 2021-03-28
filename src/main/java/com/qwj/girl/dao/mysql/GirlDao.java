package com.qwj.girl.dao.mysql;

import com.qwj.girl.entity.mysql.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GirlDao extends JpaRepository<Girl, String> {

    Integer countByCode(String code);

}
