package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.Trade;
import com.openclassroomsprojet.poseidon.repositories.TradeRepository;
import com.openclassroomsprojet.poseidon.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Trade> findAllTrade() {
        return tradeRepository.findAll();
    }

    @Override
    public Optional<Trade> findTradeById(int id) {
        return tradeRepository.findById(id);
    }

    @Override
    public void deleteTradeById(int id) {
        tradeRepository.deleteById(id);
    }

    @Override
    public void saveTrade(Trade trade) {
        Date currentDate = new Date();
        trade.setCreationDate(currentDate);
        tradeRepository.save(trade);
    }
}