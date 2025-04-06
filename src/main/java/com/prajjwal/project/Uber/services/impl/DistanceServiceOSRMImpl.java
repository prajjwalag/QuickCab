package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point source, Point destination) {
        return 0;
    }
}
