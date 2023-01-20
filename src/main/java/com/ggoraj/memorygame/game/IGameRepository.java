package com.ggoraj.memorygame.game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IGameRepository extends CrudRepository<GameEntity, String> {


    @Query(value = "SELECT * FROM games g where g.user_id = ?1", nativeQuery = true)
    Collection<GameEntity> findAllGamesByUserId(Long userId);

}
