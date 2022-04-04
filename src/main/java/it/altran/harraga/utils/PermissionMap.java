package it.altran.harraga.utils;

import it.altran.harraga.model.User;
import it.altran.harraga.model.User.Ruolo;

import java.util.ArrayList;
import java.util.HashMap;

public class PermissionMap {

	HashMap<Ruolo, Permission> permissonMap;
	static PermissionMap instance = null;

	public HashMap<Ruolo, Permission> getPermissonMap() {
		return permissonMap;
	}

	public void setPermissonMap(HashMap<Ruolo, Permission> permissonMap) {
		this.permissonMap = permissonMap;
	}

	private PermissionMap() {

		permissonMap = new HashMap<Ruolo, Permission>();

		// CPA
		ArrayList<Permission.sezioni> read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.PENALE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.SOCIALE);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		ArrayList<Permission.sezioni> write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ANAGRAFICA);
		write.add(Permission.sezioni.AMMINISTRATIVA);
		write.add(Permission.sezioni.STORIA);
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.SANITARIA);
		write.add(Permission.sezioni.LAVORO);
		write.add(Permission.sezioni.COMPETENZE);
		write.add(Permission.sezioni.DESIDERI);
		write.add(Permission.sezioni.SOCIALE);

		Permission permessi = new Permission(read, write);
		permissonMap.put(Ruolo.CPA, permessi);

		// RESP_SEC_ACC
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.SOCIALE);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ANAGRAFICA);
		write.add(Permission.sezioni.AMMINISTRATIVA);
		write.add(Permission.sezioni.STORIA);
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.SANITARIA);
		write.add(Permission.sezioni.LAVORO);
		write.add(Permission.sezioni.COMPETENZE);
		write.add(Permission.sezioni.DESIDERI);
		write.add(Permission.sezioni.SOCIALE);


		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.RESP_SEC_ACC, permessi);

		// TUTORE
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.SOCIALE);
		write.add(Permission.sezioni.COMPETENZE);
		write.add(Permission.sezioni.DESIDERI);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.TUTORE, permessi);

		// COMUNE_MARSALA
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.PENALE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ANAGRAFICA);
		write.add(Permission.sezioni.AMMINISTRATIVA);
		write.add(Permission.sezioni.STORIA);
		write.add(Permission.sezioni.SANITARIA);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.COMUNE_MARSALA, permessi);

		// REF_LEGALE
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.AMMINISTRATIVA);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.REF_LEGALE, permessi);

		// ASP
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.SANITARIA);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.SANITARIA);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.ASP, permessi);

		// CPIA
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.SOCIALE);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.LAVORO);
		write.add(Permission.sezioni.SOCIALE);
		write.add(Permission.sezioni.COMPETENZE);
		write.add(Permission.sezioni.DESIDERI);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.CPIA, permessi);

		// AGENZIA_LAVORO
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.SOCIALE);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.LAVORO);
		write.add(Permission.sezioni.COMPETENZE);
		write.add(Permission.sezioni.DESIDERI);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.AGENZIA_LAVORO, permessi);

		// ASSOCIAZIONI
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.SOCIALE);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.SOCIALE);
		write.add(Permission.sezioni.COMPETENZE);


		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.ASSOCIAZIONI, permessi);


		// ASSOCIAZIONI
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.SOCIALE);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.SOCIALE);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.ASSOCIAZIONI, permessi);


		// USSM
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.SOCIALE);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.PENALE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.PENALE);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.USSM, permessi);


		// SCUOLA_ITALIANO
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		write.add(Permission.sezioni.ISTRUZIONE);
		write.add(Permission.sezioni.COMPETENZE);

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.SCUOLA_ITALIANO, permessi);


		// GARANTE
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.STORIA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();
		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.GARANTE, permessi);


		// MINORE
		read = new ArrayList<Permission.sezioni>();
		read.add(Permission.sezioni.ANAGRAFICA);
		read.add(Permission.sezioni.AMMINISTRATIVA);
		read.add(Permission.sezioni.ISTRUZIONE);
		read.add(Permission.sezioni.SANITARIA);
		read.add(Permission.sezioni.LAVORO);
		read.add(Permission.sezioni.COMPETENZE);
		read.add(Permission.sezioni.DESIDERI);
		read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

		write = new ArrayList<Permission.sezioni>();

		permessi = new Permission(read, write);
		permissonMap.put(Ruolo.MINORE, permessi);

		//TRIBUNALE
        read = new ArrayList<Permission.sezioni>();
        read.add(Permission.sezioni.ANAGRAFICA);
        read.add(Permission.sezioni.AMMINISTRATIVA);
        read.add(Permission.sezioni.ISTRUZIONE);
        read.add(Permission.sezioni.SANITARIA);
        read.add(Permission.sezioni.LAVORO);
        read.add(Permission.sezioni.COMPETENZE);
        read.add(Permission.sezioni.DESIDERI);
        read.add(Permission.sezioni.PERMESSO_SOGGIORNO);

        write = new ArrayList<Permission.sezioni>();

        permessi = new Permission(read, write);
        permissonMap.put(Ruolo.TRIBUNALE, permessi);


	}

	public static PermissionMap getInstance() {
		if (instance == null)
			instance = new PermissionMap();

		return instance;
	}

	/*
	 * inserire tutti i ruoli che possono vedere tutte le cartelle sociali
	 */
	public boolean isIstitutional(Ruolo ruolo) {
	    /*
		if (ruolo == User.Ruolo.COMUNE_MARSALA || ruolo == User.Ruolo.CPA || ruolo == User.Ruolo.RESP_SEC_ACC || ruolo == User.Ruolo.ASP || ruolo == User.Ruolo.CPIA || ruolo == User.Ruolo.USSM
				|| ruolo == User.Ruolo.GARANTE || ruolo == User.Ruolo.SCUOLA_ITALIANO)
			return true;
		return false;
		*/
        if (ruolo == Ruolo.COMUNE_MARSALA  || ruolo == Ruolo.ASP || ruolo == Ruolo.CPIA || ruolo == Ruolo.USSM
            || ruolo == Ruolo.GARANTE || ruolo == Ruolo.SCUOLA_ITALIANO)
            return true;
        return false;
	}

	/*
	 * inserire tutti i ruoli che possono creare le cartelle sociali
	 */
	public boolean canCreate(Ruolo ruolo) {
		if (ruolo == Ruolo.CPA || ruolo == Ruolo.RESP_SEC_ACC || ruolo == Ruolo.COMUNE_MARSALA)
			return true;
		return false;
	}

	/*
	 */
	public boolean canRead(Ruolo ruolo, Permission.sezioni sezione) {
		if (permissonMap.get(ruolo).read.contains(sezione))
			return true;
		return false;
	}

	/*
	 */
	public boolean canWrite(Ruolo ruolo, Permission.sezioni sezione) {
		if (permissonMap.get(ruolo).write.contains(sezione))
			return true;
		return false;
	}

}
