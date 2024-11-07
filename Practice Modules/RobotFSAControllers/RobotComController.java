package RobotFSAControllers;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RobotComController {

    private HashMap<String, Robot> robots = new HashMap<>();
    private LinkedHashMap<String, IsBinaryPrime.results> stringresultsLinkedHashMap = new LinkedHashMap<>();

    public RobotComController(Robot... instances) {
        for (Robot robot : instances) {
            robots.put(robot.getName(), robot);
        }
    }

    public void addRobot(Robot robot) {
        robots.put(robot.getName(), robot);
    }
    public void removeRobot(String robotName) {
        robots.remove(robotName);
    }

    public RobotMessageDTO executeCommandOnSingleRobot(String robotName, String command) {
        Robot robot = robots.get(robotName);
        if (robot != null) {
            var result = robot.acceptCommand(command);
            if (result.isBinaryPrime() )
            {
                System.out.println("\nReminder...");
                System.out.println("Robot " + robotName + " has analyzed the string: " + command + " and it is a binary prime number. " +
                        "Sending a signal to all other robots");
                executeCommandOnAllRobots(result);
                System.out.println("End of Transmission...");
            }
            else {
                System.out.println("\nReminder...");
                System.out.println("Robot " + robotName + " has analyzed the string: " + command + " and it is not a binary prime number." +
                        "Notifying all other robots");
                executeCommandOnAllRobots(result);
                System.out.println("End of Transmission...");
            }
            return result;
        } else {
            System.out.println("Robot not found: " + robotName);
            return null;
        }
    }

    public void executeCommandOnAllRobots(RobotMessageDTO messageDTO) {
        for (Robot robot : robots.values()) {
            if (!robot.getName().equals(messageDTO.getPreviousRobotName())) {
                robot.consumerMessageRobot(messageDTO);
            }
        }
    }

}
