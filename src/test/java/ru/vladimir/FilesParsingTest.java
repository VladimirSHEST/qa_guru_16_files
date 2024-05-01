package ru.vladimir;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.io.Zip;

import java.io.FileInputStream;
import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
    @DisplayName("в папку ресурсы поместили эксель, читаем этот файл, указываем лист-строку-таблицу и что там находится")
    @Test
    void xlsParseTest2() throws Exception {
        try (InputStream resourceAsStream =
                     cl.getResourceAsStream("example/table.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("1120");
        }
    }
    @DisplayName("работа с CSV файлом")
    @Test
    void csvParseTest() throws Exception {
        try (
                InputStream res = cl.getResourceAsStream("example/exam.csv");
                CSVReader read = new CSVReader(new InputStreamReader(res))
        ){
            List<String[]> con = read.readAll(); // в стринговый массив списка вложили наш вайл,
            // там будет 3 массива так как 3 строки
            assertThat(con.get(0)[1]).contains("My"); // тут проверка. 1 строка и в нкй  2 ячейка - слово "My"
        }
    }
    @DisplayName("позволяет узнать имя файла внутри зип zip архива")
    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("example/1.txt.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ){
            ZipEntry entry;
            while ((entry = zis.getNextEntry())!= null) {
                assertThat(entry.getName()).contains("1.tx");
            }
        }
    }

    @DisplayName("проверяем что внутри файла Json")
    @Test
    void jsonParseTest() throws Exception{
        Gson gson = new Gson();
        try (
                InputStream res = cl.getResourceAsStream("example/glossary.json");
                InputStreamReader read = new InputStreamReader(res)
        ){
            JsonObject jsonObject = gson.fromJson(read, JsonObject.class);// парсим gson в JsonObject
            // далее проверка, вводим ключ и значения, учитывая их тип, 1 пример тип Стринг
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            // 2 пример в значение вложен объект, а в объекте строка
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("title").getAsString())
                    .isEqualTo("S");
            //  3 пример идем глубже: проверяем значение с типом булеан
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("flag").getAsBoolean())
                    .isTrue();
        }


    }

}
