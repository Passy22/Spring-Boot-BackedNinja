package com.example.backedninja.service;

import java.util.List;

import com.example.backedninja.entity.Contact;
import com.example.backedninja.model.ContactModel;

public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contactModel);
	public abstract List<ContactModel> listAllContacts();
	public abstract Contact findContactById(int id);
	public abstract void removeContact(int id);
	public abstract ContactModel findContactByIdModel(int id);
	

}
