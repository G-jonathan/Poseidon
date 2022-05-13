package com.openclassroomsprojet.poseidon.service;

import com.openclassroomsprojet.poseidon.domain.RuleName;
import java.util.List;
import java.util.Optional;

public interface IRuleNameService {

    List<RuleName> findAllRuleName();

    Optional<RuleName> findRuleNameById(int id);

    void deleteRuleNameById(int id);

    void saveRuleName(RuleName ruleName);
}