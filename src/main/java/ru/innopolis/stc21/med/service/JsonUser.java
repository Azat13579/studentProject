package ru.innopolis.stc21.med.service;

import lombok.Data;

@Data
public class JsonUser {
    long id;
    String accuracy;
    String result;

    public JsonUser(long id, String result, String accuracy) {
        this.id = id;
        this.result = result;
        this.accuracy = accuracy;
    }
}