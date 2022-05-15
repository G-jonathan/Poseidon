package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.CurvePoint;
import com.openclassroomsprojet.poseidon.repositories.CurvePointRepository;
import com.openclassroomsprojet.poseidon.service.ICurvePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> findAllCurvePoint() {
        log.info("Call service method: findAllCurvePoint()");
        return curvePointRepository.findAll();
    }

    @Override
    public Optional<CurvePoint> findCurvePointById(int id) {
        log.info("Call service method: findCurvePointById(int id)");
        return curvePointRepository.findById(id);
    }

    @Override
    public void deleteCurvePointById(int id) {
        log.info("Call service method: deleteCurvePointById(int id)");
        curvePointRepository.deleteById(id);
    }

    @Override
    public void saveCurvePoint(CurvePoint curvePoint) {
        log.info("Call service method: saveCurvePoint(CurvePoint curvePoint)");
        Date currentDate = new Date();
        curvePoint.setCreationDate(currentDate);
        curvePointRepository.save(curvePoint);
    }
}