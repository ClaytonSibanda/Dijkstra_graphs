/**
 * @author  Clayton Sibanda
 * @version 1.0
 * @since   2017-05-21
 */
public class Hospital {
    private int number_of_beds;
    private  String name;


    private String groupName;

    public Hospital(int number_of_beds, String name,String groupName) {
        this.number_of_beds = number_of_beds;
        this.name = name;
        this.groupName =groupName;
    }

    /**
     * setter method
     * @param name, name of the hospital
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method
     * @param groupName, name of the group to which the hospital/ambulance belongs to
     *
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * getter method
     * returns the number of beds in the hospital
     *
     */
    public int getNumber_of_beds() {
        return number_of_beds;
    }


    /**
     * getter method
     * returns the name of the hospital
     *
     */
    public String getName() {
        return name;
    }




    /**
     * getter method
     * returns the group name of the hospital
     *
     */
    public String getGroupName() {
        return groupName;
    }


}
