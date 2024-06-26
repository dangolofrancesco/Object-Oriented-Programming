package it.polito.library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Comparator;


public class LibraryManager {

	Map<String, Book> titles;
	Map<String, List<Book>> books;
	Map<String, Reader> readers;

	int bookId = 1000;
	int readerId = 1000;

	public LibraryManager(){
		titles = new TreeMap<>();
		books = new TreeMap<>();
		readers = new TreeMap<>();
	}
	    
    // R1: Readers and Books 
    
    /**
	 * adds a book to the library archive
	 * The method can be invoked multiple times.
	 * If a book with the same title is already present,
	 * it increases the number of copies available for the book
	 * 
	 * @param title the title of the added book
	 * @return the ID of the book added 
	 */
    public String addBook(String title) {
		String id = Integer.toString(bookId);
		bookId++;
		Book book = new Book(id, title);
		books.computeIfAbsent(title, b -> new ArrayList<>()).add(book);
		titles.put(id, book);
        return book.getId();
    }
    
    /**
	 * Returns the book titles available in the library
	 * sorted alphabetically, each one linked to the
	 * number of copies available for that title.
	 * 
	 * @return a map of the titles liked to the number of available copies
	 */
    public SortedMap<String, Integer> getTitles() {    	
		SortedMap<String, Integer> bookCopies = new TreeMap<>();
		for (Map.Entry<String, List<Book>> e : books.entrySet()){
			bookCopies.put(e.getKey(), e.getValue().size());
		}
        return bookCopies;
    }
    
    /**
	 * Returns the books available in the library
	 * 
	 * @return a set of the titles liked to the number of available copies
	 */
    public Set<String> getBooks() {    
		Set<String> booksAvailable = new HashSet<>();
		for ( List<Book> list : books.values()){
			for ( Book b : list){
				booksAvailable.add(b.getId());
			}
		}	    	
        return booksAvailable;
    }
    
    /**
	 * Adds a new reader
	 * 
	 * @param name first name of the reader
	 * @param surname last name of the reader
	 */
    public void addReader(String name, String surname) {
		Reader reader = new Reader(readerId++, name, surname);
		readers.put(reader.getId(), reader);
    }
    
    
    /**
	 * Returns the reader name associated to a unique reader ID
	 * 
	 * @param readerID the unique reader ID
	 * @return the reader name
	 * @throws LibException if the readerID is not present in the archive
	 */
    public String getReaderName(String readerID) throws LibException {
		if ( !readers.containsKey(readerID)) throw new LibException();
        return readers.get(readerID).toString();
    }    
    
    
    // R2: Rentals Management
    
    
    /**
	 * Retrieves the bookID of a copy of a book if available
	 * 
	 * @param bookTitle the title of the book
	 * @return the unique book ID of a copy of the book or the message "Not available"
	 * @throws LibException  an exception if the book is not present in the archive
	 */
    public String getAvailableBook(String bookTitle) throws LibException {
		if (!books.containsKey(bookTitle)) throw new LibException();
        return  books.get(bookTitle).stream()
		.filter(b -> !b.isRent())
		.map(Book::getId)
		.findFirst().orElse("Not available");
    }   

    /**
	 * Starts a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param startingDate the starting date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is already renting a book, or if the book copy is already rented
	 */
	public void startRental(String bookID, String readerID, String startingDate) throws LibException {
		if ( !getBooks().contains(bookID) || !readers.containsKey(readerID)) throw new LibException();

		if ( titles.get(bookID).isRent() || readers.get(readerID).isRenting()) throw new LibException();

		readers.get(readerID).startRental(bookID, startingDate);
		titles.get(bookID).startRental(readerID, startingDate);
    }
    
	/**
	 * Ends a rental of a specific book copy for a specific reader
	 * 
	 * @param bookID the unique book ID of the book copy
	 * @param readerID the unique reader ID of the reader
	 * @param endingDate the ending date of the rental
	 * @throws LibException  an exception if the book copy or the reader are not present in the archive,
	 * if the reader is not renting a book, or if the book copy is not rented
	 */
    public void endRental(String bookID, String readerID, String endingDate) throws LibException {
		if ( !getBooks().contains(bookID) || !readers.containsKey(readerID)) throw new LibException();

		if ( !titles.get(bookID).isRent() || !readers.get(readerID).isRenting()) throw new LibException();

		readers.get(readerID).endRental(bookID, endingDate);
		titles.get(bookID).endRental(readerID, endingDate);
	}
    
    
   /**
	* Retrieves the list of readers that rented a specific book.
	* It takes a unique book ID as input, and returns the readers' reader IDs and the starting and ending dates of each rental
	* 
	* @param bookID the unique book ID of the book copy
	* @return the map linking reader IDs with rentals starting and ending dates
	* @throws LibException  an exception if the book copy or the reader are not present in the archive,
	* if the reader is not renting a book, or if the book copy is not rented
	*/
    public SortedMap<String, String> getRentals(String bookID) throws LibException {
		if (!titles.containsKey(bookID)) throw new LibException();
		SortedMap<String, String> rentals = new TreeMap<>(titles.get(bookID).getRentings());
        return rentals;
    }
    
    
    // R3: Book Donations
    
    /**
	* Collects books donated to the library.
	* 
	* @param donatedTitles It takes in input book titles in the format "First title,Second title"
	*/
    public void receiveDonation(String donatedTitles) {
		String[] donations = donatedTitles.split(",");
		for ( String b : donations){
			addBook(b);
		}
    }
    
    // R4: Archive Management

    /**
	* Retrieves all the active rentals.
	* 
	* @return the map linking reader IDs with their active rentals

	*/
    public Map<String, String> getOngoingRentals() {

		Map<String, String> ongoingRentings = new TreeMap<>();
		for ( Reader r : readers.values()){
			if (r.isRenting()){
				for (Map.Entry<String, String> bookRented : r.getRentals().entrySet()){
					String status = bookRented.getValue().split(" ")[1];
					if ( status.equals("ONGOING")){
						ongoingRentings.put(r.getId(), bookRented.getKey());
					}
				}
			}
		}
        return ongoingRentings;
    }
    
    /**
	* Removes from the archives all book copies, independently of the title, that were never rented.
	* 
	*/
    public void removeBooks() {

		List<Book> booksToRemove = new ArrayList<>();
		for ( List<Book> list : books.values()){
			for ( Book b : list){
				if ( b.getRentings().isEmpty()){
					booksToRemove.add(b);
					titles.remove(b.getId());
				}
			}
		}	

		for ( Book b : booksToRemove){
			books.get(b.getTitle()).remove(b);
		}
    }
    	
    // R5: Stats
    
    /**
	* Finds the reader with the highest number of rentals
	* and returns their unique ID.
	* 
	* @return the uniqueID of the reader with the highest number of rentals
	*/
    public String findBookWorm() {
        Reader r =  readers.values().stream()
		.sorted(Comparator.comparing(Reader::getNumberOfRents).reversed())
		.findFirst().get();

		return r.getId();

    }
    
    /**
	* Returns the total number of rentals by title. 
	* 
	* @return the map linking a title with the number of rentals
	*/
    public Map<String,Integer> rentalCounts() {

		Map<String, Integer> numberOfRentals = new TreeMap<>();

		for ( Book b : titles.values() ){
			if (numberOfRentals.containsKey(b.getTitle())){
				int n = numberOfRentals.get(b.getTitle());
				numberOfRentals.put(b.getTitle(), n+b.getNumberOfRents());
			}else{
				numberOfRentals.put(b.getTitle(), b.getNumberOfRents());
			}

		}
        return numberOfRentals;
    }
}
