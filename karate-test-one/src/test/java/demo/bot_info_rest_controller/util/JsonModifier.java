package demo.bot_info_rest_controller.util;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class JsonModifier {
    /**
     * Updates a specific attribute inside the "fields" array and also top-level fields if present.
     *
     * @param json        the input JSON map
     * @param parentKey   the name of the attribute to update (e.g., "ticket_subject")
     * @param newValue    the new value to set
     * @return            the modified JSON map
     */
    // ✅ Update any field inside a named nested object like "createFaq"
    public static Map<String, Object> updateNestedField(Map<String, Object> json, String parentKey, String fieldKey, String newValue) {
        if (json.containsKey(parentKey)) {
            Object nested = json.get(parentKey);
            if (nested instanceof Map) {
                Map<String, Object> nestedMap = (Map<String, Object>) nested;
                if (nestedMap.containsKey(fieldKey)) {
                    nestedMap.put(fieldKey, newValue);
                }
            }
        }
        return json;
    }

    // ✅ Generate random name like: "FAQ-Test-12345"
    public static String generateRandomFaqName() {
        int randomNum = new Random().nextInt(90000) + 10000;
        return "FAQ-Test-" + randomNum;
    }

    // ✅ Combine both: update "name" inside "createFaq" with random value
    public static Map<String, Object> updateFaqName(Map<String, Object> json) {
        String randomName = generateRandomFaqName();
        return updateNestedField(json, "createFaq", "name", randomName);
    }
}
