package tech.alei.html2pdf.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class JsonToHtmlUtil
{
    /**
     * @param jsonData  json格式的数据
     * @param templateName 在templates/目录下，html模板的名称（不包含.html后缀）
     * @return html字符串
     */
    public static String getHtml(String jsonData, String templateName)
    {
        // 构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/"); // 模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html"); // 模板文件后缀

        return createHtml(resolver, jsonData, templateName);
    }

    /**
     * @param jsonData  json格式的数据
     * @param template html模板内容
     * @return html字符串
     */
    public static String getHtmlFromString(String jsonData, String template) {
        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);

        return createHtml(resolver, jsonData, template);
    }

    private static String createHtml(AbstractTemplateResolver resolver, String jsonData, String template) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        // 构造上下文(Model)
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model = (Map<String, Object>) new ObjectMapper().readValue(jsonData, HashMap.class);
        } catch (JsonParseException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Context context = new Context();
        context.setVariables(model);

        // 渲染模板
        StringWriter writer = new StringWriter();
        templateEngine.process(template, context, writer);
        String html = writer.toString();
        return html;
    }

    public static String getJsonFromFile() {
        String text = "";
        Resource resource = new ClassPathResource("origin-flight-query.json");
        File file = null;
        try {
            file = resource.getFile();
            text = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String getTemplateFromFile() {
        String text = "";
        Resource resource = new ClassPathResource("templates/origin-flight-query.html");
        File file = null;
        try {
            file = resource.getFile();
            text = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
