package br.edu.infnet.hanielapi.config;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {

    private final AtomicLong generator = new AtomicLong(1);

    public Long getNextId() {
        return generator.getAndIncrement();
    }
}