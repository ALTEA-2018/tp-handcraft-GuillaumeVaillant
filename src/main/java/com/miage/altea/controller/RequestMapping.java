package com.miage.altea.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Les annotations nous permettent de se debarasser du fichier Web.xml
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    // uri à écouter
    String uri();
}