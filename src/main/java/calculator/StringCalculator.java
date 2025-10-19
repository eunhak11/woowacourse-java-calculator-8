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

        // 커스텀 구분자 형식이 잘못되었는지 검증
        validateCustomDelimiterFormat(input);

        // 커스텀 구분자 패턴이 있는지 확인
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (matcher.matches()) {
            // 패턴이 일치하면, 새로운 구분자와 숫자 문자열을 추출
            delimiter = Pattern.quote(matcher.group(1)); // group(1): 구분자 문자
            numbersText = matcher.group(2);              // group(2): 숫자 부분
        }

        // 확정된 구분자로 문자열 분리
        String[] numbers = numbersText.split(delimiter);
        return sum(numbers);
    }

    // 문자열 배열의 원소들을 정수로 변환하여 합 계산
    private int sum(String[] numbers) {
        int result = 0;
        for (String number : numbers) {
            result += parseNumber(number);
        }
        return result;
    }

    // 커스텀 구분자 형식 검증
    private void validateCustomDelimiterFormat(String input) {
        // "//"로 시작하는 경우 커스텀 구분자 형식이어야 함
        if (input.startsWith("//")) {
            // 형식이 틀린 경우 예외 발생
            if (!CUSTOM_DELIMITER_PATTERN.matcher(input).matches()) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
        }
    }

    // 음수 입력, 숫자가 아닌 값에 대한 예외 처리
    private int parseNumber(String number) {
        try {
            int value = Integer.parseInt(number);
            if (value < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 입력되었습니다: " + number);
        }
    }
}