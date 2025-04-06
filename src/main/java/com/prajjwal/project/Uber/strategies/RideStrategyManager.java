package com.prajjwal.project.Uber.strategies;

import com.prajjwal.project.Uber.strategies.impl.DefaultRideFareCalculationStrategy;
import com.prajjwal.project.Uber.strategies.impl.HighestRatedDriverMatchingStrategy;
import com.prajjwal.project.Uber.strategies.impl.NearestDriverMatchingStrategy;
import com.prajjwal.project.Uber.strategies.impl.SurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final NearestDriverMatchingStrategy nearestDriverMatchingStrategy;
    private final HighestRatedDriverMatchingStrategy highestRatedDriverMatchingStrategy;
    private final DefaultRideFareCalculationStrategy defaultRideFareCalculationStrategy;
    private final SurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if(riderRating >= 4.8) {
            return highestRatedDriverMatchingStrategy;
        } else {
            return nearestDriverMatchingStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime) {
            return surgePricingFareCalculationStrategy;
        } else {
            return defaultRideFareCalculationStrategy;
        }
    }
}
