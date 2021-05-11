package com.qwj.girl.dao.oracle;

import com.qwj.girl.entity.oracle.Hooker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HookerDao extends JpaRepository<Hooker, String> {

    Integer countByCode(String code);

}
