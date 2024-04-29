package ru.vladimir;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class FilesParsingTest {
    ClassLoader cl =  FilesParsingTest.class.getClassLoader();
    @Test
    void pdfParseTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedPdf = $("a[href='junit-user-guide-5.10.2.pdf']").download();
        PDF content = new PDF(downloadedPdf);// класс PDF уже содержит внутри класс InputStream
        assertThat(content.author).contains("Sam Brannen");
    }
//    @Test
//    void xlsParseTest() throws Exception {
//        open("https://hthwater.ru/spisokgorodovrossii");
//        File downloadedXml = $("//*[@id='content']/a[2]").download();
//        XLS content2 = new XLS(downloadedXml);
//    }

    @Test
    void xlsParseTest2() throws Exception {
        try (InputStream resourceAsStream =
                     cl.getResourceAsStream("example/table.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            System.out.println("jj");
        }
    }

}
