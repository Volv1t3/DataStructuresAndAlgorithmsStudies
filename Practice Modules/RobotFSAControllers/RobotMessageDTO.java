package RobotFSAControllers;

public record RobotMessageDTO(String previousRobotName,
                              String binaryChain,
                              Boolean isBinaryPrime,
                              Boolean isControlCharacter) {
    public RobotMessageDTO(String previousRobotName, String binaryChain,
                           Boolean isBinaryPrime, Boolean isControlCharacter) {
            this.previousRobotName = previousRobotName;
            this.binaryChain = binaryChain;
            this.isBinaryPrime = isBinaryPrime;
            this.isControlCharacter = isControlCharacter;
        }

    //! Getter para previousRobotName
    public String getPreviousRobotName() {
        return previousRobotName;
    }

    //! Getter para binaryChain
    public String getBinaryChain() {
        return binaryChain;
    }
    //! Getter para isBinaryPrime
    public Boolean getIsBinaryPrime() {
        return isBinaryPrime;
    }
    //! Getter para isControlCharacter
    public Boolean getIsControlCharacter() {
        return isControlCharacter;
    }
    

}

