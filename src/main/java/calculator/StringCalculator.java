package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    // 기본 구분자는 쉼표(,)와 콜론(:)
    private static final String DEFAULT_DELIMITER = "[,:]";
    // 커스텀 구분자를 찾기 위한 정규표현식 패턴 (\n을 문자열로 인식)
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\\\\n(.*)");

    public int calculate(String input) {
        // null 또는 빈 문자열 입력 처리
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = DEFAULT_DELIMITER;
        String numbersText = input;

        // 커스텀 구분자 패턴이 있는지 확인
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (matcher.matches()) {
            // 패턴이 일치하면, 새로운 구분자와 숫자 문자열을 추출
            delimiter = Pattern.quote(matcher.group(1)); // group(1): 구분자 문자
            numbersText = matcher.group(2);              // group(2): 숫자 부분
        }

        // 확정된 구분자로 문자열 분리
        String[] numbers = numbersText.split(delimiter);

        // 숫자 배열의 합 계산
        return sum(numbers);
    }

    // 문자열 배열의 원소들을 정수로 변환하여 합계를 구하는 메서드
    private int sum(String[] numbers) {
        int result = 0;
        for (String number : numbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}