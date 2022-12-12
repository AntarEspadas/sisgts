package mx.uam.ayd.proyecto.negocio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Captcha {
    @Getter(AccessLevel.PACKAGE)
    private String texto;

    @Getter
    private byte[] imagen;
}
