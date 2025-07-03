package demo.ticket_rest_controller.util;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class JsonModifier {
    /**
     * Updates a specific attribute inside the "fields" array and also top-level fields if present.
     *
     * @param json        the input JSON map
     * @param attribute   the name of the attribute to update (e.g., "ticket_subject")
     * @param newValue    the new value to set
     * @return            the modified JSON map
     */
    public static Map<String, Object> updateField(Map<String, Object> json, String attribute, String newValue) {
        // Update in "fields" array
        List<Map<String, Object>> fields = (List<Map<String, Object>>) json.get("fields");
        if (fields != null) {
            for (Map<String, Object> field : fields) {
                if (attribute.equals(field.get("attribute"))) {
                    field.put("value", newValue);
                    field.put("valueLabel", newValue);
                    break;
                }
            }
        }

        // Update top-level field if exists
        if (json.containsKey(attribute)) {
            json.put(attribute, newValue);
        }

        return json;
    }

    // ✅ Updates only the subject field (both top-level and inside 'fields' array)
    public static Map<String, Object> updateSubject(Map<String, Object> json) {
        String subject = generateRandomSubject();

        // Update top-level subject
        json.put("subject", subject);

        // Update in fields array
        if (json.containsKey("fields")) {
            List<Map<String, Object>> fields = (List<Map<String, Object>>) json.get("fields");
            for (Map<String, Object> field : fields) {
                if ("ticket_subject".equals(field.get("attribute"))) {
                    field.put("value", subject);
                    field.put("valueLabel", subject);
                    break;
                }
            }
        }

        return json;
    }

    // ✅ Random subject generator: "Test ticket <random number>"
    public static String generateRandomSubject() {
        int randomNum = new Random().nextInt(90000) + 10000;
        return "Test ticket " + randomNum;
    }
}
