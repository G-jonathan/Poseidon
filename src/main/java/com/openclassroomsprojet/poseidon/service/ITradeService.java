package com.openclassroomsprojet.poseidon.service;

import com.openclassroomsprojet.poseidon.domain.Trade;
import java.util.List;
import java.util.Optional;

public interface ITradeService {
    List<Trade> findAllTrade();

    Optional<Trade> findTradeById(int id);

    void deleteTradeById(int id);

    void saveTrade(Trade trade);
}