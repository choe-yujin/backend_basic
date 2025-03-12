import org.junit.Assert;
import org.junit.Test;

public class ExerciseTests {
    @Test
    public void calculateLengthTest() {
        String result = Exercise.calculateLength("Java is Fun");

        // 단어1: 4, 단어2: 2, 단어3: 3, 총 길이: 9
        Assert.assertEquals("단어1: 4, 단어2: 2, 단어3: 3, 총 길이: 9", result);
    }
    @Test
    public void calculateNoSpaces() {
        String result = Exercise.calculateLength("Hello World !");

        // 단어1: 5, 단어2: 5, 단어3: 1, 총 길이: 11
        Assert.assertTrue(result.contains("단어1: 5"));
        Assert.assertTrue(result.contains("단어2: 5"));
        Assert.assertTrue(result.contains("단어3: 1"));
        Assert.assertTrue(result.contains("총 길이: 11"));
    }
}
