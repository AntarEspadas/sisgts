package mx.uam.ayd.proyecto.negocio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.image.BufferedImage;

@AllArgsConstructor
public class Captcha {
    @Getter(AccessLevel.PACKAGE)
    private String texto;

    @Getter
    private BufferedImage imagen;
}
