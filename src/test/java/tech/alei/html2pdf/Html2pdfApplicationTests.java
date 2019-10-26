package tech.alei.html2pdf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import tech.alei.html2pdf.util.HtmlToPdfUtil;
import tech.alei.html2pdf.util.JsonToHtmlUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Html2pdfApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void getJson() {
	    String text = "";
        Resource resource = new ClassPathResource("origin-flight-query.json");
        File file = null;
        try {
            file = resource.getFile();
            text = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }

    @Test
    public void toHtml() {
        String jsonData = JsonToHtmlUtil.getJsonFromFile();
        String html = JsonToHtmlUtil.getHtml(jsonData, "origin-flight-query");
        System.out.println(html);
    }

    @Test
    public void toHtmlFromString() {
        String jsonData = JsonToHtmlUtil.getJsonFromFile();
        String template = JsonToHtmlUtil.getTemplateFromFile();
        String html = JsonToHtmlUtil.getHtmlFromString(jsonData, template);

        System.out.println(html);
    }

    @Test
    public void toPdf() {
        String jsonData = JsonToHtmlUtil.getJsonFromFile();
        String html = JsonToHtmlUtil.getHtml(jsonData, "origin-flight-query");

        // 需要修改成本机的路径
        String fileName = "C:\\space\\911test.pdf";
        HtmlToPdfUtil.toPdfFile(html, fileName);
    }

}
