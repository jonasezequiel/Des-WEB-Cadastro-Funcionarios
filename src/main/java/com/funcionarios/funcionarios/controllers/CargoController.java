package com.funcionarios.funcionarios.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.funcionarios.funcionarios.model.Cargo;
import com.funcionarios.funcionarios.repository.CargoRepository;

@Controller
public class CargoController {

    @Autowired
    CargoRepository repository;

    @GetMapping("/cargo/cadastro")
    public String cadastroCargo() {
        return "cadastroCargo";
    }

    @PostMapping("/cargo")
    public String novoCargo(Cargo cargo) {
        repository.inserir(cargo);
        return "redirect:/cargo/lista";
    }

    @GetMapping("/cargo/lista")
    public ModelAndView lista() throws SQLException {
        ModelAndView mv = new ModelAndView("listaCargo");
        List<Cargo> cargos = new ArrayList<>();
        cargos = repository.buscar();
        mv.addObject("cargos", cargos);

        return mv;
    }

    @GetMapping("/cargo/excluir/{id_cargo}")
    public String excluir(@PathVariable("id_cargo") int id_cargo) throws SQLException {
        repository.excluir(id_cargo);
        return "redirect:/cargo/lista";
    }
}