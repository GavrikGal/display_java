package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.User;

/**
 * интерфейс пользователей для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * поиск пользователя по фамилии.
	 *
	 * @param firstName фамилия
	 * @return найденный пользователь или null
	 */
	User findByFirstName(String firstName);

	/**
	 * поиск пользователя по логину.
	 *
	 * @param userName логин
	 * @return найденный пользователь или null
	 */
	User findByUserName(String userName);

}
