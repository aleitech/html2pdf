package tech.alei.html2pdf.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.alei.html2pdf.util.JsonToHtmlUtil;

import java.util.HashMap;

@RestController
public class ConvertController {

    /**
     * 首页测试
     * @return
     */
    @GetMapping("")
    public String index() {
        return "hello world";
    }

    /**
     * 根据json格式的数据和html模板内容，生成html
     * @param paramMap
     * @return html string
     */
    @PostMapping(value="/html", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getHtml(@RequestBody HashMap<String, String> paramMap) {
        // 示例代码，可以删除
        String jsonData2 = JsonToHtmlUtil.getJsonFromFile();
        String template2 = JsonToHtmlUtil.getTemplateFromFile();
        String html2 = JsonToHtmlUtil.getHtmlFromString(jsonData2, template2);
        System.out.println("html2*******************************");
        System.out.println(html2);

        // 真实代码
        String jsonData = paramMap.get("jsonData");
        String template = paramMap.get("template");
        String html = JsonToHtmlUtil.getHtmlFromString(jsonData, template);
        System.out.println("html==========================");
        System.out.println(html);

        return html;
    }
}
