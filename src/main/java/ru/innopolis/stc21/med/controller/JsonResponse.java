package ru.innopolis.stc21.med.controller;

public class JsonResponse {

    private String neiro_diagnose;
    private String accuracy;

    public String getNeiro_diagnose() {
        return neiro_diagnose;
    }

    public void setNeiro_diagnose(String neiro_diagnose) {
        this.neiro_diagnose = neiro_diagnose;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}
