package fr.epsi.myEpsi.models.beans;

import fr.epsi.myEpsi.models.EStatus;

import java.sql.Date;
import java.util.Calendar;

public class AdDefault extends Ad {

	/**
	 * Constructor
	 */
	public AdDefault() {
		super();

		Date date = new Date(Calendar.getInstance().getTimeInMillis());

		this.setId(0);
		this.setTitle("title");
		this.setDescription("description");
		this.setStatus(EStatus.TEMPORARY);
		this.setSeller("seller");
		this.setPrice(0f);
		this.setSoldAt(date);
		this.setViewNumber(0);
		this.setModificationAt(date);
	}
}
