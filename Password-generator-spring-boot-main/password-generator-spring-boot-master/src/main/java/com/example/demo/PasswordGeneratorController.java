package com.example.demo;

import java.security.SecureRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordGeneratorController {

    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBER_CHARS = "0123456789";
    private static final String SYMBOL_CHARS = "!@#$%^&*()_-+=<>?";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/generatePassword")
    public String generatePassword(@RequestParam int length,
                                   @RequestParam(required = false, defaultValue = "false") boolean includeUppercase,
                                   @RequestParam(required = false, defaultValue = "false") boolean includeLowercase,
                                   @RequestParam(required = false, defaultValue = "false") boolean includeNumbers,
                                   @RequestParam(required = false, defaultValue = "false") boolean includeSymbols,
                                   Model model) {

        StringBuilder allCharacters = new StringBuilder();

        if (includeUppercase) {
            allCharacters.append(UPPERCASE_CHARS);
        }
        if (includeLowercase) {
            allCharacters.append(LOWERCASE_CHARS);
        }
        if (includeNumbers) {
            allCharacters.append(NUMBER_CHARS);
        }
        if (includeSymbols) {
            allCharacters.append(SYMBOL_CHARS);
        }

        if (allCharacters.length() == 0) {
            model.addAttribute("error", "Please select at least one character type.");
            return "index";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            passwordBuilder.append(allCharacters.charAt(randomIndex));
        }

        model.addAttribute("password", passwordBuilder.toString());
        return "index";
    }
}
