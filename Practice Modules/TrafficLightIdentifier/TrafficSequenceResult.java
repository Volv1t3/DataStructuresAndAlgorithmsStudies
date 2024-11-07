package TrafficLightIdentifier;

public record TrafficSequenceResult(
        Boolean isValidTrafficLightSequence /*Used to represent if the given string input is valid or not*/,
        Boolean endedInRedLight /*used to represent the case that a sequence ended in a red light*/,
        Boolean endedInYellowLight /*used to represent the case that a sequence ended in a yellow light*/,
        Boolean endedInGreenLight /*used to represent the case that a sequence ended in a green light*/,
        Boolean isControlCharacterOnly /*used to represent the case that a sequence contains only control characters*/,
        Boolean endedInStopCharacter /*used to represent the case that a sequence ended in a stop character*/,
        String recordOfInputString /*used to represent the record of the input string*/)
{

    //! Public Constructor with all values stablished
    public TrafficSequenceResult(Boolean isValidTrafficLightSequence,
                                 Boolean endedInRedLight,
                                 Boolean endedInYellowLight,
                                 Boolean endedInGreenLight,
                                 Boolean isControlCharacterOnly,
                                 Boolean endedInStopCharacter,
                                 String recordOfInputString) {
        this.isValidTrafficLightSequence = isValidTrafficLightSequence;
        this.endedInRedLight = endedInRedLight;
        this.endedInYellowLight = endedInYellowLight;
        this.endedInGreenLight = endedInGreenLight;
        this.isControlCharacterOnly = isControlCharacterOnly;
        this.endedInStopCharacter = endedInStopCharacter;
        this.recordOfInputString = recordOfInputString;
    }

    //! Getter method for each value
    public Boolean getIsValidTrafficLightSequence() {
        return isValidTrafficLightSequence;
    }
    public Boolean getEndedInRedLight() {
        return endedInRedLight;
    }
    public Boolean getEndedInYellowLight() {
        return endedInYellowLight;
    }
    public Boolean getEndedInGreenLight() {
        return endedInGreenLight;
    }
    public Boolean getIsControlCharacterOnly() {
        return isControlCharacterOnly;
    }
    public Boolean getEndedInStopCharacter() {
        return endedInStopCharacter;
    }
    public String getRecordOfInputString() {
        return recordOfInputString;
    }

    //! Overloaded To String Method
    @Override
    public String toString() {
        return "TrafficSequenceResult{" +
                "isValidTrafficLightSequence=" + isValidTrafficLightSequence +
                ", endedInRedLight=" + endedInRedLight +
                ", endedInYellowLight=" + endedInYellowLight +
                ", endedInGreenLight=" + endedInGreenLight +
                ", isControlCharacterOnly=" + isControlCharacterOnly +
                ", endedInStopCharacter=" + endedInStopCharacter +
                ", recordOfInputString='" + recordOfInputString + '\'' +
                '}';
    }

    //! Overloaded equals based on isValidLightSequence and end state
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TrafficSequenceResult that = (TrafficSequenceResult) obj;
        return isValidTrafficLightSequence == that.isValidTrafficLightSequence &&
                endedInRedLight == that.endedInRedLight &&
                endedInYellowLight == that.endedInYellowLight &&
                endedInGreenLight == that.endedInGreenLight;
    }


}
