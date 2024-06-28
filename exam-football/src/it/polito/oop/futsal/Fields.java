package it.polito.oop.futsal;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {
	    
    private int fieldId = 1;
    private Map<Integer, Field> fields;
    private LocalTime open;
    private LocalTime close;
    private int associateId = 1;
    private Map<Integer, Associate> associates;

    public Fields(){
        this.fields = new TreeMap<>();
        this.associates = new TreeMap<>();
        this.associates = new TreeMap<>();
    }

    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }
    }
    
    public void defineFields(Features... features) throws FutsalException {
        for ( Features f : features){
            if (!f.indoor && (f.heating || f.ac)) throw new FutsalException("Invalid feature");
            Field field = new Field( f, fieldId++);
            fields.put(field.getField(), field);
        }

    }
    
    public long countFields() {
        return fields.size();
    }

    public long countIndoor() {
        return fields.values().stream()
        .filter(Field::isIndoor)
        .count();
    }
    
    public String getOpeningTime() {
        return this.open.toString();
    }
    
    public void setOpeningTime(String time) {
        open = LocalTime.parse(time);
    }
    
    public String getClosingTime() {
        return this.close.toString();
    }
    
    public void setClosingTime(String time) {
        close = LocalTime.parse(time);
    }

    public int newAssociate(String first, String last, String mobile) {
        Associate associate = new Associate(associateId++, first, last, mobile);
        associates.put(associate.getId(), associate);

        return associate.getId();
    }
    
    public String getFirst(int associate) throws FutsalException {
        if (!associates.containsKey(associate)) throw new FutsalException("Invalid Associate");
        return associates.get(associate).getName();
    }
    
    public String getLast(int associate) throws FutsalException {
        if (!associates.containsKey(associate)) throw new FutsalException("Invalid Associate");
        return associates.get(associate).getSurname();
    }
    
    public String getPhone(int associate) throws FutsalException {
        if (!associates.containsKey(associate)) throw new FutsalException("Invalid Associate");
        return associates.get(associate).getMobile();
    }
    
    public int countAssociates() {
        return associates.size();
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
        if (!fields.containsKey(field)) throw new FutsalException("Invalid Field");
        if (!associates.containsKey(associate)) throw new FutsalException("Invalid Associate");

        LocalTime start = LocalTime.parse(time);
        if ( start.isBefore(open) || start.isAfter(close)) throw new FutsalException("Invalid time");
        
        long difference = open.until(start, ChronoUnit.MINUTES);
        if ( (difference % 60) != 0 ) throw new FutsalException("Invalid time");

        Booking booking = new Booking(field, associate, time);
        fields.get(field).addBooking(booking);

        associates.get(associate).addBooking();
    }

    public boolean isBooked(int field, String time) {
        return fields.get(field).isBooked(time);
    }
    

    public int getOccupation(int field) {
        return fields.get(field).getOccupation();
    }
    
    public List<FieldOption> findOptions(String time, Features required){
    
        return fields.values().stream()
        .filter( f -> !f.isBooked(time))
        .filter( f -> f.match(required))
        .sorted(Comparator.comparing(Field::getOccupation).reversed()
        .thenComparing(Field::getField))
        .collect(Collectors.toList());
    }
    
    public long countServedAssociates() {
        return associates.values().stream()
        .filter(a -> a.getNumOfBookings() > 0)
        .count();
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return fields.values().stream()
        .collect(Collectors.toMap(Field::getField, f -> (long) f.getOccupation()));
    }
    
    public double occupation() {

        int totOcc = 0;
        long blocks = open.until(close, ChronoUnit.HOURS);

        for ( int f : fields.keySet()){
            totOcc = totOcc + getOccupation(f);
        }
        return totOcc/((double)blocks*fields.size());
    }
    
 }
