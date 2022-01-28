package com.openclassroomsprojet.poseidon.service;

import com.openclassroomsprojet.poseidon.repositories.BidListRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServiceTests {

    @Autowired
    private BidListRepository bidListRepository;
}