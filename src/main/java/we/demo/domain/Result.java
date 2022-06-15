package we.demo.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Result {
    private String quotient;
    private String remainder;
}
