package eu.ensup.gestionetablissement.domain;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum Roles
{
    STUDENT,
    TEACHER,
    MANAGER,
    DIRECTOR;

    public Roles getRoleByName(String roleName)
    {
        for(Roles role : Roles.values())
            if( role.name().equals(roleName.toUpperCase()) )
                return role;

        log.error("The role \""+roleName+"\" doesn't exist !");
        return null;
    }
}
