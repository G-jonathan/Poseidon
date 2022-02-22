package com.openclassroomsprojet.poseidon.repositories;

import com.openclassroomsprojet.poseidon.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    Optional<BidList> findBidListByBidListId(int id);
}