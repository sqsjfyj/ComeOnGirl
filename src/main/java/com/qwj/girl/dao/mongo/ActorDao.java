package com.qwj.girl.dao.mongo;

import com.qwj.girl.entity.mongo.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorDao extends MongoRepository<Actor, String> {

    Integer countByCode(String code);

}
