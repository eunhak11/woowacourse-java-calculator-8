package calculator;

public class StringCalculator {

    // 정규표현식으로 ,와 : 구분자 등록
    private static final String DEFAULT_DELIMITER = "[,:]";

    public int calculate(String input) {
        // null 또는 빈 문자열 입력 처리
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // 기본 구분자로 문자열 분리
        String[] numbers = input.split(DEFAULT_DELIMITER);

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