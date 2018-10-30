package tech.alei.html2pdf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {

    @GetMapping("")
    public String index() {
        return "hello";
    }
}
