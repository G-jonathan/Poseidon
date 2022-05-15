package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.BidList;
import com.openclassroomsprojet.poseidon.repositories.BidListRepository;
import com.openclassroomsprojet.poseidon.service.IBidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BidListServiceImpl implements IBidListService {

    @Autowired
    private BidListRepository bidListRepository;

    @Override
    public List<BidList> findAllBidList() {
        log.info("Call service method: findAllBidList()");
        return bidListRepository.findAll();
    }

    @Override
    public Optional<BidList> findBidListById(int id) {
        log.info("Call service method: findBidListById(int id)");
        return bidListRepository.findBidListByBidListId(id);
    }

    @Override
    public void deleteBidListById(int id) {
        log.info("Call service method: deleteBidListById(int id)");
        bidListRepository.deleteById(id);
    }

    @Override
    public void saveBidList(BidList bidList) {
        log.info("Call service method: saveBidList(BidList bidList)");
        Date currentDate = new Date();
        bidList.setCreationDate(currentDate);
        bidListRepository.save(bidList);
    }
}