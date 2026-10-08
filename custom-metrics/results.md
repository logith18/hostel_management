# Custom Metrics Results

## Project
Hostel Management System with Dynamic Room Allocation and Fine Calculation

## RARD — Room Allocation Rule Density

**Formula**

`RARD = Number of room-allocation decision rules / LOC of RoomAllocationService`

| Input | Value |
|---|---:|
| Room-allocation decision rules | 7 |
| RoomAllocationService LOC | 96 |
| RARD | **0.0729** |

The seven rules are student existence, hostel eligibility, active-allocation check, room existence, room availability, room capacity, and preferred room-type matching.

> LOC note: 66 is the fallback non-comment, non-blank physical-line count. If SonarQube provides a file-level LOC measure for `RoomAllocationService.java`, use that value and recalculate RARD so the LOC convention remains consistent with the project's baseline register.

## HCCI — Hostel Checkout Complexity Index

**Formula**

`HCCI = Total checkout decision conditions / Total checkout operations`

| Input | Value |
|---|---:|
| Checkout decision conditions | 8 |
| Checkout workflow operations | 5 |
| HCCI | **1.60** |

The eight conditions are student existence, active allocation, pending payment, room damage, lost key, late checkout, outstanding fine, and room clearance.

The five workflow operations are student/allocation validation, financial-clearance validation, room/property-clearance validation, bed release, and allocation closure.

## Current result summary

| Custom metric | Result |
|---|---:|
| RARD | **0.1061** |
| HCCI | **1.60** |
