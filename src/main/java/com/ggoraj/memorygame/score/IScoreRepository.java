package com.ggoraj.memorygame.score;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IScoreRepository extends CrudRepository<Score, Long> {



}
