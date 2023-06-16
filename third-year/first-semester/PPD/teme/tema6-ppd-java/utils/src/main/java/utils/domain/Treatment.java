package utils.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
public class Treatment implements Serializable {
    private Integer cost;
    private Integer duration;
}
