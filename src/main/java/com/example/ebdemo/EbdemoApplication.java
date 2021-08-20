package com.example.ebdemo;

import com.example.ebdemo.model.User;
import io.ebean.DB;
import io.ebean.Database;
import io.ebean.SqlUpdate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class EbdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(EbdemoApplication.class, args);
		runInitScript();
		insertUser();
		findUser();
		insertAndDeleteUser();
	}
	public static void runInitScript(){

		Database database = DB.getDefault();
		try {
			final URL url = new ClassPathResource("dbInit.sql").getURL();
			database.script().run(url);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}
	public static void insertUser(){
		User user = new User("Query User");
		user.setEmail("qu@example.com");
		Database database = DB.getDefault();
		database.save(user);
		System.out.println("User with id: " + user.getId() + " created!");
	}

	public static void insertAndDeleteUser(){
		User user = new User("Robert");
		user.setEmail("robby@example.com");
		Database database = DB.getDefault();
		database.save(user);
		System.out.println("User with id: " + user.getId() + " created!");

		User user1 = database.find(User.class, user.getId());
		System.out.println("Found user: " + user1);
		database.delete(user1);
	}

	public static void findUser(){
		Database database = DB.getDefault();
		User user = database.find(User.class)
				.select("name")
				.where()
				.eq("email", "qu@example.com")
				.findOne();
		System.out.println("Found user: " + user.getName());
	}
	public static void cleanUp(){
		Database database = DB.getDefault();
		String sql = "delete from eb_user";
		SqlUpdate sqlUpdate = database.sqlUpdate(sql);
		database.execute(sqlUpdate);

	}

}
