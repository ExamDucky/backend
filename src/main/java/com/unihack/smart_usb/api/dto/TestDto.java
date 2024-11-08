package com.unihack.smart_usb.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {

    private Long id;
    private String title;
    private int duration;
    private String groupOneTestFileName;
    private String groupTwoTestFileName;
    private String groupOneUnitTestFileName;
    private String groupTwoUnitTestFileName;
    private String blacklistProcessesFileName;
    private Long professorId;

}
