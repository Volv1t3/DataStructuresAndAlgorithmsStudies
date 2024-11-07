package RobotFSATenCentCoinIdentifier;

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

import java.util.LinkedHashSet;
import java.util.Objects;

/**
 *
 * @param e_RobotEntityName
 */
public record RobotEntity(
        String e_RobotEntityName /*Used to Store the Referencial Name of the Entity*/)
        implements Comparable<RobotEntity> {
    
    //! Implementation of a basic constructor for a RobotEntity
    public RobotEntity(String e_RobotEntityName)
    {
        this.e_RobotEntityName = e_RobotEntityName;
    }
    
    //! Implementation of getters for RobotEntityName
    public String gete_RobotEntityName() {return e_RobotEntityName;}


    //! Implementation of Sanitization classes, equals, toString, compareTo

    //? Implementation for compareTo Method, this method checks nothing but the entity name since the LinkedHashSet 
    //? will be shared
    @Override
    public int compareTo(RobotEntity o) {

        if (this == o) {
            return 0;
        }
        if (o == null) {
            throw new NullPointerException("The provided RobotEntity object is null.");
        }
        return Objects.equals(this.e_RobotEntityName, o.e_RobotEntityName) ? 1 : -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RobotEntity other = (RobotEntity) obj;
        return Objects.equals(this.e_RobotEntityName, other.e_RobotEntityName);
    }

    @Override
    public String toString() {
        return "RobotEntity{" + "e_RobotEntityName=" + e_RobotEntityName + " }";
    }

    //! Definition of the internal method which will handle the analysis of the string via the PrimeHelper Class
    public  AnalysisResultDTO analyzeBinaryString(String binaryString) {
        System.out.printf("Robot: %s will process the following string:%s%n",
                this.gete_RobotEntityName(), binaryString);
        Boolean[] interimResults = TenCentCoinFinder.isBinaryMoneyDivisibleIntoTenCentCointsOrControl(binaryString);
        //! Check for base cases
        if (interimResults[0] == Boolean.FALSE && interimResults[1] == Boolean.TRUE) {
            System.out.printf("Robot: %s has detected a control character for input string %s, proceeding accordingly%n",
                    this.gete_RobotEntityName(), binaryString);
            return new AnalysisResultDTO(Boolean.FALSE, Boolean.TRUE,
                    binaryString, Objects.equals(binaryString, "#")? Boolean.FALSE:Boolean.TRUE);
        }
        else
        {
            System.out.printf("Robot: %s has proceeded with the analysis and the results are the following.%n",
                    this.gete_RobotEntityName());
            return new AnalysisResultDTO(interimResults[0], interimResults[1],
                    binaryString, Boolean.FALSE);
        }
    }

}
