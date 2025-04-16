package mission.h_collection;

import mission.h_collection.model.Book;
import mission.h_collection.model.LibraryMember;
import mission.h_collection.service.RentalManager;

public class Application {
    public static void main(String[] args) {
        LibraryMember libraryMember = new LibraryMember("Yujin", "LM001");
        RentalManager rentalManager = new RentalManager();
        Book book1 = new Book("Clean code", "Robert C. Martin", 45.0);
        Book book2 = new Book("Effective Java", "Joshua", 55.0);
        String membershipId = libraryMember.getMembershipId();
        rentalManager.registerMember(membershipId);
        rentalManager.rentBook(membershipId, book1);
        rentalManager.rentBook(membershipId, book2);
        String rentalList = rentalManager.getRentalList(membershipId);
        System.out.println(rentalList);
    }
}
