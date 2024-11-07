    package RobotFSAControllers;
    


    import java.security.NoSuchAlgorithmException;
    import java.security.SecureRandom;
    import java.util.HashMap;
    import java.util.Map;
    
    public class ControllerExample {
    
    
        public static void main(String[] args) throws NoSuchAlgorithmException {


            String startString = "$";
            //! Base Step: First we define the base robots for communication
            RobotComController robots = new RobotComController(
                    new Robot("R2D2", new HashMap<>()),
                    new Robot("C3PO", new HashMap<>())
            );
    
            //! Base Step.2: Once defined, we need to test communication
            robots.executeCommandOnSingleRobot("R2D2", "1010");
    
            //! Inductive Step.1: Given the robots, we define R2D2 to be the leader of the two robots, we will begin our iterations by
            //! defining the leader
    
            SecureRandom random = SecureRandom.getInstanceStrong();
            RobotMessageDTO messageDTO = null;
            do
            {
                int randomIntValue = random.nextInt(1, 46);
                messageDTO = robots.executeCommandOnSingleRobot("R2D2", Integer.toBinaryString(randomIntValue));
            }
            while (!(messageDTO.getBinaryChain().equals("100011"))
            || !messageDTO.getBinaryChain().equals("101010"));
    
            
    
    
        }
    
    
    
    }
