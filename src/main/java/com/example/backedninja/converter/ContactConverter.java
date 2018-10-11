package com.example.backedninja.converter;

import org.springframework.stereotype.Component;

import com.example.backedninja.entity.Contact;
import com.example.backedninja.model.ContactModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactConverter.
 */
@Component("contactConverter")
public class ContactConverter {
	
	/**
	 * Convert contact model 2 contact.
	 *
	 * @param contactModel the contact model
	 * @return the contact
	 */
	public Contact convertContactModel2Contact (ContactModel contactModel) {
		
		Contact contact = new Contact();
		
		contact.setCity(contactModel.getCity());
		contact.setFirstName(contactModel.getFirstName());
		contact.setId(contactModel.getId());
		contact.setLastName(contactModel.getLastName());
		contact.setTelephone(contactModel.getTelephone());
		
		return contact;
		
	}
	
	/**
	 * Convert contact 2 contact model.
	 *
	 * @param contact the contact
	 * @return the contact model
	 */
	public ContactModel convertContact2ContactModel (Contact contact) {
		
		ContactModel contactModel = new ContactModel();
		
		contactModel.setCity(contact.getCity());
		contactModel.setFirstName(contact.getFirstName());
		contactModel.setId(contact.getId());
		contactModel.setLastName(contact.getLastName());
		contactModel.setTelephone(contact.getTelephone());
		
		return contactModel;
	}

}
