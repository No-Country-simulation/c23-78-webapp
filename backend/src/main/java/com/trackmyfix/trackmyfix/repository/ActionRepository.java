package com.trackmyfix.trackmyfix.repository;

import com.trackmyfix.trackmyfix.entity.Action;
import com.trackmyfix.trackmyfix.entity.ActionUser;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepository extends CrudRepository<Action,Long> {

}
