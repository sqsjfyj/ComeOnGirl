package com.qwj.girl.dao.mongo;

import com.qwj.girl.entity.mongo.BadActor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadActorDao extends MongoRepository<BadActor, String> {

    Integer countByCode(String code);

}
