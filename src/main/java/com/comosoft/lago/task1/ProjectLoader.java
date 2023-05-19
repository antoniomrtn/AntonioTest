/*
 * Copyright (c) 2020 Comosoft GmbH
 * All rights reserved.
 */

package com.comosoft.lago.task1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provider all project load operations.
 */
public class ProjectLoader implements IDataProvider
{
    /**
     * Constructor
     * 
     * @param dataProvider
     *            the data provider
     */
    public ProjectLoader()
    {}
    
    /**
     * Returns all available projects. The projects are sorted by name.
     * 
     * @return the projects (NEVER NULL)
     */
    @Override
	public List<Project> getAllProjects(Connection conn) {
        List<Project> projects = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM project ORDER BY name");
			 while (resultSet.next()) {	     
				 Project p = new Project();
				 p.setId(resultSet.getInt("id"));
				 p.setName(resultSet.getString("name"));
				 projects.add(p);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}
    
    /**
     * Returns the projects assigned to a user. The projects are sorted by name.
     * 
     * @param userName
     *            the user name
     * @return the projects (NEVER NULL)
     */
	@Override
	public List<Project> getProjectsByUser(Connection conn, String userName) {
		List<Project> projects = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT p.* FROM project p INNER JOIN project_assignment pa on p.id = pa.project_id INNER JOIN user u on u.id = pa.user_id WHERE u.name = ? order by p.name");
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			 while (resultSet.next()) {	     
				 Project p = new Project();
				 p.setId(resultSet.getInt("id"));
				 p.setName(resultSet.getString("name"));
				 projects.add(p);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	
}
