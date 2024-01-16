package com.funcionarios.funcionarios.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.funcionarios.funcionarios.model.Funcionario;
import com.funcionarios.funcionarios.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

    @Autowired
    FuncionarioRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/funcionario/cadastro")
    public String cadastroFuncionario() {
        return "cadastroFuncionario";
    }

    @PostMapping("/funcionario")
    public String novoFuncionario(Funcionario funcionario) {
        String novaSenha = this.passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(novaSenha);
        repository.inserir(funcionario);
        return "redirect:/funcionario/lista";
    }

    @GetMapping("/funcionario/lista")
    public ModelAndView lista() throws SQLException {
        ModelAndView mv = new ModelAndView("listaFuncionarios");
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = repository.buscar();
        mv.addObject("funcionarios", funcionarios);

        return mv;
    }

    @GetMapping("/funcionario/excluir/{id}")
    public String excluir(@PathVariable("id") int id) throws SQLException {
        repository.excluir(id);
        return "redirect:/funcionario/lista";
    }

    @GetMapping("/funcionario/login")
    public String loginFuncionario() {
        return "loginFuncionario";
    }

    @PostMapping("/login")
    public String realizarLogin(Funcionario funcionario) throws SQLException {
        String url = "";

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = repository.buscar();

        for (Funcionario f : funcionarios) {

            if (f.getLogin().equals(funcionario.getLogin())
                    && passwordEncoder.matches(funcionario.getSenha(), f.getSenha())) {
            } else {
                url = "redirect:/funcionario/login";
            }
        }
        return url;
    }

}
