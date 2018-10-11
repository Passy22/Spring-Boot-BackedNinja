package com.example.backedninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.backedninja.constant.*;
import com.example.backedninja.model.ContactModel;
import com.example.backedninja.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	@Qualifier("contactService")
	ContactService contactService;
	
	private static final Log Logger = LogFactory.getLog(ContactController.class);
	
	
	// Para la editación de los contactos en la aplicación vamos a usar este metodo para recoger por id un contacto
	// Y que realice dos funciones. Hay que tener en cuenta que la logica de negocio la va a tener el servicio en un 
	// metodo separado ya que cada metodo tiene que tener la función más concisa posible
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", required=false) int id, 
									Model model) {
		ContactModel contact = new ContactModel();
				
		if(id != 0) {
			contact = contactService.findContactByIdModel(id);			
		}
		// Con esto ya tendríamos nuestro contacto en el controlador
		
		// Como siepmre agregamos el objeto vacío a la vista ya que vamos a trabajar con objetos dentro de ella
		// Creando el modelo y dandole de nombre contactModel
		model.addAttribute("contactModel", contact);
		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showContact";
	}
	
	@PostMapping("/addContact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
							Model model) {
		
		Logger.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
				
		if(null !=contactService.addContact(contactModel)) {
			model.addAttribute("result",1);
		}else {
			model.addAttribute("result",0);
		}
		
		return "redirect:/contacts/showContact";
		
	}
	
	@GetMapping("/showContact")
	public ModelAndView showContacts() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAllContacts());
		
		return mav;
		
	}
	
	@GetMapping("/removeContact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		return showContacts();
		
	}

}
