package ru.vladimir;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class selenideFilesTest2 {
    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("[data-testid='raw-button']").download(); // скачали файл
        InputStream is = new FileInputStream(downloadedFile);  // читаем этот файл
        byte[] bytes = is.readAllBytes(); // читаем этот файл
        String textContent = new String(bytes, StandardCharsets.UTF_8);  // преобраз в String
        assertThat(textContent).contains("This repository is the home of JUnit 5.");
        // проверка на содержание текста
        is.close();  // InputStream надо закрывать
    }
}
