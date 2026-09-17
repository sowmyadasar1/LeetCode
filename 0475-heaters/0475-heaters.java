
class Solution {
    public int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(heaters);

        int radius = 0;

        for (int house : houses) {

            int index = Arrays.binarySearch(heaters, house);

            if (index >= 0) {
                // House itself has a heater
                continue;
            }

            // Insertion position
            index = -index - 1;

            int leftDistance = Integer.MAX_VALUE;
            int rightDistance = Integer.MAX_VALUE;

            // Heater to the left
            if (index > 0) {
                leftDistance = house - heaters[index - 1];
            }

            // Heater to the right
            if (index < heaters.length) {
                rightDistance = heaters[index] - house;
            }

            int nearest = Math.min(leftDistance, rightDistance);

            radius = Math.max(radius, nearest);
        }

        return radius;
    }
}