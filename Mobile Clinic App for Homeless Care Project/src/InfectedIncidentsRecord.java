package src;

public class InfectedIncidentsRecord implements Comparable<InfectedIncidentsRecord> {
    private Location infectedIncidentsLocation;
    private int infectedIncidentscount;

    public InfectedIncidentsRecord(Location _location) {
        this.infectedIncidentsLocation = _location;
        this.infectedIncidentscount = 0;
    }

    public Location getInfectedIncidentsLocation() {
        return infectedIncidentsLocation;
    }

    public int getInfectedIncidentscount() {
        return infectedIncidentscount;
    }

    public void incrementInfectedIncidentscount() {
        infectedIncidentscount++;
    }

    @Override
    public int compareTo(InfectedIncidentsRecord other) {
        int res = this.infectedIncidentscount - other.infectedIncidentscount;

        if (res >= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
