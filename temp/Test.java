package temp;

import structures.linkedlist.LinkedList;

public class Test {
    public static void main(String[] args) {
        Car car1 = new Car("toyota","corolla", 110);
        Car car2 = new Car("honda","cvic", 120);
        Car car3 = new Car("tesla","model s", 140);
        Car car4 = new Car("porsche","panamera", 220);
    
        LinkedList<Car> cars = new LinkedList<>();
        cars.add(car4);
        cars.add(car2);
        cars.add(car3);
        cars.add(car1);
        cars.display();

        Car gigaCar = cars.max();
    }
}
