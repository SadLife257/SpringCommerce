package com.midterm.springcommerce.Services;

import java.util.Optional;

public interface GeneralService<T, ID> {
	Iterable<T> findAll();

    Optional<T> findById(ID id);

    T save(T t);

    void remove(ID id);
}
