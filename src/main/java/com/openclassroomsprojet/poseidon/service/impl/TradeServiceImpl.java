package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.Trade;
import com.openclassroomsprojet.poseidon.repositories.TradeRepository;
import com.openclassroomsprojet.poseidon.service.ITradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This class contains methods that allow performing CRUD actions by calling the trade repository
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@Slf4j
@Service
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Allows to search all the Trades contained in the database
     *
     * @return A list of Trade
     */
    @Override
    public List<Trade> findAllTrade() {
        log.info("Call service method: findAllTrade()");
        return tradeRepository.findAll();
    }

    /**
     * Searches for a Trade object from its identifier
     *
     * @param id The identifier of the object to find
     * @return An optional which may or may not contain the requested object
     */
    @Override
    public Optional<Trade> findTradeById(int id) {
        log.info("Call service method: findTradeById(int id)");
        return tradeRepository.findById(id);
    }

    /**
     * Delete a Trade
     *
     * @param id The identifier of the object to delete
     */
    @Override
    public void deleteTradeById(int id) {
        log.info("Call service method: deleteTradeById(int id)");
        tradeRepository.deleteById(id);
    }

    /**
     * Save a Trade
     *
     * @param trade Object to be saved
     */
    @Override
    public void saveTrade(Trade trade) {
        log.info("Call service method: saveTrade(Trade trade)");
        Date currentDate = new Date();
        trade.setCreationDate(currentDate);
        tradeRepository.save(trade);
    }
}