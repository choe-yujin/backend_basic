package metaverse.valueobjct.chap01.section01;

import java.time.LocalDate;

public class Reservation {
    private String guestName;
    private String roomNumber;
    private int numberOfGuests;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalPrice;

    public Reservation(String guestName, String roomNumber, int numberOfGuests, LocalDate checkInDate, LocalDate checkOutDate, int totalPrice) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public int calculateNights() {
        // toEpochDay : 1970년 1월 1일 이후 경과한 날짜 수를 의미
        // 두 날짜 차이로 예약한 총 날짜수 계산하기 위함
        return (int) checkOutDate.toEpochDay() - (int) checkInDate.toEpochDay();
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "guestName='" + guestName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
