package com.Equipo4.ProyectoIntegradorEquipo4.entities;

import lombok.Data;


public class MailHtml {

    public static final String correoRegistro = "<html>" +
            "<body style='background-color: #bff5d1; font-size:x-large; text-align: center; padding:50px; font-family: Arial, sans-serif;'>" +
            "<p>¡Hola bienvenido a la comunidad libre!</p>" +
            "<p>Estamos emocionados de tu registro en Homeoff</p>" +
            "<p>Te dejamos el link para que ingreses a nuestra plataforma</p>" +
            "<p> http://homeoff-ts-fe.s3-website-us-west-2.amazonaws.com/ </p>" +
            "<p>Hasta pronto,</p>" +
            "<p>Equipo HomeOff, tu oficina donde quieras.</p>" +
            "</body>" +
            "</html>";
}
