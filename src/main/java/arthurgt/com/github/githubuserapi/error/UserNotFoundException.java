package arthurgt.com.github.githubuserapi.error;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String... searchParamsMap) {
        super(UserNotFoundException.generateMessage(toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(Map<String, String> searchParams) {
        return "Github user not found for name " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}