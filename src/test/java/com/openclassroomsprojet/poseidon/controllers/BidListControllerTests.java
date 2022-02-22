package com.openclassroomsprojet.poseidon.controllers;

import com.openclassroomsprojet.poseidon.controlllers.BidListController;
import com.openclassroomsprojet.poseidon.service.IBidListService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)
public class BidListControllerTests {

    @MockBean
    IBidListService bidListService;
}
