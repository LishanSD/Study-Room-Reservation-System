import java.util.*;

class StudyRoom {
    private int roomNumber;
    private int capacity;
    private boolean availabilityStatus;

    public StudyRoom(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.availabilityStatus = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}

class StudyRoomUnavailableException extends RuntimeException {
    public StudyRoomUnavailableException() {
    }

    public StudyRoomUnavailableException(String message) {
        super(message);
    }
}

public synchronized void addStudyRoom(StudyRoom studyRoom) {
    studyRooms.add(studyRoom);
}

public synchronized StudyRoom getStudyRoom(int roomNumber) {
    for (StudyRoom studyRoom : studyRooms) {
        if (studyRoom.getRoomNumber() == roomNumber) {
            return studyRoom;
        }
    }
    return null;
}

public class Main {
    public static void main(String[] args) {
        // Create StudyRoom objects
        StudyRoom room1 = new StudyRoom(1, 4);
        StudyRoom room2 = new StudyRoom(2, 6);
        StudyRoom room3 = new StudyRoom(3, 8);

        // Create StudyRoomReservationSystem
        StudyRoomReservationSystem reservationSystem = new StudyRoomReservationSystem();

        // Add study rooms to the reservation system
        reservationSystem.addStudyRoom(room1);
        reservationSystem.addStudyRoom(room2);
        reservationSystem.addStudyRoom(room3);

        // Display initial study room status
        reservationSystem.displayStudyRoomStatus();

        // Test Case 1: Single student reserving an available study room
        try {
            reservationSystem.reserveStudyRoom(1);
            // System.out.println("Student 1 reserved Study Room 1.");
        } catch (StudyRoomUnavailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}