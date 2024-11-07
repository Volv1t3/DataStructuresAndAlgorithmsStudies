package DeberTresStringMatchingAnalysisSantiagoArellano;

import java.security.SecureRandom;
import java.util.Random;

/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: La presente clase contiene metodos helper para producir strings de prueba para el algoritmo de
 * busqueda de patron de strings, junto con sus respectivos patrones
 ============================================**/
public class StringGeneratorHelper {

    public static TextAndPatternDTO generateStringDTO(Integer e_TextLength, /*Length in integers for the text string*/
                                                      Integer e_PatternLength, /*Length in integers for the pattern string*/
                                                      Boolean e_ShouldMatch /*Boolean indicating whether pattern matches or not*/)

    {
        //! Definimos constantes basicas
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Integer characterLenght =  characters.length();
        Random random = new SecureRandom();
        StringBuilder pattern = new StringBuilder();
        //! Generamos una String aleatoria de longitud e_TextLength
        StringBuilder text = new StringBuilder(e_TextLength);
        for(int i = 0; i < e_TextLength; i++){
            text.append(characters.charAt(random.nextInt(characterLenght)));
        }

        //! Generamos una String aleatoria de longitud e_PatternLength basados en la condicion de ShouldMatch yes or not
        if (e_ShouldMatch)
        {
            int start = random.nextInt(0, e_TextLength - e_PatternLength + 1);
            pattern = new StringBuilder(text.substring(
                    start, start + e_PatternLength));
        }
        else
        {
            for (int i = 0; i < e_PatternLength; i++) {
                pattern.append(characters.charAt(random.nextInt(characterLenght)));
            }
        }

        //! Recolectamos resultados y retornamos
        return new TextAndPatternDTO(text.toString(), pattern.toString(), e_ShouldMatch);
    }

//    public static void main(String[] args) {
//        DeberTresStringMatchingAnalysisSantiagoArellano.TextAndPatternDTO result = generateStringDTO(10, 5, true);
//        System.out.println(result);
//    }
}
