package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // used to block reflection-based multiple construction
    private static boolean constructed = false;

    // private constructor
    private MetricsRegistry() {
        if (constructed) {
            throw new RuntimeException("Use getInstance(), reflection not allowed");
        }
        constructed = true;
    }

    // ✅ Lazy + thread-safe (Initialization-on-demand holder)
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // ---- business data ----
    private final Map<String, Long> counters = new HashMap<>();

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // ✅ Serialization protection
    @Serial
    private Object readResolve() {
        return getInstance();
    }
}