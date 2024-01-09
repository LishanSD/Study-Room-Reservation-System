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

public class StudyRoomReservationSystem {
    private ArrayList<StudyRoom> studyRooms = new ArrayList<StudyRoom>();
    
    public synchronized void reserveStudyRoom(int roomNumber) throws StudyRoomUnavailableException {
        StudyRoom studyRoom = this.getStudyRoom(roomNumber);
        if (studyRoom.isAvailabilityStatus() == true) {
            studyRoom.setAvailabilityStatus(false);
        }
        else {
            throw new StudyRoomUnavailableException("Study room is already occupied.");
        }
    }

    public synchronized void releaseStudyRoom(int roomNumber) {
        StudyRoom studyRoom = this.getStudyRoom(roomNumber);
        studyRoom.setAvailabilityStatus(true);
    }

    public synchronized void displayStudyRoomStatus() {
        System.out.println("Study Room Status:");
        for (StudyRoom studyRoom : studyRooms) {
            if (studyRoom.isAvailabilityStatus() == true) {
                System.out.println("Room Number: " + studyRoom.getRoomNumber() + ", Capacity: " + studyRoom.getCapacity() + ", Availability: Available");
            }
            else {
                System.out.println("Room Number: " + studyRoom.getRoomNumber() + ", Capacity: " + studyRoom.getCapacity() + ", Availability: Not available");
            }
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
}
    

