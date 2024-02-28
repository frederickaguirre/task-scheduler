package com.leanstacks.ws.infraestructure.shared;

import java.io.Serializable;

public interface RestConverter<R extends Serializable, E extends Serializable> {

	default E mapToEntity(final R rest) {
		throw new UnsupportedOperationException();
	}

	default R mapToDTO(final E entity) {
		throw new UnsupportedOperationException();
	}
}
