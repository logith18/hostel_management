# Custom Software Metrics

This directory implements the two project-specific metrics defined in Software Metrics Report 1.

## Metrics

### 1. Room Allocation Rule Density (RARD)

Formula:

`RARD = Number of room-allocation decision rules / LOC of RoomAllocationService`

The project defines seven distinct room-allocation business-policy rules:

1. Student exists
2. Student is hostel eligible
3. Student has no active allocation
4. Room exists
5. Room is available
6. Room has capacity
7. Preferred room type matches room type

If SonarQube provides a file-level LOC value for `RoomAllocationService.java`, that value should replace the fallback LOC used by this calculator. Otherwise, the calculator uses the project's documented convention: non-comment, non-blank physical lines.

### 2. Hostel Checkout Complexity Index (HCCI)

Formula:

`HCCI = Total checkout decision conditions / Total checkout operations`

The current `CheckoutService` contains eight distinct policy guards:

1. Student exists
2. Active allocation exists
3. Pending payment
4. Room damage
5. Lost key
6. Late checkout
7. Outstanding fine
8. Room clearance

The project defines five checkout workflow operations:

1. Student and allocation validation
2. Financial-clearance validation
3. Room/property-clearance validation
4. Bed release
5. Allocation closure

## Run

From the project root:

```text
javac custom-metrics/CustomMetricsCalculator.java
java -cp custom-metrics CustomMetricsCalculator
```

The calculator does not modify application source code. It produces the custom metric values separately so they can be reported alongside the native SonarQube metrics.
