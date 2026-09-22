package com.hostel.service;

import com.hostel.dao.PaymentDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentServiceTest {

    @Test
    void recordsValidPayment() {
        PaymentService service = new PaymentService(new PaymentDAO());

        assertTrue(service.makePayment("S001", "P001", 1250.0, "HOSTEL_FEE"));
        assertEquals(1250.0, service.getTotalPaid("S001"));
    }

    @Test
    void rejectsBlankStudentInvalidAmountAndNonFiniteAmount() {
        PaymentService service = new PaymentService(new PaymentDAO());

        assertFalse(service.makePayment("", "P001", 100.0, "HOSTEL_FEE"));
        assertFalse(service.makePayment("S001", "P002", 0.0, "HOSTEL_FEE"));
        assertFalse(service.makePayment("S001", "P003", Double.NaN, "HOSTEL_FEE"));
        assertFalse(service.makePayment("S001", "P004", Double.POSITIVE_INFINITY, "HOSTEL_FEE"));
    }
}
