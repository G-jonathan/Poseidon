package com.openclassroomsprojet.poseidon.repositories;

import com.openclassroomsprojet.poseidon.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {
}