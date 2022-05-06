package com.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.StatusTitulo;
import com.cobranca.model.Titulo;
import com.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

//métodos usados na tela de cadastro de titulo
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private Titulos titulos;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors,RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem","Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	//método usado na edição do registro
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		
		@SuppressWarnings("deprecation")
		Titulo titulo = titulos.getOne(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	
//métodos usados na tela de pesquisa de titulo
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos",todosTitulos);
		return mv;
	}
	
	
// métodos usados no icone de excluir
	@RequestMapping(value= {"codigo"},method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo,RedirectAttributes attributes) {
		titulos.deleteById(codigo);
		
		attributes.addFlashAttribute("mensagem","Título excluído com sucesso!");
		
		return "redirect:/titulos";
	}
	
}
