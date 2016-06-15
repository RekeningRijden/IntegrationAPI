/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Position;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author maikel
 */
public class PositionHelper {

    /**
     * Creates a longitude in portugal
     * @return a longitude as double
     */
    public static Double createLongitude() {
        Random r = new Random();
        Double longitude = (r.nextDouble() * -180.0) + 90;
        boolean longitudeBool = true;
        while (longitudeBool) {
            if (longitude < Constants.EAST_BORDER && longitude > Constants.WEST_BORDER) {
                longitudeBool = false;
            } else {
                longitude = (r.nextDouble() * -180.0) + 90;
            }
        }
        return longitude;
    }

    /**
     * Creates a latitude in portugal
     * @return a latitude as double
     */
    public static Double createLatitude() {
        Random r = new Random();
        Double latitude = (r.nextDouble() * -360.0) + 180.0;
        boolean latidudeBool = true;
        while (latidudeBool) {
            if (latitude < Constants.NORTH_BORDER && latitude > Constants.SOUTH_BORDER) {
                latidudeBool = false;
            } else {
                latitude = (r.nextDouble() * -360.0) + 180.0;
            }
        }
        return latitude;
    }

    /**
     * Creates random positions in portugal
     * @return a list with random positions in portugal
     */
    public static List<Position> createPositions() {
        List<Position> positions = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < r.nextInt(100) + 5; i++) {
            positions.add(new Position(new Date(), createLongitude(), createLatitude()));
        }
        return positions;
    }

}
