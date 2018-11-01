package tech.alei.html2pdf.util;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class HtmlToPdfUtil
{
    public static void toPdfFile(String html, String fileName)
    {
        Pdf pdf = new Pdf();
        pdf.addPageFromString(html);

        try
        {
            pdf.saveAs(fileName);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void toPdfResponse(String html, HttpServletResponse response)
    {
        Pdf pdf = new Pdf();
        pdf.addPageFromString(html);
        response.setContentType("application/pdf");
        try
        {
            OutputStream out = response.getOutputStream();
            out.write(pdf.getPDF());
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
