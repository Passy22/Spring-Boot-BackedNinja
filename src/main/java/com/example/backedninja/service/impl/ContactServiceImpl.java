package com.example.backedninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.backedninja.converter.ContactConverter;
import com.example.backedninja.entity.Contact;
import com.example.backedninja.model.ContactModel;
import com.example.backedninja.repository.ContactRepository;
import com.example.backedninja.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		return contactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactModels = new ArrayList<ContactModel>();
		
		for(Contact contact: contacts) {
			contactModels.add(contactConverter.convertContact2ContactModel(contact));
			
		}
			
		return contactModels;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContact2ContactModel(findContactById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(null != contact) {
			contactRepository.delete(contact);			
		}
	}
}