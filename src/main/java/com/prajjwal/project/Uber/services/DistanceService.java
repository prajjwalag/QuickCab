package com.prajjwal.project.Uber.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

    double calculateDistance(Point source, Point destination);
}
