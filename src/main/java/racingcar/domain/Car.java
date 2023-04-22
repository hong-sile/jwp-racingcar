package racingcar.domain;

public class Car {
    private static final int DISTANCE_DEFAULT = 0;
    private static final int MOVE_CRITERIA = 4;

    private final Name name;
    private final Distance distance;

    Car(String name, int distance) {
        this.name = new Name(name);
        this.distance = new Distance(distance);
    }

    public Car(String name) {
        this(name, DISTANCE_DEFAULT);
    }

    public void runForward(int engine) {
        if (engine >= MOVE_CRITERIA) {
            distance.increaseDistance();
        }
    }

    public Distance getDistance() {
        return distance;
    }

    public Name getName() {
        return name;
    }

    public boolean isSameDistance(int otherDistance) {
        return otherDistance == distance.getValue();
    }
}
