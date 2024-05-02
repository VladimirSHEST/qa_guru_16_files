package ru.vladimir.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {
    public  String title;  // 1 ключ
    @SerializedName("GlossDiv")  // аннотация означает, что переменнная на самом делеле с заглавной буквы
    public GlossDiv glossDiv;

    public static class GlossDiv { // 1 вложенный объект
        public String title;
        public Boolean flag;
    }
}
