import java.util.Scanner;

class FatTreeImplementation {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int k = init(userInput);
        menuInput(userInput, k);
    }

    /**
     * Logic to take input k from user and evaluate ids for POD, PM, Edge,
     * Aggrication & Core.
     * 
     * @param userInput
     * @return
     */
    public static int init(Scanner userInput) {
        System.out.println("Enter input k:");
        int k = userInput.nextInt();
        System.out.println();
        while (k < 1 || k % 2 != 0) {
            System.out.println("Input is not a valid even number for k");
            System.out.println("Enter input k:");
            k = userInput.nextInt();
        }

        int counter = 0;

        if (k != 2) {
            System.out.print("POD Ids -> ");
            for (int i = 0; i < k; i++) {
                System.out.print(i + ", ");
            }
            System.out.println();

            System.out.print("PM Ids -> ");
            for (int i = 0; i < (int) Math.pow(k, 3) / 4; i++) {
                counter = i;
                System.out.print(counter + ", ");
            }
            System.out.println();

            System.out.print("Edge Ids -> ");
            for (int i = (int) Math.pow(k, 3) / 4; i < (((int) Math.pow(k, 3) / 4) + ((int) Math.pow(k, 2) / 2)); i++) {
                counter = i;
                System.out.print(counter + ", ");
            }
            System.out.println();

            System.out.print("Aggregation Ids -> ");
            for (int i = (((int) Math.pow(k, 3) / 4) + ((int) Math.pow(k, 2) / 2)); i < (((int) Math.pow(k, 3) / 4)
                    + ((int) Math.pow(k, 2))); i++) {
                counter = i;
                System.out.print(counter + ", ");
            }
            System.out.println();

            System.out.print("Core Ids -> ");
            for (int i = (((int) Math.pow(k, 3) / 4) + ((int) Math.pow(k, 2))); i < (((int) Math.pow(k, 3) / 4)
                    + ((int) Math.pow(k, 2)) + ((int) Math.pow(k, 2) / 4)); i++) {
                counter = i;
                System.out.print(counter + ", ");
            }
            System.out.println();
        } else {
            System.out.println("POD Ids -> 0");
            System.out.println("PM Ids -> 0, 1");
            System.out.println("Edge Ids -> 2");
            System.out.println("Aggregation Ids -> 2");
            System.out.println("Core Ids -> 2");
            counter = 4;
        }

        System.out.println("Total node count -> " + (counter + 1));
        System.out.println();
        return k;
    }

    /**
     * Logic for display menu
     * 
     * @param userInput
     * @param kInput
     */
    public static void menuInput(Scanner userInput, int kInput) {
        int k = kInput;
        boolean shouldLoop = true;
        while (shouldLoop) {
            System.out.println("############## MENU ##############");
            System.out.println("Please enter: ");
            System.out.println("(1): To find distance for k, " + k);
            System.out.println("(2): To enter different value for k");
            System.out.println("(0): To Exit");
            System.out.println("##################################");
            int inputMenu = userInput.nextInt();
            if (inputMenu == 1) {
                System.out.println("Enter source node Id: ");
                int sourceNode = userInput.nextInt();
                System.out.println("Enter destination node Id: ");
                int destinationNode = userInput.nextInt();
                int distance = findDistance(k, sourceNode, destinationNode);
                System.out.println("-------------------------------");
                System.out.println("The distance from " + sourceNode + " to " + destinationNode + " is: " + distance);
                System.out.println("-------------------------------");
            } else if (inputMenu == 2) {
                k = init(userInput);
            } else if (inputMenu == 0) {
                System.out.println("EXITING...");
                shouldLoop = false;
                break;
            } else {
                System.out.println("Enter valid input");
            }
        }
    }

    /**
     * Buisness logic to calculate distance between the @sourceNode
     * & @destinationNode for give k
     * 
     * @param k
     * @param sourceNode
     * @param destinationNode
     * @return distance between source and destination node
     */
    public static int findDistance(int k, int sourceNode, int destinationNode) {

        int distance = 0;
        int firstPmId = 0;
        int lastPmId = 7;
        int firstEdgeId = 8;
        int lastEdgeId = 11;
        int firstAggrigationId = 12;
        int lastAggrigationId = 15;
        int firstCoreId = 16;
        int lastCoreId = 17;

        if (k != 2) {
            firstPmId = 0;
            lastPmId = (int) (Math.pow(k, 3) / 4 - 1);
            firstEdgeId = (int) (Math.pow(k, 3) / 4);
            lastEdgeId = (int) ((Math.pow(k, 3) / 4) + (Math.pow(k, 2) / 2) - 1);
            firstAggrigationId = (int) ((Math.pow(k, 3) / 4) + (Math.pow(k, 2) / 2));
            lastAggrigationId = (int) ((Math.pow(k, 3) / 4) + (Math.pow(k, 2)) - 1);
            firstCoreId = (int) ((Math.pow(k, 3) / 4) + (Math.pow(k, 2)));
            lastCoreId = (int) ((Math.pow(k, 3) / 4) + (Math.pow(k, 2)) + (Math.pow(k, 2) / 4) - 1);
        }

        if (sourceNode >= firstPmId && sourceNode <= lastPmId && destinationNode >= firstPmId
                && destinationNode <= lastPmId) {
            // If both nodes are PMs
            if (k == 2) {
                distance = 2;
                return distance;
            } else 
            if (sourceNode / ((int) (Math.pow(k, 2) / 4)) == destinationNode / ((int) (Math.pow(k, 2) / 4))) {
                if (((int) (sourceNode / (k / 2))) == ((int) (destinationNode / (k / 2)))) {
                    distance = 2;
                } else {
                    distance = 4;
                }
            } else {
                distance = 6;
            }
            return distance;
        } else if (sourceNode >= firstEdgeId && sourceNode <= lastEdgeId && destinationNode >= firstEdgeId
                && destinationNode <= lastEdgeId) {
            // If both nodes are edge
            if ((int) (((sourceNode - firstEdgeId)
                    / ((Math.pow(k, 2) / 4) / (k / 2)))) == (int) (((destinationNode - firstEdgeId)
                            / ((Math.pow(k, 2) / 4) / (k / 2))))) {
                distance = 2;
            } else {
                distance = 4;
            }
            return distance;
        } else if (sourceNode >= firstAggrigationId && sourceNode <= lastAggrigationId
                && destinationNode >= firstAggrigationId && destinationNode <= lastAggrigationId) {
            // If both nodes are aggrigation
            if (((int) ((sourceNode - firstAggrigationId)
                    / ((Math.pow(k, 2) / 4) / (k / 2)))) == (int) ((destinationNode - firstAggrigationId)
                            / ((Math.pow(k, 2) / 4) / (k / 2)))) {
                distance = 2;
            } else {
                if ((int) ((sourceNode - firstAggrigationId) % (k / 2)) == (int) ((destinationNode - firstAggrigationId)
                        % (k / 2))) {
                    distance = 2;
                } else {
                    distance = 4;
                }
            }
            return distance;
        } else if (sourceNode >= firstCoreId && sourceNode <= lastCoreId && destinationNode >= firstCoreId
                && destinationNode <= lastCoreId) {
            // If both nodes are core
            if (k == 2) {
                distance = 2;
                return distance;
            }
            if ((int) ((sourceNode - firstCoreId)
                    / ((Math.pow(k, 2) / 4) / (k / 2))) == (int) ((destinationNode - firstCoreId)
                            / ((Math.pow(k, 2) / 4) / (k / 2)))) {
                distance = 2;
            } else {
                distance = 4;
            }
            return distance;
        } else if ((sourceNode >= firstPmId && sourceNode <= lastPmId && destinationNode >= firstEdgeId
                && destinationNode <= lastEdgeId)
                || (destinationNode >= firstPmId && destinationNode <= lastPmId && sourceNode >= firstEdgeId
                        && sourceNode <= lastEdgeId)) {
            // If nodes are pm & edge
            if (sourceNode > destinationNode) {
                int temp = destinationNode;
                destinationNode = sourceNode;
                sourceNode = temp;
            }
            if (k == 2) {
                distance = 1;
                return distance;
            }
            if ((int) (sourceNode / (Math.pow(k, 2) / 4)) == (int) ((destinationNode - firstEdgeId)
                    / ((Math.pow(k, 2) / 4) / (k / 2)))) {
                if ((int) (sourceNode % (k / 2)) == (int) ((destinationNode - firstEdgeId) % (k / 2))) {
                    distance = 1;
                } else {
                    distance = 3;
                }
            } else {
                distance = 5;
            }
            return distance;
        } else if ((sourceNode >= firstPmId && sourceNode <= lastPmId && destinationNode >= firstAggrigationId
                && destinationNode <= lastAggrigationId)
                || (destinationNode >= firstPmId && destinationNode <= lastPmId && sourceNode >= firstAggrigationId
                        && sourceNode <= lastAggrigationId)) {
            // If nodes are pm & aggrigation
            if (sourceNode > destinationNode) {
                int temp = destinationNode;
                destinationNode = sourceNode;
                sourceNode = temp;
            }
            if (k == 2) {
                distance = 1;
                return distance;
            }
            if ((int) (sourceNode / (Math.pow(k, 2) / 4)) == (int) ((destinationNode - firstAggrigationId)
                    / ((Math.pow(k, 2) / 4) / (k / 2)))) {
                distance = 2;
            } else {
                distance = 4;
            }
            return distance;
        } else if ((sourceNode >= firstPmId && sourceNode <= lastPmId && destinationNode >= firstCoreId
                && destinationNode <= lastCoreId)
                || (destinationNode >= firstPmId && destinationNode <= lastPmId && sourceNode >= firstCoreId
                        && sourceNode <= lastCoreId)) {
            // If nodes are pm & core
            distance = 3;
            return distance;
        } else if ((sourceNode >= firstEdgeId && sourceNode <= lastEdgeId && destinationNode >= firstAggrigationId
                && destinationNode <= lastAggrigationId)
                || (destinationNode >= firstEdgeId && destinationNode <= lastEdgeId && sourceNode >= firstAggrigationId
                        && sourceNode <= lastAggrigationId)) {
            // If nodes are edge & aggrigation
            if (sourceNode > destinationNode) {
                int temp = destinationNode;
                destinationNode = sourceNode;
                sourceNode = temp;
            }
            if ((int) ((sourceNode - firstEdgeId)
                    / ((Math.pow(k, 2) / 4) / (k / 2))) == (int) ((destinationNode - firstAggrigationId)
                            / ((Math.pow(k, 2) / 4) / (k / 2)))) {
                distance = 1;
            } else {
                distance = 3;
            }
            return distance;
        } else if ((sourceNode >= firstEdgeId && sourceNode <= lastEdgeId && destinationNode >= firstCoreId
                && destinationNode <= lastCoreId)
                || (destinationNode >= firstEdgeId && destinationNode <= lastEdgeId && sourceNode >= firstCoreId
                        && sourceNode <= lastCoreId)) {
            // If nodes are edge & core
            distance = 2;
            return distance;
        } else if ((sourceNode >= firstAggrigationId && sourceNode <= lastAggrigationId
                && destinationNode >= firstCoreId && destinationNode <= lastCoreId)
                || (destinationNode >= firstAggrigationId && destinationNode <= lastAggrigationId
                        && sourceNode >= firstCoreId && sourceNode <= lastCoreId)) {
            // If nodes are aggrigation & core
            if (sourceNode > destinationNode) {
                int temp = destinationNode;
                destinationNode = sourceNode;
                sourceNode = temp;
            }
            if (k == 2) {
                distance = 1;
                return distance;
            }
            if ((int) ((sourceNode - firstAggrigationId) % (k / 2)) == (int) ((destinationNode - firstCoreId)
                    / ((Math.pow(k, 2) / 4) / (k / 2)))) {
                distance = 1;
            } else {
                distance = 3;
            }
            return distance;
        } else {
            distance = 0;
            return distance;
        }
    }
}