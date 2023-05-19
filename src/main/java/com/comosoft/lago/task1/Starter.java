/*
 * Copyright (c) 2020 Comosoft GmbH
 * All rights reserved.
 */

package com.comosoft.lago.task1;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Main class
 */
public class Starter
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL      = "jdbc:h2:$path/com/comosoft/lago/task1/db";
    
    // Database credentials
    static final String USER = "sa";
    static final String PASS = "sa";
    
    /**
     * Creates a database connection
     * 
     * @return the database connection (NEVER NULL)
     */
    private static Connection createDatabaseConnection()
    {
        try
        {
            final File currentPath =
                new File( Starter.class.getProtectionDomain().getCodeSource().getLocation().getPath() );
            
            Class.forName( JDBC_DRIVER );
            
            String finalDBUrl = DB_URL.replace( "$path", currentPath.getAbsolutePath() );
            System.out.println( "Connecting to database... " + finalDBUrl );
            return DriverManager.getConnection( finalDBUrl, USER, PASS );
        }
        catch ( Throwable e )
        {
            throw new RuntimeException( "Unexpected error ", e );
        }
    }
    
    /**
     * Main class
     * 
     * @param args
     *            the parameters
     */
    public static void main( String[] args )
    {
        try ( Connection conn = createDatabaseConnection() )
        {
        	IDataProvider dataProvider = new ProjectLoader();
        	List<Project> projects = dataProvider.getAllProjects(conn);
        	projects.stream().forEach(System.out::print);
        	System.out.println("\n");
        	List<Project> projectsByUser = dataProvider.getProjectsByUser(conn, "Carl");
        	projectsByUser.stream().forEach(System.out::print);
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }
    }
    
}
