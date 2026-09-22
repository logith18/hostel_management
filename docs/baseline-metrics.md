# Baseline Metrics Register

This file records only measurements produced from the `v1.0-baseline` checkpoint. Blank entries mean the required analysis has not yet been run.

| Metric | Baseline | Source and convention |
|---|---:|---|
| Lines of Code |  | SonarQube source-line measure after analysis |
| Cyclomatic Complexity |  | SonarQube after analysis |
| Cognitive Complexity |  | SonarQube after analysis |
| Technical Debt Ratio |  | SonarQube after analysis |
| Maintainability |  | SonarQube maintainability rating after analysis |
| Code Smells |  | SonarQube after analysis |
| Duplicated Lines |  | SonarQube after analysis |
| Bugs |  | SonarQube after analysis |
| Vulnerabilities |  | SonarQube after analysis |
| Security Hotspots |  | SonarQube after analysis |
| Test Coverage | 32.50% line coverage | JaCoCo XML report from `mvn verify` on 2026-09-22: 222 covered of 683 lines |
| RARD |  | Defined below; calculate after final LOC convention is available |
| HCCI |  | Defined below; do not calculate until workflow operations are enumerated |

## RARD counting convention

RARD is the number of distinct room-allocation business-policy decisions divided by the LOC of `RoomAllocationService`.

For the baseline, one rule is counted once even when its implementation includes multiple boolean expressions. The seven currently documented rules are:

1. The student exists.
2. The student is hostel eligible.
3. The student has no active allocation.
4. The room exists.
5. The room is available.
6. The room has capacity.
7. The preferred room type matches the room type.

The numerator is therefore 7 for this checkpoint. The denominator must use SonarQube's source-line measurement for `RoomAllocationService` when that analysis is run. If SonarQube does not provide a file-level LOC value, use non-comment, non-blank physical lines in that class and record the command and result here. Do not mix LOC conventions between versions.

## HCCI counting convention

HCCI is the total number of distinct checkout decision conditions divided by the total number of checkout operations.

For both baseline and refactored versions, a checkout operation means one named workflow stage that can change or conclusively validate checkout state: (1) student and allocation validation, (2) financial-clearance validation, (3) room/property-clearance validation, (4) bed release, and (5) allocation closure. A decision condition is a distinct policy guard evaluated by the checkout workflow; conditions that merely form one policy guard are counted once.

The baseline method currently combines these stages and must be reviewed against this definition before HCCI is entered. HCCI is deliberately blank until the actual condition count and stage evidence are recorded.

## Expected analysis focus

- `RoomAllocationService`: rule density and decision complexity.
- `FineCalculationEngine`: violation and severity branching.
- `CheckoutService`: guard density and HCCI evidence.
- `HostelManagementUI`: centralisation, coupling, and responsibility.
