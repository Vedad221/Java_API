package si.telekom.potres.model;

import lombok.Value;

@Value
public class Potres {
    private final String najblizjiKraj;
    private final String geoLokacija;
    private final double globina;
}
