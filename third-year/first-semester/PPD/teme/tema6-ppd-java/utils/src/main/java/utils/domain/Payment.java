package utils.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Payment implements Serializable {
    private Integer value;
    private String cnp;
    private Date date;
}
