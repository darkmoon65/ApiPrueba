package com.cesars.api.services.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class BinaryOutputWrapper {
    private HttpHeaders headers;
    private byte[] data;

    public BinaryOutputWrapper(String contentType) {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    }

    public void setFilename(String filename) {
        headers.setContentDispositionFormData(filename, filename);
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
