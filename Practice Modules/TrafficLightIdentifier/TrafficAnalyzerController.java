package TrafficLightIdentifier;

import java.util.*;

public class TrafficAnalyzerController {

    ArrayList<TrafficAnalyzerRobot> robots = new ArrayList<>();

    //! Method to add a single robot into the internal holder
    public void addRobot(TrafficAnalyzerRobot robot) {
        robots.add(robot);
    }
    //! Elipsis notation method to add multiple robots into the internal holder
    public void addRobots(TrafficAnalyzerRobot... robots) {
        for (TrafficAnalyzerRobot robot : robots) {
            addRobot(robot);
        }
    }
    //! Method to add a collection of robots directly into the internal holder
    public void addRobots(Collection<TrafficAnalyzerRobot> robots) {
        this.robots.addAll(robots);
    }

    //! Method to retrieve the internal holder of robots
    public ArrayList<TrafficAnalyzerRobot> getRobots() {
        return robots;
    }

    //! Method to analyze a single String[] sequence of traffic light indications
    public static TrafficSequenceResult  analyzeTrafficSequence(String[] sequence)
            throws IllegalArgumentException {

        if (sequence == null || sequence.length < 3) {
            throw new IllegalArgumentException("Error Code 0x0001 - [Raised] Sequence must have at " +
                    "least a start marker, one signal, and an end marker.");
        }

        Stack<String> internalHeap = new Stack<>();
        //! Base check, we will loop over the entire list if and only if the first character
        //! is the # start sequence character
        if (!Objects.equals(sequence[0], TrafficAnalyzerRobot.START_ANALYSIS_MARKER)) {
            throw new IllegalArgumentException(String.format("Error Code 0x0001 - [Raised] The given Sequence [%s] did not " +
                            "meet the basic requirement of having a start character #. Therefore analysis throws an error.",
                    Arrays.toString(sequence)));
        }
        else
        {
            //! We now push onto the stack the first character, no matter what it was
            internalHeap.push(sequence[1]);
            for(int i = 1; i < sequence.length - 1; i++)
            {

                if (sequence[i+1].equals(TrafficAnalyzerRobot.END_ANALYSIS_MARKER))
                {
                    return new TrafficSequenceResult(
                            true,
                            internalHeap.peek().equals(TrafficAnalyzerRobot.RED_LIGHT_MARKER),
                            internalHeap.peek().equals(TrafficAnalyzerRobot.YELLOW_LIGHT_MARKER),
                            internalHeap.peek().equals(TrafficAnalyzerRobot.GREEN_LIGHT_MARKER),
                            false,
                            true,
                            Arrays.toString(sequence)
                    );
                }
                //? Check if the second character follows the sequence green, yellow, red
              analyzeRedLightTransitions(i, sequence, internalHeap);
              analyzeGreenLightTransitions(i, sequence, internalHeap);
              analyzeYellowLightTransitions(i, sequence, internalHeap);
            }
            throw new IllegalArgumentException("Error Code 0x0001 - [Raised] Sequence did not end with an end marker.");
        }
    }

    private static void analyzeRedLightTransitions(int indexAtCheck, String[] sequence,
                                                   Stack<String> internalHeap) {
        if (internalHeap.peek().equals(TrafficAnalyzerRobot.RED_LIGHT_MARKER)) {
            if (sequence[indexAtCheck + 1].equals(TrafficAnalyzerRobot.GREEN_LIGHT_MARKER)) {
                internalHeap.pop();
                internalHeap.push(sequence[indexAtCheck + 1]);
            } else if (sequence[indexAtCheck + 1].equals(TrafficAnalyzerRobot.YELLOW_LIGHT_MARKER)) {
                throw new IllegalArgumentException("Error Code 0x0001 - [Raised] Switching from red " +
                        "directly to yellow is disallowed at index " + (indexAtCheck) + ".");
            }
        }
    }
    private static void analyzeGreenLightTransitions(int indexAtCheck, String[] sequence,
                                                     Stack<String> internalHeap) {
        if (internalHeap.peek().equals(TrafficAnalyzerRobot.GREEN_LIGHT_MARKER)) {
            if (sequence[indexAtCheck + 1].equals(TrafficAnalyzerRobot.YELLOW_LIGHT_MARKER)) {
                internalHeap.pop();
                internalHeap.push(sequence[indexAtCheck + 1]);
            } else if (sequence[indexAtCheck + 1].equals(TrafficAnalyzerRobot.RED_LIGHT_MARKER)) {
                throw new IllegalArgumentException("Error Code 0x0001 - [Raised] Switching from green " +
                        "directly to red is disallowed at index " + (indexAtCheck) + ".");
            }
        }
    }
    private static void analyzeYellowLightTransitions(int indexAtCheck, String[] sequence,
                                                      Stack<String> internalHeap) {
        if (internalHeap.peek().equals(TrafficAnalyzerRobot.YELLOW_LIGHT_MARKER)) {
            if (sequence[indexAtCheck + 1].equals(TrafficAnalyzerRobot.RED_LIGHT_MARKER)) {
                internalHeap.pop();
                internalHeap.push(sequence[indexAtCheck + 1]);
            } else if (sequence[indexAtCheck + 1].equals(TrafficAnalyzerRobot.GREEN_LIGHT_MARKER)) {
                throw new IllegalArgumentException("Error Code 0x0001 - [Raised] Switching from yellow " +
                        "directly to green is disallowed at index " + (indexAtCheck) + ".");
            }
        }
    }


    public static void main(String[] args) {
        TrafficAnalyzerRobot robot = TrafficAnalyzerRobot.create("Robot1");
        TrafficAnalyzerController controller = new TrafficAnalyzerController();
        controller.addRobot(robot);
        System.out.println(String.format("%120s","Traffic Sequence Analyzer Input Analysis"));
        ArrayList<String[]> sequences = new ArrayList<>();

        // Correct sequences
        sequences.add(new String[]{"#", "R", "G", "Y", "R", "*"});
        sequences.add(new String[]{"#", "R", "G", "Y", "R", "G", "Y", "R", "*"});
        sequences.add(new String[]{"#", "R", "G", "Y", "*"});
        sequences.add(new String[]{"#", "R", "G", "Y", "R", "G", "*"});
        sequences.add(new String[]{"#", "R", "*"});

        // Incorrect sequences
        sequences.add(new String[]{"R", "G", "Y", "R", "*"}); // Missing start character
        sequences.add(new String[]{"#", "R", "G", "Y", "R"}); // Missing end character
        sequences.add(new String[]{"#", "R", "B", "Y", "R", "*"}); // Invalid color 'B'
        sequences.add(new String[]{"#", "R", "G", "G", "Y", "R", "*"}); // Invalid sequence (G followed by G)
        sequences.add(new String[]{"#", "*"}); // No light sequence

        for(String[] sequence: sequences)
        {
            System.out.println("Testing Input Array: " + Arrays.toString(sequence));
            try {
                TrafficSequenceResult result = analyzeTrafficSequence(sequence);
                System.out.println("Was this Input a Correct Traffic Light Sequence: " + result.isValidTrafficLightSequence());
            } catch (IllegalArgumentException e) {
                System.out.println("Reported Error: " + e.getMessage());
            }
        }
    }
}
