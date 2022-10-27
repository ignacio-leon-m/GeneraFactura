
package com.ignacioleon.generafactura.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/procesaFactura")
public class ProcesaFactura extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre, empresa, rut, direccion, ciudad, pais;
        nombre = request.getParameter("nombre");
        empresa = request.getParameter("empresa");
        rut = request.getParameter("rut");
        direccion = request.getParameter("direccion");
        ciudad = request.getParameter("ciudad");
        pais = request.getParameter("pais");

        Integer cantValvulas, cantTurbo, cantFrenos, cantRefri, cantPlumas;
        cantValvulas = Integer.parseInt(request.getParameter("valvulas"));
        cantTurbo = Integer.parseInt(request.getParameter("turbo"));
        cantFrenos = Integer.parseInt(request.getParameter("frenos"));
        cantRefri = Integer.parseInt(request.getParameter("refri"));
        cantPlumas = Integer.parseInt(request.getParameter("plumas"));

        int precioValvulas = 120000;
        int precioTurbo = 1700000;
        int precioFreno = 760000;
        int precioRefri = 2300000;
        int precioPlumas = 10000;

        request.setAttribute("nombre", nombre); //Identificador y dato a enviar
        request.setAttribute("empresa", empresa);
        request.setAttribute("rut", rut);
        request.setAttribute("direccion", direccion);
        request.setAttribute("ciudad", ciudad);
        request.setAttribute("pais", pais);

        request.setAttribute("cantValvulas", cantValvulas);
        request.setAttribute("cantTurbo", cantTurbo);
        request.setAttribute("cantFrenos", cantFrenos);
        request.setAttribute("cantRefri", cantRefri);
        request.setAttribute("cantPlumas", cantPlumas);

        //Calculo de totales
        request.setAttribute("precioTotalValvulas", cantValvulas * precioValvulas);
        request.setAttribute("precioTotalTurbos", cantTurbo * precioTurbo);
        request.setAttribute("precioTotalFrenos", cantFrenos * precioFreno);
        request.setAttribute("precioTotalRefri", cantRefri * precioRefri);
        request.setAttribute("precioTotalPlumas", cantPlumas * precioPlumas);

        Integer valorTotal = (
                        cantValvulas * precioValvulas +
                        cantTurbo * precioTurbo +
                        cantFrenos * precioFreno +
                        cantRefri * precioRefri +
                        cantPlumas * precioPlumas
        );

        request.setAttribute("valorTotal", valorTotal);

        //Redireccionamiento al servlet que genera la factura y redirecciona a la página de factura
        request.getRequestDispatcher("/generaFactura").forward(request,response);

    }
}
