package com.qwj.girl.dao.redis;

import com.qwj.girl.entity.redis.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao extends CrudRepository<Teacher, String> {

    List<Teacher> findByCode(String code);

}
