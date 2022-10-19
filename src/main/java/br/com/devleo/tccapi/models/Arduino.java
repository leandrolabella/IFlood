package br.com.devleo.tccapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Arduino {

    public String id;
    public int valueWater;
    public int valueRain;
}
