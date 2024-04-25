//package ru.vladimir;
//
//import org.junit.jupiter.api.Test;
//import java.io.File;
//import java.io.FileNotFoundException;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.open;
//public class SelenideFilesTest {
//    @Test
//    void selenideDownloadTest() {   // в тестах так не делают, а пробрасываю дальше
//        open("https://github.com/junit-team/junit5/blob/main/README.md");
//        try {
//            File downloadedFile = $("[data-testid='raw-button']").download();
//        } catch(FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }    @Test
//    void selenideDownloadTest() throws FileNotFoundException {   // в тестах делают так
//        open("https://github.com/junit-team/junit5/blob/main/README.md");
//    }
//}
