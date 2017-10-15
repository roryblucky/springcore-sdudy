package com.rory.springcore.study.io;

import java.net.URL;

public class ResourceLoader {
    public Resource gerResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
