package com.qwj.girl.dao.redis;

import com.qwj.girl.entity.redis.BadTeacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadTeacherDao extends CrudRepository<BadTeacher, String> {

    List<BadTeacher> findByCode(String code);

}
