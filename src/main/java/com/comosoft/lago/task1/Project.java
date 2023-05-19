/*
 * Copyright (c) 2020 Comosoft GmbH
 * All rights reserved.
 */

package com.comosoft.lago.task1;

/**
 * The project entity
 */
public class Project
{
    /** the project id */
    private int    id;
    /** the project name */
    private String name;
    
    /**
     * Gets {@link #id}.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Sets {@link #id}.
     */
    public void setId( int id )
    {
        this.id = id;
    }
    
    /**
     * Gets {@link #name}.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets {@link #name}.
     */
    public void setName( String name )
    {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}
    
    
}
