package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Genero;
import application.repository.GeneroRepository;

@Controller
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepo;

    @RequestMapping("/insert")
    public String insert() {
        return "/generos/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("titulo") String titulo) {
        Genero genero = new Genero();
        genero.setTitulo(titulo);
        generoRepo.save(genero);
        return "redirect:/generos/list";
    }

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("generos", generoRepo.findAll());
        return "/generos/list";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable long id, Model ui) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if (resultado.isPresent()) {
            ui.addAttribute("genero", resultado.get());  // ✅ Singular
            return "/generos/update";
        }

        return "redirect:/generos/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam("id") long id,
            @RequestParam("titulo") String titulo) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if (resultado.isPresent()) {
            resultado.get().setTitulo(titulo);
            generoRepo.save(resultado.get());
        }

        return "redirect:/generos/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model ui) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if (resultado.isPresent()) {
            ui.addAttribute("genero", resultado.get());  // ✅ Singular
            return "/generos/delete";
        }

        return "redirect:/generos/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") long id) {
        generoRepo.deleteById(id);
        return "redirect:/generos/list";
    }
}
