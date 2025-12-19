package com.business_app.authservice.constants;

public final class Apipaths {
    public Apipaths() {
    }
    // Base paths
    public static final String API_BASE = "/api";
    public static final String AUTH_BASE = API_BASE + "/auth";

    // Auth endpoints
    public static final String REGISTER ="/register";
    public static final String LOGIN ="/login";
    public static final String REFRESH_TOKEN ="/refresh";
}
