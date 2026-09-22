package com.hostel.dao;

import com.hostel.model.Allocation;

import java.util.ArrayList;
import java.util.List;

public class AllocationDAO {

    private final List<Allocation> allocations = new ArrayList<>();

    public void addAllocation(Allocation allocation) {
        allocations.add(allocation);
    }

    public Allocation findByStudentId(String studentId) {

        for (Allocation allocation : allocations) {

            if (allocation.getStudentId().equals(studentId)
                    && allocation.getStatus().equals("ACTIVE")) {

                return allocation;
            }
        }

        return null;
    }

    public Allocation findById(String allocationId) {

        for (Allocation allocation : allocations) {

            if (allocation.getAllocationId().equals(allocationId)) {
                return allocation;
            }
        }

        return null;
    }

    public List<Allocation> getAllAllocations() {
        return allocations;
    }
}