package RobotFSAControllers;

import java.util.Map;

public class Robot {

    private final String name;
    private Map<String, IsBinaryPrime.results> internalRecordOfInstructions;

    public Robot(String name, Map<String, IsBinaryPrime.results> internalRecordOfInstructions) {
        this.name = name;
        this.internalRecordOfInstructions = internalRecordOfInstructions;
    }

    public String getName() {
        return name;
    }
    public Map<String, IsBinaryPrime.results> getInternalRecordOfInstructions() {
        return internalRecordOfInstructions;
    }

    //! Retorna un booleano resultado de la evaluacion de isBinaryPrime || isControlCharacter
    public  RobotMessageDTO acceptCommand(String command)
    {
        IsBinaryPrime.results prime = IsBinaryPrime.isBinaryPrime(command);
        this.internalRecordOfInstructions.putIfAbsent(command, prime);
        if (internalRecordOfInstructions.containsKey(command))
        {
            System.out.println();
            System.out.println("Analysis already done on this input...");
            System.out.println(this.getName() + " has already analyzed the string: " + command);
            System.out.println("Last Analysis indicted that the string " + command + " is " +
            (this.internalRecordOfInstructions.get(command).isBinaryPrime() ? "a binary prime number." : "not a binary prime number.")
            + "and " + (this.internalRecordOfInstructions.get(command).isControlCharacter() ? "is" : "is not") + " a control character.");
            System.out.println("End of Transmission...");
        }
        else
        {
            System.out.println();
            System.out.println("Input has not been analyzed before...");
            if (prime.isBinaryPrime()) {System.out.println(this.getName() + " has analyzed the string: " + command + " and it is a binary prime number.");}
            else {System.out.println(this.getName() + " has analyzed the string: " + command + " and it is not a binary prime number.");}
            if (prime.isControlCharacter()) {System.out.println("The string: " + command + " is a control character.");}
            else {System.out.println(this.getName() + " has analyzed the string: " + command + " and it is not a control character.");}
            System.out.println("End of Transmission...");
        }

        return new RobotMessageDTO(this.getName(),
                command,
                prime.isBinaryPrime(),
                prime.isControlCharacter());
    }

    //! Implementacion de un Consumer Para Analizar las senales
    //! de la clase IsBinaryPrime
    public void consumerMessageRobot(RobotMessageDTO messageDTO)
    {
        System.out.println("Consumer From [" + messageDTO.getPreviousRobotName() + "] indicated that ["
        + messageDTO.binaryChain() + "] is " + messageDTO.getIsBinaryPrime());
        System.out.println();
        System.out.println(this.getName() + "will proceed to analyze said string.");
        System.out.println();
        this.acceptCommand(messageDTO.binaryChain());
    }


}
