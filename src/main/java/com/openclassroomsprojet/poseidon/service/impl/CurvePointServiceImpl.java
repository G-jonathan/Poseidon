package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.CurvePoint;
import com.openclassroomsprojet.poseidon.repositories.CurvePointRepository;
import com.openclassroomsprojet.poseidon.service.ICurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> findAllCurvePoint() {
        return curvePointRepository.findAll();
    }

    @Override
    public Optional<CurvePoint> findCurvePointById(int id) {
        return curvePointRepository.findById(id);
    }

    @Override
    public void deleteCurvePointById(int id) {
        curvePointRepository.deleteById(id);
    }

    @Override
    public void saveCurvePoint(CurvePoint curvePoint) {
        Date currentDate = new Date();
        curvePoint.setCreationDate(currentDate);
        curvePointRepository.save(curvePoint);
    }
}