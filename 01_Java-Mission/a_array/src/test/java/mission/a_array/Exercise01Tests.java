package mission.a_array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exercise01Tests {
    private static final String[] INPUT_STRING_ARRAY = {"Java", "is", "Fun"};
    private static final String EXPECTED_STRING = "단어1: 4, 단어2: 2, 단어3: 3, 총 길이: 9";

    @Test
    @DisplayName("공백문자를 제외한 단어별 문자 길이와 총 길이 리턴값 확인")
    public void calculateLengthTest() {
        Assertions.assertEquals(EXPECTED_STRING, Exercise01.calculateLength(INPUT_STRING_ARRAY));
    }

    @Test
    @DisplayName("단어 split 확인")
    public void getSplitStringArrayTest() {
        String test1 = "Java is Fun";
        String test2 = "Java   is   Fun";
        String test3 = "  Java is Fun  ";
        String test4 = "Java\tis\nFun";

        String[] expected = {"Java", "is", "Fun"};

        Assertions.assertArrayEquals(expected, Exercise01.getSplitStringArray(test1));
        Assertions.assertArrayEquals(expected, Exercise01.getSplitStringArray(test2));
        Assertions.assertArrayEquals(expected, Exercise01.getSplitStringArray(test3));
        Assertions.assertArrayEquals(expected, Exercise01.getSplitStringArray(test4));
    }

    @Test
    @DisplayName("개별 길이 정보 포함 여부 확인")
    public void calculateLengthNoSpaces() {
        String result = Exercise01.calculateLength(INPUT_STRING_ARRAY);
        Assertions.assertTrue(result.contains("단어1: 4"));
        Assertions.assertTrue(result.contains("단어2: 2"));
        Assertions.assertTrue(result.contains("단어3: 3"));
        Assertions.assertTrue(result.contains("총 길이: 9"));
    }
} 