package com.hostel.ui;

import com.hostel.dao.AllocationDAO;
import com.hostel.dao.ComplaintDAO;
import com.hostel.dao.FineDAO;
import com.hostel.dao.PaymentDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.dao.StudentDAO;

import com.hostel.model.Allocation;
import com.hostel.model.Complaint;
import com.hostel.model.Fine;
import com.hostel.model.Payment;
import com.hostel.model.Room;
import com.hostel.model.Student;

import com.hostel.service.CheckoutService;
import com.hostel.service.ComplaintService;
import com.hostel.service.FineCalculationEngine;
import com.hostel.service.PaymentService;
import com.hostel.service.RoomAllocationService;
import com.hostel.service.RoomService;
import com.hostel.service.RoomTransferService;
import com.hostel.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class HostelManagementUI {

    private static final Scanner scanner = new Scanner(System.in);

    // DAOs
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final AllocationDAO allocationDAO = new AllocationDAO();
    private static final PaymentDAO paymentDAO = new PaymentDAO();
    private static final ComplaintDAO complaintDAO = new ComplaintDAO();
    private static final FineDAO fineDAO = new FineDAO();

    // Services
    private static final StudentService studentService =
            new StudentService(studentDAO);

    private static final RoomService roomService =
            new RoomService(roomDAO);

    private static final RoomAllocationService allocationService =
            new RoomAllocationService(
                    studentDAO,
                    roomDAO,
                    allocationDAO
            );

    private static final RoomTransferService transferService =
            new RoomTransferService(
                    allocationDAO,
                    roomDAO
            );

    private static final FineCalculationEngine fineEngine =
            new FineCalculationEngine(fineDAO);

    private static final CheckoutService checkoutService =
            new CheckoutService(
                    studentDAO,
                    roomDAO,
                    allocationDAO,
                    fineEngine
            );

    private static final PaymentService paymentService =
            new PaymentService(paymentDAO);

    private static final ComplaintService complaintService =
            new ComplaintService(complaintDAO);

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("       HOSTEL MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    addRoom();
                    break;

                case 3:
                    viewStudents();
                    break;

                case 4:
                    viewRooms();
                    break;

                case 5:
                    allocateRoom();
                    break;

                case 6:
                    transferRoom();
                    break;

                case 7:
                    calculateFine();
                    break;

                case 8:
                    makePayment();
                    break;

                case 9:
                    registerComplaint();
                    break;

                case 10:
                    resolveComplaint();
                    break;

                case 11:
                    checkoutStudent();
                    break;

                case 12:
                    viewAllocations();
                    break;

                case 13:
                    viewStudentPayments();
                    break;

                case 14:
                    markFinePaid();
                    break;

                case 15:
                    System.out.println("Exiting system...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("              MAIN MENU");
        System.out.println("----------------------------------------------");

        System.out.println("1.  Register Student");
        System.out.println("2.  Add Room");
        System.out.println("3.  View Students");
        System.out.println("4.  View Rooms");
        System.out.println("5.  Allocate Room");
        System.out.println("6.  Transfer Room");
        System.out.println("7.  Calculate Fine");
        System.out.println("8.  Make Payment");
        System.out.println("9.  Register Complaint");
        System.out.println("10. Resolve Complaint");
        System.out.println("11. Checkout Student");
        System.out.println("12. View Allocations");
        System.out.println("13. View Student Payments");
        System.out.println("14. Mark Fine as Paid");
        System.out.println("15. Exit");

        System.out.println("----------------------------------------------");
    }

    private static void registerStudent() {

        System.out.println("\n===== STUDENT REGISTRATION =====");

        String id = readString("Student ID: ");
        String name = readString("Name: ");
        String department = readString("Department: ");
        int year = readInt("Year: ");

        boolean eligible =
                readYesNo("Hostel eligible? (yes/no): ");

        String category =
                readString("Category: ");

        String roomType =
                readString("Preferred room type (AC/NON-AC): ");

        Student student = new Student(
                id,
                name,
                department,
                year,
                eligible,
                category,
                roomType
        );

        boolean success =
                studentService.registerStudent(student);

        if (success) {
            System.out.println("Student registered successfully.");
        } else {
            System.out.println(
                    "Student registration failed. Check ID or details."
            );
        }
    }

    private static void addRoom() {

        System.out.println("\n===== ADD ROOM =====");

        String roomNumber =
                readString("Room number: ");

        String roomType =
                readString("Room type (AC/NON-AC): ");

        int capacity =
                readInt("Capacity: ");

        Room room =
                new Room(
                        roomNumber,
                        roomType,
                        capacity
                );

        boolean success =
                roomService.addRoom(room);

        if (success) {
            System.out.println("Room added successfully.");
        } else {
            System.out.println("Room could not be added.");
        }
    }

    private static void viewStudents() {

        System.out.println("\n===== ALL STUDENTS =====");

        List<Student> students =
                studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void viewRooms() {

        System.out.println("\n===== ALL ROOMS =====");

        List<Room> rooms =
                roomService.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private static void allocateRoom() {

        System.out.println("\n===== ROOM ALLOCATION =====");

        String studentId =
                readString("Student ID: ");

        String roomNumber =
                readString("Room number: ");

        Allocation allocation =
                allocationService.allocateRoom(
                        studentId,
                        roomNumber
                );

        if (allocation != null) {
            System.out.println(allocation);
        }
    }

    private static void transferRoom() {

        System.out.println("\n===== ROOM TRANSFER =====");

        String studentId =
                readString("Student ID: ");

        String newRoom =
                readString("New room number: ");

        transferService.transferRoom(
                studentId,
                newRoom
        );
    }

    private static void calculateFine() {

        System.out.println("\n===== FINE CALCULATION =====");

        String studentId =
                readString("Student ID: ");

        String fineId =
                readString("Fine ID: ");

        String violation =
                readString(
                        "Violation type " +
                        "(LATE_CHECKOUT/LOST_KEY/ROOM_DAMAGE/PENDING_PAYMENT): "
                );

        int severity =
                readInt("Severity (1-3): ");

        Fine fine =
                fineEngine.calculateFine(
                        studentId,
                        fineId,
                        violation,
                        severity
                );

        System.out.println(fine);
    }

    private static void makePayment() {

        System.out.println("\n===== PAYMENT =====");

        String studentId =
                readString("Student ID: ");

        String paymentId =
                readString("Payment ID: ");

        double amount =
                readDouble("Amount: ");

        String paymentType =
                readString("Payment type: ");

        paymentService.makePayment(
                studentId,
                paymentId,
                amount,
                paymentType
        );
    }

    private static void registerComplaint() {

        System.out.println("\n===== REGISTER COMPLAINT =====");

        String complaintId =
                readString("Complaint ID: ");

        String studentId =
                readString("Student ID: ");

        String description =
                readString("Description: ");

        Complaint complaint =
                complaintService.registerComplaint(
                        complaintId,
                        studentId,
                        description
                );

        if (complaint != null) {
            System.out.println(complaint);
        }
    }

    private static void resolveComplaint() {

        System.out.println("\n===== RESOLVE COMPLAINT =====");

        String complaintId =
                readString("Complaint ID: ");

        complaintService.resolveComplaint(
                complaintId
        );
    }

    private static void checkoutStudent() {

        System.out.println("\n===== STUDENT CHECKOUT =====");

        String studentId =
                readString("Student ID: ");

        boolean pendingPayment =
                readYesNo("Pending payment? (yes/no): ");

        boolean roomDamage =
                readYesNo("Room damage? (yes/no): ");

        boolean lostKey =
                readYesNo("Lost key? (yes/no): ");

        boolean lateCheckout =
                readYesNo("Late checkout? (yes/no): ");

        boolean roomClearance =
                readYesNo("Room clearance completed? (yes/no): ");

        checkoutService.checkoutStudent(
                studentId,
                pendingPayment,
                roomDamage,
                lostKey,
                lateCheckout,
                roomClearance
        );
    }

    private static void viewAllocations() {

        System.out.println("\n===== ALL ALLOCATIONS =====");

        List<Allocation> allocations =
                allocationService.getAllAllocations();

        if (allocations.isEmpty()) {
            System.out.println("No allocations found.");
            return;
        }

        for (Allocation allocation : allocations) {
            System.out.println(allocation);
        }
    }

    private static void viewStudentPayments() {

        System.out.println("\n===== STUDENT PAYMENTS =====");

        String studentId =
                readString("Student ID: ");

        List<Payment> payments =
                paymentService.getPayments(studentId);

        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        for (Payment payment : payments) {
            System.out.println(payment);
        }

        System.out.println(
                "Total paid: Rs. " +
                paymentService.getTotalPaid(studentId)
        );
    }

    private static void markFinePaid() {

        System.out.println("\n===== MARK FINE AS PAID =====");

        String fineId =
                readString("Fine ID: ");

        fineEngine.markFineAsPaid(fineId);
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }

    private static boolean readYesNo(String message) {

        while (true) {

            String answer =
                    readString(message);

            if (answer.equalsIgnoreCase("yes")
                    || answer.equalsIgnoreCase("y")) {

                return true;

            } else if (answer.equalsIgnoreCase("no")
                    || answer.equalsIgnoreCase("n")) {

                return false;

            } else {

                System.out.println(
                        "Please enter yes or no."
                );
            }
        }
    }
}