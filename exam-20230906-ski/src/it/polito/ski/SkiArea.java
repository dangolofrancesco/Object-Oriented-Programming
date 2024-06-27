package it.polito.ski;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SkiArea {

	private String nameArena;
	private Map<String, Type> types;
	private Map<String, Lift> lifts;
	private Map<String, Slope> slopes;
	private Map<String, Parking> parkings;

	/**
	 * Creates a new ski area
	 * @param name name of the new ski area
	 */
	public SkiArea(String name) {
		this.nameArena = name;
		types = new TreeMap<>();
		lifts = new TreeMap<>();
		slopes = new TreeMap<>();
		parkings = new TreeMap<>();
    }

	/**
	 * Retrieves the name of the ski area
	 * @return name
	 */
	public String getName() { return this.nameArena; }

    /**
     * define a new lift type providing the code, the category (Cable Cabin, Chair, Ski-lift)
     * and the capacity (number of skiers carried) of a single unit
     * 
     * @param code		name of the new type
     * @param category	category of the lift
     * @param capacity	number of skiers per unit
     * @throws InvalidLiftException in case of duplicate code or if the capacity is <= 0
     */
    public void liftType(String code, String category, int capacity) throws InvalidLiftException {
		Type lift = new Type(code, category, capacity);
		if (types.containsKey(code)) throw new InvalidLiftException();

		types.put(code, lift);
    }
    
    /**
     * retrieves the category of a given lift type code
     * @param typeCode lift type code
     * @return the category of the type
     * @throws InvalidLiftException if the code has not been defined
     */
    public String getCategory(String typeCode) throws InvalidLiftException {
		if (!types.containsKey(typeCode)) throw new InvalidLiftException("Invalid type");
		return types.get(typeCode).getCategory();
    }

    /**
     * retrieves the capacity of a given lift type code
     * @param typeCode lift type code
     * @return the capacity of the type
     * @throws InvalidLiftException if the code has not been defined
     */
    public int getCapacity(String typeCode) throws InvalidLiftException {
		if (!types.containsKey(typeCode)) throw new InvalidLiftException("Invalid type");
        return types.get(typeCode).getCapacity();
    }


    /**
     * retrieves the list of lift types
     * @return the list of codes
     */
	public Collection<String> types() {
		return types.keySet();
	}
	
	/**
	 * Creates new lift with given name and type
	 * 
	 * @param name		name of the new lift
	 * @param typeCode	type of the lift
	 * @throws InvalidLiftException in case the lift type is not defined
	 */
    public void createLift(String name, String typeCode) throws InvalidLiftException{
		if (!types.containsKey(typeCode)) throw new InvalidLiftException("Invalid type");
	
		Lift lift= new Lift(name, types.get(typeCode));
		lifts.put(name, lift);

    }
    
	/**
	 * Retrieves the type of the given lift
	 * @param lift 	name of the lift
	 * @return type of the lift
	 */
	public String getType(String lift) {
		return lifts.get(lift).getType().getCode();
	}

	/**
	 * retrieves the list of lifts defined in the ski area
	 * @return the list of names sorted alphabetically
	 */
	public List<String> getTypes(){
		List<String> liftNames = new ArrayList<>(lifts.keySet());
		return liftNames;
    }

	/**
	 * create a new slope with a given name, difficulty and a starting lift
	 * @param name			name of the slope
	 * @param difficulty	difficulty
	 * @param lift			the starting lift for the slope
	 * @throws InvalidLiftException in case the lift has not been defined
	 */
    public void createSlope(String name, String difficulty, String lift) throws InvalidLiftException {
		Lift l = lifts.get(lift);
		if (l==null) throw new InvalidLiftException();

		Slope slope = new Slope(name, difficulty, l);
		slopes.put(name, slope);
		l.addSlope(name);
    }
    
    /**
     * retrieves the name of the slope
     * @param slopeName name of the slope
     * @return difficulty
     */
	public String getDifficulty(String slopeName) {
		return slopes.get(slopeName).getDifficulty();
	}

	/**
	 * retrieves the start lift
	 * @param slopeName name of the slope
	 * @return starting lift
	 */
	public String getStartLift(String slopeName) {
		return slopes.get(slopeName).getLift().getName();
	}

	/**
	 * retrieves the list of defined slopes
	 * 
	 * @return list of slopes
	 */
    public Collection<String> getSlopes(){
		return slopes.keySet();
    }

    /**
     * Retrieves the list of slopes starting from a given lift
     * 
     * @param lift the starting lift
     * @return the list of slopes
     */
    public Collection<String> getSlopesFrom(String lift){
		return lifts.get(lift).getSlopes();
    }

    /**
     * Create a new parking with a given number of slots
     * @param name 	new parking name
     * @param slots	slots available in the parking
     */
    public void createParking(String name, int slots){
		Parking parking = new Parking(name, slots);
		parkings.put(name, parking);
    }

    /**
     * Retrieves the number of parking slots available in a given parking
     * @param parking	parking name
     * @return number of slots
     */
	public int getParkingSlots(String parking) {
		return parkings.get(parking).getSlots();
	}

	/**
	 * Define a lift as served by a given parking
	 * @param lift		lift name
	 * @param parking	parking name
	 */
	public void liftServedByParking(String lift, String parking) {
		Lift l = lifts.get(lift);
		parkings.get(parking).addLift(l);
	}

	
	/**
	 * Retrieves the list of lifts served by a parking.
	 * @param parking	parking name
	 * @return the list of lifts
	 */
	public Collection<String> servedLifts(String parking) {
		return parkings.get(parking).getLifts();
	}

	/**
	 * Checks whether the parking is proportional to the capacity of the lift it is serving.
	 * A parking is considered proportionate if its size divided by the sum of the capacity of the lifts 
	 * served by the parking is less than 30.
	 * 
	 * @param parkingName name of the parking to check
	 * @return true if the parking is proportionate
	 */
	public boolean isParkingProportionate(String parkingName) {
		return parkings.get(parkingName).isProportionate();
	}

	/**
	 * reads the description of lift types and lift descriptions from a text file. 
	 * The contains a description per line. 
	 * Each line starts with a letter indicating the kind of information: "T" stands for Lift Type, 
	 * while "L" stands for Lift.
	 * A lift type is described by code, category and seat number. 
	 * A lift is described by the name and the lift type.
	 * Different data on a line are separated by ";" and possible spaces surrounding the separator are ignored.
	 * If a line contains the wrong number of information it should be skipped and
	 * the method should continue reading the following lines. 
	 * 
	 * @param path 	the path of the file
	 * @throws IOException	in case IO error
	 * @throws InvalidLiftException in case of duplicate type or non-existent lift type
	 */
    /* public void readLifts(String path) throws IOException, InvalidLiftException {

		Reader reader = new FileReader(path);
		try ( BufferedReader r = new BufferedReader(reader)){
			String line;
			while ( (line = r.readLine()) != null){
				if (addData(line))
			}
		}
    } */

	/* public boolean addData(String line) throws IOException, InvalidLiftException{
		String[] res = line.split("\\s*;\\s*");
		boolean ok = res.length>0;
		if (ok && res[0].equals("T")){
			if ( res.length == 4){
				if (!types.containsKey(res[1])) throw new InvalidLiftException("Invalid Type");
				liftType(res[1], res[2], Integer.parseInt(res[3]));
			}else{
				ok = false;
			}
		}else if( ok && res[0].equals("L")){
			if ( res.length == 3){
				if (!types.containsKey(res[2])) throw new InvalidLiftException("Invalid Type");
				createLift(res[1], res[2]);
			}else{
				ok = false;
			}
			
		}
		return ok;
	} */

	public void readLifts(String path) throws IOException, InvalidLiftException {

		Reader reader = new FileReader(path);
		try ( BufferedReader r = new BufferedReader(reader)){
			String line;
			while ( (line = r.readLine()) != null){
				String[] res = line.split(";");
				String kind = res[0].toUpperCase().trim();
				String name;
				String cat;
				int capacity;
				String type;
				switch (kind) {
					case "T":
						if ( res.length != 4) continue;
						name = res[1].trim();
						cat = res[2].trim();
						capacity = Integer.parseInt(res[3].trim());
						this.liftType(name, cat, capacity);
						break;
					case "L":
						if ( res.length != 3) continue;
						name = res[1].trim();
						type = res[2].trim();
						this.createLift(name, type);
						break;
					default:
						System.out.println("Unknown line type: " + line);
				}
			}
		}catch(IOException e){
			throw e;
		}
		
    }
	

}
