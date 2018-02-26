package dev5.chermenin.service.dto.subject;

import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ancarian on 25.02.2018.
 */

@Getter
@Setter
public class SubjectScoreDto extends Dto {
    private String subject;
    private int score;
}
