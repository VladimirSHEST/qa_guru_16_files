package ru.vladimir;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class selenideFilesTest2 {
    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("[data-testid='raw-button']").download(); // скачали файл
        // читаем этот файл
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes(); // читаем этот файл
            String textContent = new String(bytes, StandardCharsets.UTF_8);  // преобраз в String
            assertThat(textContent).contains("This repository is the home of _JUnit 5_.");
            // assertThat это проверка, что в скачанном файле есть нужный текст
            // InputStream надо закрывать
        }
    }

//    @Disabled("пример использования PROXY, если нет href")
//    @Test
//    void goTest(){
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//    }
//
//    @Disabled("так делать не надо")
//    @Test
//    void selenideUploadFile(){
//        open("https://fineuploader.com/demos.html");
//        $("input[type='file']").uploadFile(new File("build/downloads/53b17e54-420e-4835-baf3-e71b17e467ec/README.md"));
//    }
       @Test
        void selenideUploadFile(){
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("example/nature.jpg");
        $("div.qq-file-name").shouldHave(Condition.text("nature.jpg"));
        // пример проверки
       // $("span.qq-upload-file-selector").shouldHave(attribute("title","nature.jpg"));


    }
}

