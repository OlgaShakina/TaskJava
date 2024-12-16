import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static void main(String[] args) {
            if (args.length != 3) {
                System.out.println("report.json");
                return;
            }

        String testsFilePath = "/Users/olgabuinova/IdeaProjects/TaskJava/task3/src/main/java/tests-edf09d69ff.docx";
        String valuesFilePath = "/Users/olgabuinova/IdeaProjects/TaskJava/task3/src/main/java/values-c44d5c50d1.docx";
        String reportFilePath = "/Users/olgabuinova/IdeaProjects/TaskJava/task3/src/main/java/report.json";

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode testsRoot = mapper.readTree(new File(testsFilePath));
            JsonNode valuesRoot = mapper.readTree(new File(valuesFilePath));

            Map<String, String> valuesMap = createValuesMap(valuesRoot);

            fillValues(testsRoot, valuesMap);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsRoot);

            System.out.println("Отчет сгенерирован: " + reportFilePath);

        } catch (IOException e) {
            System.out.println("Ошибка при обработке файлов: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static Map<String, String> createValuesMap(JsonNode valuesRoot) {
        Map<String, String> valuesMap = new HashMap<>();

        if (valuesRoot.isObject()) {
            valuesRoot.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                String value = entry.getValue().asText();
                valuesMap.put(key, value);
            });
        }

        return valuesMap;
    }

    private static void fillValues(JsonNode node, Map<String, String> valuesMap) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            if (objectNode.has("id")) {
                String id = objectNode.get("id").asText();
                if (valuesMap.containsKey(id)) {
                    objectNode.put("value", valuesMap.get(id));
                }
            }

            if (objectNode.has("tests")) {
                fillValues(objectNode.get("tests"), valuesMap);
            }
        } else if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            for (JsonNode child : arrayNode) {
                fillValues(child, valuesMap);
            }
        }
    }
}
