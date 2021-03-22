package com.qwj.girl.dao;

import com.qwj.girl.entity.BadGirl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadGirlDao extends JpaRepository<BadGirl, String> {

    Integer countByCode(String code);

}
