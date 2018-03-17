package fr.epsi.myEpsi.models;

/**
 * Status of an ad
 */
public enum EStatus {
	TEMPORAIRE,
	VALIDE,
	VENDUE,
	SUPPRIMEE;

	public static EStatus getStatus(int value) {
		EStatus status;

		switch (value) {
			default:
			case 0:
				status = TEMPORAIRE;
				break;
			case 1:
				status = VALIDE;
				break;
			case 2:
				status = VENDUE;
				break;
			case 3:
				status = SUPPRIMEE;
				break;
		}

		return status;
	}
}
