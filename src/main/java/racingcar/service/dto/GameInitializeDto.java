package racingcar.service.dto;

import javax.validation.constraints.NotEmpty;

public class GameInitializeDto {

    @NotEmpty
    private String names;
    @NotEmpty
    private int count;

    private GameInitializeDto() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
