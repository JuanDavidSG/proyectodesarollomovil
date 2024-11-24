package com.example.prolinkfinal;

public class Service {
    private String title;
    private int availableProviders;
    private int imageResourceId;
    private double starts;

    public Service(String title, int availableProviders, int imageResourceId, double starts) {
        this.title = title;
        this.availableProviders = availableProviders;
        this.imageResourceId = imageResourceId;
        this.starts = starts;
    }

    public int getImageResourceId() { return imageResourceId; }
    public String getTitle() {
        return title;
    }
    public int getAvailableProviders() {
        return availableProviders;
    }
    public double getStarts() { return starts; }
}
