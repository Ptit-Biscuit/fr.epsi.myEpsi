package fr.epsi.myEpsi.models;

/**
 * Status of an ad
 */
public enum EStatus {
	TEMPORARY,
	VALID,
	SOLD,
	DELETE;

	public static EStatus getStatus(int value) {
		EStatus status;

		switch (value) {
			default:
			case 0:
				status = TEMPORARY;
				break;
			case 1:
				status = VALID;
				break;
			case 2:
				status = SOLD;
				break;
			case 3:
				status = DELETE;
				break;
		}

		return status;
	}
}
