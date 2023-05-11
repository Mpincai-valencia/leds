package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.controller.dto.LedDTO;
import co.edu.umanizales.leds.controller.dto.ResponseDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.LedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/leds")
public class LedsController {
    @Autowired
    public LedService ledService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds()
    {
        return new ResponseEntity<>(new ResponseDTO(200,ledService.getLedsList(),null), HttpStatus.OK);
    }
    @PostMapping(path="/add")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody LedDTO ledDTO)
    {
        ledService.getLeds().add(new Led(ledDTO.getIdentification(),ledDTO.isState(),
                ledDTO.getDateOn(),ledDTO.getDateOff()));
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha adicionado el led", null), HttpStatus.OK);
    }
    @PostMapping(path="/addtostart")
    public  ResponseEntity<ResponseDTO>addToStart(@RequestBody LedDTO ledDTO)
    {
        ledService.getLeds().addToStart(new Led(ledDTO.getIdentification(),ledDTO.isState(),
                ledDTO.getDateOn(),ledDTO.getDateOff()));
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha adicionado el led", null), HttpStatus.OK);
    }
    @PostMapping(path = "/addinposition")
    public ResponseEntity<ResponseDTO>addInPosition(@RequestBody LedDTO ledDTO,@PathVariable int position)
    {
        ledService.getLeds().addInPosition(new Led(ledDTO.getIdentification(),ledDTO.isState(),
                ledDTO.getDateOn(),ledDTO.getDateOff()),position);
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha adicionado el led", null), HttpStatus.OK);
    }
    @GetMapping(path = "/delete")
    public ResponseEntity<ResponseDTO>delete(@PathVariable String identification,@PathVariable int position)
    {
        ledService.getLeds().delete(identification,position);
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado el led",null),HttpStatus.OK);
    }
    @GetMapping(path ="/restartleds")
    public ResponseEntity<ResponseDTO>restartListLeds()
    {
        ledService.getLeds().restartLedList();
        return new ResponseEntity<>(new ResponseDTO(200,"Se han reiniciado los leds",null),HttpStatus.OK);
    }


}
