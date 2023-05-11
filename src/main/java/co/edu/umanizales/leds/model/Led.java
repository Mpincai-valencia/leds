package co.edu.umanizales.leds.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Led {
    private int identification;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;
}
