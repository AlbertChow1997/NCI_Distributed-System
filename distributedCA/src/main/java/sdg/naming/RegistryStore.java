package sdg.naming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// This class is used to store the registered services in memory.
// The idea is simple because this project is only for CA demo, so database is not necessary here.

public class RegistryStore {

    // One entry means one running service endpoint.
    public static class Entry {
        public final String host;
        public final int port;
        public final String version;
        public final Map<String,String> metadata;
        public final long registeredAt;

        public Entry(String host, int port, String version, Map<String,String> metadata, long registeredAt) {
            this.host = host;
            this.port = port;
            this.version = version;
            this.metadata = metadata;
            this.registeredAt = registeredAt;
        }
    }

    private final Map<String, List<Entry>> services = new ConcurrentHashMap<>();

    public void register(String name, Entry entry) {
        services.compute(name, (k, list) -> {
            if (list == null) list = new ArrayList<>();
            list.removeIf(e -> e.host.equals(entry.host) && e.port == entry.port);
            list.add(entry);
            return list;
        });
    }

    public List<Entry> discover(String name) {
        return services.getOrDefault(name, List.of());
    }
}
