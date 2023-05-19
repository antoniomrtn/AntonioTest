/*
 * Copyright (c) 2020 Comosoft GmbH
 * All rights reserved.
 */

package com.comosoft.lago.task1;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test1
{
	// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL      = "jdbc:h2:$path/com/comosoft/lago/task1/db";
    
    // Database credentials
    static final String USER = "sa";
    static final String PASS = "sa";
    
    Connection conn;
    IDataProvider dataProvider = new ProjectLoader();
    
    @BeforeEach
    void setUp() throws Exception
    {
    	
    	try
        {
            final File currentPath =
                new File( Starter.class.getProtectionDomain().getCodeSource().getLocation().getPath() );
            
            Class.forName( JDBC_DRIVER );
            
            String finalDBUrl = DB_URL.replace( "$path", currentPath.getAbsolutePath() );
            conn = DriverManager.getConnection( finalDBUrl, USER, PASS );
        }
        catch ( Throwable e )
        {
            throw new RuntimeException( "Unexpected error ", e );
        }
    }
    
    @Test
    void testGetAllProjects()
    {
    	List<Project> projects = dataProvider.getAllProjects(conn);
    	assertEquals(projects.size(), 10);
    }
    
    @Test
    void testGetProjectByUserName()
    {
    	List<Project> projectsByCarl = dataProvider.getProjectsByUser(conn, "Carl");
    	assertEquals(projectsByCarl.size(), 3);
    }
    
}
