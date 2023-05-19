/*
 * Copyright (c) 2020 Comosoft GmbH
 * All rights reserved.
 */

package com.comosoft.lago.task1;

import java.sql.Connection;
import java.util.List;

/**
 * Data provider
 */
public interface IDataProvider
{
    /**
     * Returns all available projects. The projects are sorted by name.
     * @param conn 
     * 
     * @return the projects (NEVER NULL)
     */
    List<Project> getAllProjects(Connection conn);
    
    /**
     * Returns the projects assigned to a user. The projects are sorted by name.
     * @param conn 
     * 
     * @param userName
     *            the user name
     * @return the projects (NEVER NULL)
     */
    List<Project> getProjectsByUser( Connection conn, String userName );
}
