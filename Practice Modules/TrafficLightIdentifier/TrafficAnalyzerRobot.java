package TrafficLightIdentifier;

public class TrafficAnalyzerRobot
{
    //! Internal Variables
    String robotIdentifier;
    //! Static constants
    public static String GREEN_LIGHT_MARKER = "G";
    public static String RED_LIGHT_MARKER = "R";
    public static String YELLOW_LIGHT_MARKER = "Y";
    public static String START_ANALYSIS_MARKER = "#";
    public static String END_ANALYSIS_MARKER = "*";

    //! Factory method to create instances
    public static TrafficAnalyzerRobot create( String RobotIdentifier)
    {
        return new TrafficAnalyzerRobot(RobotIdentifier);
    }

    //! Constructor
    private TrafficAnalyzerRobot( String RobotIdentifier)
    {
        this.robotIdentifier = RobotIdentifier;
    }

    //! Getter and Setter methods for internal variables


    //! Overloaded toString Method
    @Override
    public String toString()
    {
       //! Implementing toString
        return "TrafficAnalyzerRobot{" +
                "robotIdentifier='" + robotIdentifier + '\'' +
                '}';
    }

    //! Overloaded Equals based on name
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof TrafficAnalyzerRobot other)
        {
            return this.robotIdentifier.equals(other.robotIdentifier);
        }
        return false;
    }

}
