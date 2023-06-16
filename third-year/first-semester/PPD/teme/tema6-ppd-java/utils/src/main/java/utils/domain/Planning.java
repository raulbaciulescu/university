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
public class Planning implements Serializable {
    private String name;
    private String cnp;
    private Date date;
    private Integer location;
    private Treatment treatment;
    private Date treatmentDate;
    private Integer hour;
}
