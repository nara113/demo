package we.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import we.demo.domain.Form;
import we.demo.domain.Result;
import we.demo.domain.Type;
import we.demo.service.FormService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class FormController {
    private final FormService formService;

    @ModelAttribute("searchTypes")
    public Type[] searchTypes() {
        return Type.values();
    }

    @GetMapping
    public String getForm(@ModelAttribute Form form) {
        return "/form";
    }

    @PostMapping
    public String post(@Valid @ModelAttribute Form form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/form";
        }

        Result result;

        try {
            result = formService.getResult(form);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("url", "invalid", "invalid url");

            return "/form";
        }

        model.addAttribute("result", result);

        return "/form";
    }

}
