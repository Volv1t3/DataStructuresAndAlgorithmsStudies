package DeberTresStringMatchingAnalysisSantiagoArellano; /**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: El presente record muestra un DTO para los resultados de algoritmos base para la produccion de cadenas
 * de texto y patrones para el programa.
 ============================================**/

import java.io.Serializable;
import java.util.Objects;

public record TextAndPatternDTO(String m_Text /*Used to store text to find substrings in*/
        , String m_Pattern /*Used to store string to find in text*/
        , Boolean m_willMatch /*used to identify cases in which the Pattern is real and cases where it is not*/) implements
        Serializable{

    /**
     * Constructor for DeberTresStringMatchingAnalysisSantiagoArellano.TextAndPatternDTO
     * @param m_Text Text to find substrings in
     * @param m_Pattern String to find in text
     * @param m_willMatch Boolean to identify cases in which the Pattern is real and cases where it is not
     */
    public TextAndPatternDTO(String m_Text, String m_Pattern, Boolean m_willMatch)
    {
        this.m_Text = m_Text;
        this.m_Pattern = m_Pattern;
        this.m_willMatch = m_willMatch;
    }

    //! Getter method for m_Text
    public String getM_Text() {
        return m_Text;
    }

    //! Getter method for m_Pattern
    public String getM_Pattern() {
        return m_Pattern;
    }

    //! Getter method for m_willMatch
    public Boolean getM_willMatch() {
        return m_willMatch;
    }

    //! Overriden equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextAndPatternDTO other = (TextAndPatternDTO) obj;
        return m_Text.equals(other.m_Text) && m_Pattern.equals(other.m_Pattern) &&
                Objects.equals(this.m_willMatch, other.m_willMatch);
    }

    @Override
    public String toString() {
        return "DeberTresStringMatchingAnalysisSantiagoArellano.TextAndPatternDTO{" +
                "m_Text=' " + m_Text + '\'' +
                ", m_Pattern=' " + m_Pattern + '\'' +
                ", m_willMatch= " + m_willMatch +
                '}';
    }

}
