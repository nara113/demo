package we.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ToString
@Builder
@Getter @Setter
public class Form {
    @NotBlank
    private String url;

    private Type type;

    @Min(1)
    private int bundleUnit;
}
