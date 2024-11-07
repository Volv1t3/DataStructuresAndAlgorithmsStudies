package DeberTresStringMatchingAnalysisSantiagoArellano; /**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: El presente record muestra un DTO para los resultados del algoritmo secundario encargado de evaluar
 * los textos con sus patrones y registrar si fueron un caso de exito o no, y el conteo de comparaciones realizadas.
 ============================================**/


import java.util.Objects;

public record PatternMatcherResultsDTO(Boolean m_isMatch/*Used to store the boolean result of each pattern search*/
        , TextAndPatternDTO m_textAndPatternDTOBackup /*Backup of the first DTO for safety reasons*/
        , Integer m_countOfComparisons /*Total of comparisons done in a single execution of the algorithms*/
        , Integer m_IndexFound /*Used to store the index where the pattern was found*/) {

    /**
     * Constructor for DeberTresStringMatchingAnalysisSantiagoArellano.PatternMatcherResultsDTO
     * @param m_isMatch Boolean result of each pattern search
     * @param m_textAndPatternDTOBackup Backup of the first DTO for safety reasons
     * @param m_countOfComparisons Total of comparisons done in a single execution of the algorithms
     */
    public PatternMatcherResultsDTO(Boolean m_isMatch, TextAndPatternDTO m_textAndPatternDTOBackup, Integer m_countOfComparisons, Integer m_IndexFound) {
        this.m_isMatch = m_isMatch;
        this.m_textAndPatternDTOBackup = m_textAndPatternDTOBackup;
        this.m_countOfComparisons = m_countOfComparisons;
        this.m_IndexFound = m_IndexFound;
    }

    //! Getter for m_isMatch
    public Boolean getM_IsMatch() {
        return m_isMatch;
    }
    //! Getter for m_textAndPatternDTOBackup
    public TextAndPatternDTO getM_TextAndPatternDTOBackup() {
        return m_textAndPatternDTOBackup;
    }

    //! Getter for m_countOfComparisons
    public Integer getM_CountOfComparisons() {
        return m_countOfComparisons;
    }

    //! Getter for m_IndexFound
    public Integer getM_IndexFound() {
        return m_IndexFound;
    }
    //! Overriden toString method
    @Override
    public String toString() {
        return "DeberTresStringMatchingAnalysisSantiagoArellano.PatternMatcherResultsDTO{" +
                "m_isMatch=" + m_isMatch +
                ", m_textAndPatternDTOBackup=" + m_textAndPatternDTOBackup +
                ", m_countOfComparisons=" + m_countOfComparisons +
                ", m_IndexFound=" + m_IndexFound +
                '}';
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
        PatternMatcherResultsDTO other = (PatternMatcherResultsDTO) obj;
        return m_isMatch == other.m_isMatch && m_textAndPatternDTOBackup.equals(other.m_textAndPatternDTOBackup)
                && Objects.equals(m_countOfComparisons, other.m_countOfComparisons);

    }

}
