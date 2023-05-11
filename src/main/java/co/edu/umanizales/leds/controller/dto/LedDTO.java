package co.edu.umanizales.leds.controller.dto;

import lombok.Data;

import java.time.LocalTime;
@Data
public class LedDTO {
    private int identification;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;

}
