package Helpers;

public class StringHelpers {

    public static int bruteForceMatching(String text, String pattern)
    {
        int jindex = 0;
        for(int index = 0; index < text.length() - pattern.length() +1; index++)
        {
            for( ; jindex < pattern.length(); jindex++)
            {
                if (text.charAt(index+ jindex) != pattern.charAt(jindex))
                {
                    break;
                }
            }

            if (jindex == pattern.length())
            {
                return index;
            }
        }

        return -1;
    }

    public static int boyerMooreMatching(String text, String pattern)
    {
        int index = pattern.length() - 1;
        while (index < text.length())
        {
            int kindex = index;
            int jindex = pattern.length() -1;

            while (jindex >= 0)
            {
                if (text.charAt(kindex) == pattern.charAt(jindex))
                {
                    kindex--; jindex--;
                }
                else {break;}
            }

            if (jindex < 0)
            {
                return kindex;
            }

            int undex = findOccurenceInPattern(text.charAt(kindex), jindex -1, pattern);

            if (undex >= 0) {
                index = kindex + pattern.length() - 1 - undex;
            }
            else {
                index = kindex + pattern.length();
            }
        }
        return -1;
    }

    public static int findOccurenceInPattern(Character ch, int index, String pattern)
    {
        for(int k = index; k >= 0; k--)
        {
            if (ch == pattern.charAt(k))
            {
                return k;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(bruteForceMatching("awdlkjlkjlkjawd", "kjlk"));
        System.out.println(boyerMooreMatching("awdljkjlkjlkjawd", "lkj"));
    }
}
