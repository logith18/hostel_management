package com.hostel.service;

import com.hostel.dao.FineDAO;
import com.hostel.model.Fine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FineCalculationEngineTest {

    @Test
    void calculatesConfiguredViolationAmounts() {
        FineCalculationEngine engine = new FineCalculationEngine(new FineDAO());

        assertEquals(500.0, engine.calculateFine("S001", "F001", "LATE_CHECKOUT", 1).getAmount());
        assertEquals(200.0, engine.calculateFine("S001", "F002", "LOST_KEY", 1).getAmount());
        assertEquals(300.0, engine.calculateFine("S001", "F003", "PENDING_PAYMENT", 1).getAmount());
    }

    @Test
    void appliesRoomDamageSeverityBranches() {
        FineCalculationEngine engine = new FineCalculationEngine(new FineDAO());

        Fine low = engine.calculateFine("S001", "F004", "ROOM_DAMAGE", 1);
        Fine medium = engine.calculateFine("S001", "F005", "ROOM_DAMAGE", 2);
        Fine high = engine.calculateFine("S001", "F006", "ROOM_DAMAGE", 3);

        assertEquals(1000.0, low.getAmount());
        assertEquals(2500.0, medium.getAmount());
        assertEquals(5000.0, high.getAmount());
    }
}
