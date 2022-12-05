package edu.uwb.css143.repository;

import edu.uwb.css143.model.Index;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
DO NOT CHANGE
 */

public interface IndexRepository extends JpaRepository<Index, Integer> {
    @Override
    List<Index> findAll();

    @Override
    Optional<Index> findById(Integer integer);
}
