package com.filmreview.springbootproject.email;

public interface EmailSender {
    void send(String to, String email);
}

