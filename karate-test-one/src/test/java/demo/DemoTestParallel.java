package demo;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pthomas3
 */
public class DemoTestParallel {

    @BeforeAll
    static void beforeAll() {
        TestBase.beforeAll();
    }

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:demo")
                .outputCucumberJson(true)
                .karateEnv("demo")
                .parallel(5);
        generateReport(results.getReportDir());
        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }

    /* public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    } */

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

        // ✅ Create a single merged file for Jenkins: target/cucumber.json
        File mergedOutput = new File("target/cucumber-html-reports/cucumber.json");
        try {
            FileUtils.write(mergedOutput, "", "UTF-8"); // clear file
            for (File file : jsonFiles) {
                String content = FileUtils.readFileToString(file, "UTF-8");

                // If files are individual feature JSONs (array-style), remove enclosing []
                content = content.trim();
                if (content.startsWith("[")) {
                    content = content.substring(1);
                }
                if (content.endsWith("]")) {
                    content = content.substring(0, content.length() - 1);
                }

                // Append with comma (to simulate array of objects)
                FileUtils.writeStringToFile(mergedOutput, content + ",\n", "UTF-8", true);
            }

            // Wrap final content in array brackets
            String mergedContent = FileUtils.readFileToString(mergedOutput, "UTF-8").trim();
            if (mergedContent.endsWith(",")) {
                mergedContent = mergedContent.substring(0, mergedContent.length() - 1);
            }
            mergedContent = "[" + mergedContent + "]";
            FileUtils.writeStringToFile(mergedOutput, mergedContent, "UTF-8", false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ This still generates the HTML report
        Configuration config = new Configuration(new File("target/cucumber-html-reports/"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }


}
