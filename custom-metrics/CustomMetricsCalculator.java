import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Calculates the two project-specific software metrics defined for the
 * Hostel Management System: RARD and HCCI.
 *
 * Counting conventions are intentionally aligned with docs/baseline-metrics.md
 * and the Software Metrics Report 1 specification.
 */
public class CustomMetricsCalculator {

    // RARD: seven distinct room-allocation business-policy decisions.
    private static final int ROOM_ALLOCATION_RULES = 7;

    // HCCI: distinct policy guards present in the current CheckoutService.
    private static final int CHECKOUT_DECISION_CONDITIONS = 8;

    // HCCI workflow stages defined by the project documentation.
    private static final int CHECKOUT_OPERATIONS = 5;

    private static final Path ROOM_ALLOCATION_SERVICE = Path.of(
            "src", "main", "java", "com", "hostel", "service",
            "RoomAllocationService.java");

    public static void main(String[] args) throws IOException {
        int roomAllocationLoc = countNonCommentNonBlankLines(ROOM_ALLOCATION_SERVICE);

        double rard = (double) ROOM_ALLOCATION_RULES / roomAllocationLoc;
        double hcci = (double) CHECKOUT_DECISION_CONDITIONS / CHECKOUT_OPERATIONS;

        System.out.println("========================================");
        System.out.println("HOSTEL MANAGEMENT SYSTEM");
        System.out.println("CUSTOM SOFTWARE METRICS");
        System.out.println("========================================");
        System.out.println();

        System.out.println("Room Allocation Rule Density (RARD)");
        System.out.println("----------------------------------------");
        System.out.println("Allocation decision rules : " + ROOM_ALLOCATION_RULES);
        System.out.println("RoomAllocationService LOC : " + roomAllocationLoc);
        System.out.printf("RARD = %d / %d = %.4f%n",
                ROOM_ALLOCATION_RULES, roomAllocationLoc, rard);
        System.out.println();

        System.out.println("Hostel Checkout Complexity Index (HCCI)");
        System.out.println("----------------------------------------");
        System.out.println("Checkout decision conditions : " + CHECKOUT_DECISION_CONDITIONS);
        System.out.println("Checkout workflow operations : " + CHECKOUT_OPERATIONS);
        System.out.printf("HCCI = %d / %d = %.2f%n",
                CHECKOUT_DECISION_CONDITIONS, CHECKOUT_OPERATIONS, hcci);
        System.out.println();

        System.out.println("RARD interpretation: higher values indicate more allocation rules");
        System.out.println("concentrated within a relatively small amount of code.");
        System.out.println("HCCI interpretation: higher values indicate more checkout");
        System.out.println("decision-making conditions per checkout workflow operation.");
    }

    /**
     * Counts physical source lines after excluding blank lines and comments.
     * This is the fallback LOC convention specified in the project register
     * when a file-level SonarQube LOC value is unavailable.
     */
    private static int countNonCommentNonBlankLines(Path source) throws IOException {
        List<String> lines = Files.readAllLines(source);
        boolean inBlockComment = false;
        int count = 0;

        for (String line : lines) {
            String trimmed = line.trim();

            if (inBlockComment) {
                if (trimmed.contains("*/")) {
                    inBlockComment = false;
                }
                continue;
            }

            if (trimmed.isEmpty()) {
                continue;
            }

            if (trimmed.startsWith("/*")) {
                if (!trimmed.contains("*/") || trimmed.indexOf("*/") < trimmed.indexOf("/*")) {
                    inBlockComment = true;
                }
                continue;
            }

            if (trimmed.startsWith("//")) {
                continue;
            }

            count++;
        }

        return count;
    }
}
