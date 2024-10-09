package org.agaldamez.spring.datajpa.app.controllers;

import jakarta.validation.Valid;
import org.agaldamez.spring.datajpa.app.models.entity.Cliente;
import org.agaldamez.spring.datajpa.app.repositories.ClienteRepository;
import org.agaldamez.spring.datajpa.app.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("cliente")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public String getAllClients(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteService.getAllClients());
        return "listar";
    }

    @GetMapping("/form")
    public String save(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Clientes");
        return "form";
    }

    @PostMapping("/form")
    public String formProcess(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }
        String mgsFlash = (cliente.getId() != null) ? "Cliente editado con exito!" : "Cliente creado con exito!";
        clienteService.saveClient(cliente);
        status.setComplete();
        flash.addAttribute("success", mgsFlash);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/form/{id}")
    public String updateClient(@PathVariable("id")  Long id, Model model, RedirectAttributes flash) {
        Cliente cliente;
        if (id > 0) {
            cliente = clienteService.getClientById(id);
            if (cliente == null) {
                flash.addAttribute("error", "El id del cliente no existe!");
                return "redirect:/clientes/listar";
            }
        } else {
            flash.addAttribute("error", "El id 0 no existe!");
            return "redirect:/clientes/listar";
        }
        model.addAttribute("titulo", "Editar datos del cliente!");
        model.addAttribute("cliente", cliente);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteClient(@PathVariable("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            clienteService.removeClientById(id);
            flash.addAttribute("success", "Cliente eliminado con exito!");
        }
        return "redirect:/clientes/listar";
    }
}
