# Hostel Management System Software Metrics Baseline

This repository preserves an existing Java console application as a controlled software-metrics experiment. The `v1.0-original` tag is the untouched uploaded source. The current `v1.0-baseline` checkpoint adds Maven, representative tests, coverage reporting, and metric documentation without a design refactor.

## Architecture

The application uses the report's layered architecture:

- `model` contains Student, Room, Allocation, Payment, Complaint, and Fine entities.
- `dao` stores the entities in in-memory Java collections.
- `service` contains the existing allocation, transfer, fine, payment, complaint, and checkout logic.
- `ui` provides the console application.

The intentionally centralised UI, string status values, direct DAO collection exposure, duplicated validation, magic monetary values, and decision-heavy service methods are baseline characteristics to measure before the later refactoring phase.

## Requirements

- Java 21
- Maven 3.9 or later

## Build and run

```powershell
mvn clean compile
mvn exec:java
```

## Tests and coverage

```powershell
mvn test
mvn verify
```

`mvn verify` writes the JaCoCo HTML report to `target/site/jacoco/index.html` and the machine-readable report to `target/site/jacoco/jacoco.xml`.

## SonarQube or SonarCloud

Set the project-specific host URL, token, organization (SonarCloud only), and project key outside source control, then run:

```powershell
mvn clean verify sonar:sonar `
  -Dsonar.projectKey=YOUR_PROJECT_KEY `
  -Dsonar.organization=YOUR_ORGANIZATION `
  -Dsonar.host.url=https://sonarcloud.io `
  -Dsonar.token=YOUR_TOKEN `
  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml `
  -Dsonar.exclusions=target/**
```

For a self-hosted SonarQube server, omit `-Dsonar.organization` and use its URL. No Sonar analysis result is claimed in this repository until a configured server actually runs the analysis.

## Baseline and refactored methodology

1. `v1.0-original` is the exact uploaded project before conversion.
2. `v1.0-baseline` is the Maven-enabled functional baseline. Measure it first.
3. Identify findings in the Room Allocation, Fine Calculation, Checkout, and UI modules.
4. Create a later refactored version only after baseline results are reviewed.
5. Re-run the same tests, coverage process, Sonar analysis, RARD, and HCCI conventions, then compare actual results.

See `docs/baseline-metrics.md` for the metrics table and custom-metric counting conventions.
