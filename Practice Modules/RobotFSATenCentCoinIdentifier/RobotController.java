package RobotFSATenCentCoinIdentifier;


import java.security.SecureRandom;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * !                Theory of Computation - Project #1 - FSA For 10 Cent Coin Identification
 * ? 1. Group Members:
 *        Tapia Buenaño, Alexis Guillermo, 00212986;
 *        Salgado Espinosa, Pablo Mateo, 00324999;
 *        Arellano Jaramillo, Santiago, 00328370;
 * ? 2. Description:
 *      The following file contains the basic implementation for the Robot entities which will identify the coins and
 *      store them for later outputting. These entities are implemented through Java records, which allow for immutable
 *      object storage out of the box.
 **/


public class RobotController {
    protected final LinkedList<String> m_AnalyzedBinaryString = new LinkedList<>();
    protected final LinkedList<RobotEntity> m_InternalRobotInstanceHolder = new LinkedList<>();
    protected Integer m_TotalOfRobots= 0;
    protected static SecureRandom m_SecureRandom = new SecureRandom();
    protected Boolean hasStarted = Boolean.FALSE;
    //! Implementation of class' constructor
    public RobotController() {
    }

    //! Implementation of getters for m_AnalyzedBinaryString
    public LinkedList<String> getm_AnalyzedBinaryString() {
        return m_AnalyzedBinaryString;
    }
    //! Implementation of getters for m_InternalRobotInstanceHolder
    public LinkedList<RobotEntity> getm_InternalRobotInstanceHolder() {
        return m_InternalRobotInstanceHolder;
    }

    //! Implemenentation for adding an instance to the internalRobotInstanceHolder
    public void addRobotInstance(RobotEntity robotEntity) throws NullPointerException{
        //! Base Check
        if (robotEntity == null) {
            throw new NullPointerException("The provided RobotEntity object is null.");
        }
        //! Secondary Check for addition correctness
        if (m_InternalRobotInstanceHolder.add(robotEntity))
        {
            System.out.println("RobotEntity added successfully: " + robotEntity.gete_RobotEntityName());
            m_TotalOfRobots++;
        }
        else {System.out.println("Failed to add RobotEntity: " + robotEntity.gete_RobotEntityName());}
    }

    //! Implementation for adding instances of robots to the internalRobotInstanceHolder
    public void addRobotInstance(RobotEntity... robotEntities)
    {
        Arrays.stream(robotEntities).parallel().forEach(this::addRobotInstance);
    }

    //! Implementation for adding collections of robots to the internalRobotInstanceHolder
    public void addRobotInstance(Collection<RobotEntity> robotEntities)
    {
        robotEntities.parallelStream().forEach(this::addRobotInstance);
    }

    //! Implementation for getters of m_TotalOfRobots
    public Integer getm_TotalOfRobots() {
        return m_TotalOfRobots;
    }

    //? We proceed now with the methods that will essentially help us on analyzing BinaryStrings

    //! Implementation for a single robotic instance working on a single String
    public AnalysisResultDTO singleRobotSingleStringAnalysis(String binaryString)
    {
        int randomRobot = RobotController.m_SecureRandom.nextInt(0, this.getm_TotalOfRobots());
        AnalysisResultDTO finalResults = null;
        //! Add into the list for quicker checking
        if (this.m_AnalyzedBinaryString.contains(binaryString)) {
            System.out.printf("Robot %s already detected the input %s as ten cent coin, returning to main program%n",
                    this.getm_InternalRobotInstanceHolder().get(randomRobot).gete_RobotEntityName(), binaryString);
            return new AnalysisResultDTO(Boolean.TRUE,
                Boolean.FALSE, binaryString, Boolean.FALSE);}
        else
        {
            finalResults = this.m_InternalRobotInstanceHolder.get(randomRobot).analyzeBinaryString(binaryString);
            if (finalResults.getIsPrime() == Boolean.TRUE && finalResults.getIsControlCharacter() == Boolean.FALSE)
            {
                System.out.printf("Robot %s has identified the input %s as a ten cent coin, saving the information.",
                        this.getm_InternalRobotInstanceHolder().get(randomRobot).gete_RobotEntityName(), binaryString);
                this.m_AnalyzedBinaryString.add(binaryString);
            }
            else {return finalResults;}
        }
        return finalResults;
    }

    //! Implementation for a single robotic instance working on a multite of input Strings
    public AnalysisResultDTO singleRobotMultipleStringAnalysis(String... binaryStrings)
    {
        //! First we should check if the first character is a start character, if it isn't then we cannot iterate and
        //! we return false false "" true
        /*We do this because without a start indicator the robot should not begin to traverse the strings*/
        if (!Objects.equals(binaryStrings[0], "#"))
        {
            System.err.println("No start character (#) was found in list of binary strings, therefore no analysis was done");
            return new AnalysisResultDTO(Boolean.FALSE,
                    Boolean.FALSE, null, Boolean.FALSE);
        }
        else
        {
            this.hasStarted = Boolean.TRUE;
            final boolean[] controlCaracterForImpression = {false};
            AtomicInteger integer = new AtomicInteger(0);
            int robots = this.getm_TotalOfRobots();
            List<AnalysisResultDTO> results = Arrays.stream(binaryStrings)
                    .filter(str -> !this.m_AnalyzedBinaryString.contains(str))
                    .map(new Function<String, AnalysisResultDTO>() {
                        @Override
                        public AnalysisResultDTO apply(String s) {

                            int randomRobot = RobotController.m_SecureRandom.nextInt(0, robots);
                            AnalysisResultDTO finalResults = m_InternalRobotInstanceHolder.get(randomRobot).analyzeBinaryString(s);
                            if (finalResults.getIsControlCharacter()  == Boolean.TRUE &&
                                Objects.equals(finalResults.getBinaryString(), "*")) {
                                System.out.println("Robot " + m_InternalRobotInstanceHolder.get(randomRobot).gete_RobotEntityName()
                                        + " analyzed " + s + " as a control character");
                                controlCaracterForImpression[0] = true;
                                return finalResults;
                            }
                            if (finalResults.getIsPrime() == Boolean.TRUE && finalResults.getIsControlCharacter() == Boolean.FALSE )
                            {
                                if (controlCaracterForImpression[0]) {return null;}
                                else
                                {

                                    //! Write a pretty message indicating the robot name and the strin which analyzed it
                                    System.out.println("Robot " + m_InternalRobotInstanceHolder.get(randomRobot).gete_RobotEntityName()
                                            + " analyzed " + s + " as a valid coin");
                                    m_AnalyzedBinaryString.add(s);
                                }
                            }
                            return finalResults;
                        }
                    }).toList();

            for (AnalysisResultDTO result : results)
            {
                if ((result.getIsPrime() == Boolean.FALSE && result.getIsControlCharacter() == Boolean.TRUE) &&
                Objects.equals(result.getBinaryString(), "*"))
                {
                    return result;
                }

            }

        }

        return new AnalysisResultDTO(Boolean.FALSE, Boolean.FALSE, null, Boolean.FALSE);
    }


}
