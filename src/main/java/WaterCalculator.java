import exception.LandscapeValidationException;

public class WaterCalculator {

    public long calculateWaterAmount(int[] landscape) {
        validateLandscape(landscape);
        long collectedWater = 0;
        final int size = landscape.length;
        int[] maxLeftToRight = new int[size];
        int[] maxRightToLeft = new int[size];

        int leftMaximum = 0;

        for (int i = 0; i < size; i++) {
            if (landscape[i] > leftMaximum) {
                maxLeftToRight[i] = landscape[i];
                leftMaximum = landscape[i];
            } else {
                maxLeftToRight[i] = leftMaximum;
            }
        }

        int rightMaximum = 0;

        for (int j = size - 1; j >= 0; j--) {
            if (landscape[j] > rightMaximum) {
                maxRightToLeft[j] = landscape[j];
                rightMaximum = landscape[j];
            } else {
                maxRightToLeft[j] = rightMaximum;
            }
        }

        for (int k = 0; k < size; k++) {
            int possibleWaterLevel = Math.min(maxLeftToRight[k], maxRightToLeft[k]);
            if (landscape[k] < possibleWaterLevel) {
                collectedWater += possibleWaterLevel - landscape[k];
            }
        }

        return collectedWater;
    }

    private void validateLandscape(int[] landscape) {
        if(landscape == null || landscape.length == 0) {
            throw new LandscapeValidationException("Landscape is empty!");
        }
        if(landscape.length > 32000) {
            throw new LandscapeValidationException("Landscape positions number is greater than 32000!");
        }
        for(int unit : landscape) {
            if(unit > 32000) {
                throw new LandscapeValidationException("Landscape height is greater than 32000!");
            } else if (unit < 0) {
                throw new LandscapeValidationException("Landscape height is less than 0!");
            }
        }
    }
}
