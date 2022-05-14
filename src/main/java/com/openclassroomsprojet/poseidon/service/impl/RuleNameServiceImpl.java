package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.RuleName;
import com.openclassroomsprojet.poseidon.repositories.RuleNameRepository;
import com.openclassroomsprojet.poseidon.service.IRuleNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RuleNameServiceImpl implements IRuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAllRuleName() {
        log.info("Call service method: findAllRuleName()");
        return ruleNameRepository.findAll();
    }

    @Override
    public Optional<RuleName> findRuleNameById(int id) {
        log.info("Call service method: findRuleNameById(int id)");
        return ruleNameRepository.findById(id);
    }

    @Override
    public void deleteRuleNameById(int id) {
        log.info("Call service method: deleteRuleNameById(int id)");
        ruleNameRepository.deleteById(id);
    }

    @Override
    public void saveRuleName(RuleName ruleName) {
        log.info("Call service method: saveRuleName(RuleName ruleName)");
        ruleNameRepository.save(ruleName);
    }
}