package racingcar.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GameInitializeDto {

    private static final String NAMES_IS_NOT_EMPTY_MESSAGE = "[ERROR] : 이름은 비어있을 수 없습니다.";
    private static final String TRY_COUNT_IS_NOT_NULL = "시도횟수는 비어있을 수 없습니다.";

    @NotEmpty(message = NAMES_IS_NOT_EMPTY_MESSAGE)
    private String names;
    @NotNull(message = TRY_COUNT_IS_NOT_NULL)
    private Integer count;

    private GameInitializeDto() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
