package com.qwj.girl.dao.oracle;

import com.qwj.girl.entity.oracle.BadHooker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadHookerDao extends JpaRepository<BadHooker, String> {

    Integer countByCode(String code);

}
