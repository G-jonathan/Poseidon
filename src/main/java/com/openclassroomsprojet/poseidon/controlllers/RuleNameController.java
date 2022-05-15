package com.openclassroomsprojet.poseidon.controlllers;

import com.openclassroomsprojet.poseidon.domain.RuleName;
import com.openclassroomsprojet.poseidon.service.IRuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.Optional;

/**
 * This controller provides CRUD operations on RuleName entity
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */

@Controller
public class RuleNameController {

    @Autowired
    private IRuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleName", ruleNameService.findAllRuleName());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<RuleName> ruleName = ruleNameService.findRuleNameById(id);
        model.addAttribute("ruleName", ruleName.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id)));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        Optional<RuleName> ruleName = ruleNameService.findRuleNameById(id);
        if (ruleName.isPresent()) {
            ruleNameService.deleteRuleNameById(id);
        } else {
            throw new IllegalArgumentException("RuleName id not found");
        }
        return "redirect:/ruleName/list";
    }
}