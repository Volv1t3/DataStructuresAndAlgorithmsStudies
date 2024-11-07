package RobotFSATenCentCoinIdentifier;

public record AnalysisResultDTO(Boolean isTenCentCoint,
                                Boolean isControlCharacter,
                                String binaryString,
                                Boolean toHaltOperation) {

    //! Implementation of improved constructor
    public AnalysisResultDTO(Boolean isTenCentCoint, Boolean isControlCharacter, String binaryString, Boolean toHaltOperation) {
        this.isTenCentCoint = isTenCentCoint; this.isControlCharacter = isControlCharacter; this.binaryString = binaryString;
        this.toHaltOperation = toHaltOperation;
    }

    //! Implementation of getters for AnalysisResultDTO
    public Boolean getIsPrime() {return isTenCentCoint;}
    public Boolean getIsControlCharacter() {return isControlCharacter;}
    public String getBinaryString() {return binaryString;}
    public Boolean getToHaltOperation() {return toHaltOperation;}

    @Override
    public String toString() {
        return String.format("{isPrime=%s, isControlCharacter=%s,binaryString=%s,toHaltOperation=%s",
                getIsPrime(),getIsControlCharacter(),getBinaryString(),getToHaltOperation());
    }


    //? No other methods are required for a DTO
}

