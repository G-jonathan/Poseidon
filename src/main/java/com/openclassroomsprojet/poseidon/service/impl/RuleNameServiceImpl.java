package com.openclassroomsprojet.poseidon.service.impl;

import com.openclassroomsprojet.poseidon.domain.RuleName;
import com.openclassroomsprojet.poseidon.repositories.RuleNameRepository;
import com.openclassroomsprojet.poseidon.service.IRuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RuleNameServiceImpl implements IRuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAllRuleName() {
        return ruleNameRepository.findAll();
    }

    @Override
    public Optional<RuleName> findRuleNameById(int id) {
        return ruleNameRepository.findById(id);
    }

    @Override
    public void deleteRuleNameById(int id) {
        ruleNameRepository.deleteById(id);
    }

    @Override
    public void saveRuleName(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }
}