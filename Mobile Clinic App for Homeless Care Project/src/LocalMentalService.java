package src;

public class LocalMentalService {
    private String name;
    private Location localMentalServiceLocation;

    public LocalMentalService(Location _location, String _name) {
        localMentalServiceLocation = _location;
        name = _name;
    }

    public Location getLocalMentalServiceLocation() {
        return localMentalServiceLocation;
    }

    public void setLocalMentalServiceLocation(Location localMentalServiceLocation) {
        this.localMentalServiceLocation = localMentalServiceLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
