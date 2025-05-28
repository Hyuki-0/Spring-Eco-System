package com.hyuki.tobi_spring.api;

import java.io.IOException;
import java.net.URI;

@FunctionalInterface
public interface ApiExecutor {
  String executeApi(URI uri) throws IOException;
}
