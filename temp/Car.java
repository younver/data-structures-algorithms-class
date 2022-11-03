package temp;

public class Car implements Comparable {
    private String make;
    private String model;
    private int horsePower;

    public Car(String make, String model, int horsePower){
        this.make = make;
        this.model = model;
        this.horsePower = horsePower;
    }

    @Override
    public int compareTo(Object o) {
        Car other = (Car) o;

        if (this.horsePower == other.horsePower)
            return 0;
        else if (this.horsePower > other.horsePower){
            return 1;
        }

        return -1;
    }

    public String toString(){
        return this.make + " " + this.model + " " + this.horsePower; 
    }
}