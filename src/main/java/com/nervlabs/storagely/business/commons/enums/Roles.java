package com.nervlabs.storagely.business.commons.enums;

import java.util.HashSet;
import java.util.Set;

public enum Roles {
	STORER("Almacenista"), ADMIN("Administrador"), VENDOR("Vendedor"), UNDEFINED("#");

	private String rolEsp;
	
	Roles(String rolEsp) {
		this.rolEsp = rolEsp;
	}
	
	static public Set<String> getRoles() {
		 Set<String> rolesEs = new HashSet<>();
		 
		 for( Roles rol : values()) {
			 
			 if(!(rol.rolEsp.contentEquals("#")))
				 rolesEs.add(rol.rolEsp);
		 }
		 
		 return rolesEs;
	}
	
	static public Roles getRoles(String rolEsp)
    {
        for(Roles rol : values())
        {
            if(rol.rolEsp.equals(rolEsp))
                return rol;
        }
        
        return UNDEFINED;
    }
}
