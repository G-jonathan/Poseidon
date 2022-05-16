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

/**
 * This class contains methods that allow performing CRUD actions by calling the bidList repository
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@Slf4j
@Service
public class BidListServiceImpl implements IBidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Allows to search all the BidLists contained in the database
     *
     * @return A list of BidList
     */
    @Override
    public List<BidList> findAllBidList() {
        log.info("Call service method: findAllBidList()");
        return bidListRepository.findAll();
    }

    /**
     * Searches for a BidList object from its identifier
     *
     * @param id The identifier of the object to find
     * @return An optional which may or may not contain the requested object
     */
    @Override
    public Optional<BidList> findBidListById(int id) {
        log.info("Call service method: findBidListById(int id)");
        return bidListRepository.findBidListByBidListId(id);
    }

    /**
     * Delete a BidList
     *
     * @param id The identifier of the object to delete
     */
    @Override
    public void deleteBidListById(int id) {
        log.info("Call service method: deleteBidListById(int id)");
        bidListRepository.deleteById(id);
    }

    /**
     * Save a BidList
     *
     * @param bidList Object to be saved
     */
    @Override
    public void saveBidList(BidList bidList) {
        log.info("Call service method: saveBidList(BidList bidList)");
        Date currentDate = new Date();
        bidList.setCreationDate(currentDate);
        bidListRepository.save(bidList);
    }
}