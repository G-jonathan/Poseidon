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

@Slf4j
@Service
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Trade> findAllTrade() {
        log.info("Call service method: findAllTrade()");
        return tradeRepository.findAll();
    }

    @Override
    public Optional<Trade> findTradeById(int id) {
        log.info("Call service method: findTradeById(int id)");
        return tradeRepository.findById(id);
    }

    @Override
    public void deleteTradeById(int id) {
        log.info("Call service method: deleteTradeById(int id)");
        tradeRepository.deleteById(id);
    }

    @Override
    public void saveTrade(Trade trade) {
        log.info("Call service method: saveTrade(Trade trade)");
        Date currentDate = new Date();
        trade.setCreationDate(currentDate);
        tradeRepository.save(trade);
    }
}