/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.enterprise.util.io;

import com.sun.enterprise.universal.i18n.LocalStringsImpl;
import com.sun.enterprise.universal.io.SmartFile;
import java.io.*;
import java.io.File;

/**
 * The hierarchy of directories above a running DAS or server instance can get
 * messy to deal with -- thus this class.  This class is a bullet-proof holder of
 * that information.
 * 
 * Instances and DAS are arranged differently:
 * 
 * examples:
 * DAS
 * domainDir = getServerDir() == C:/glassfishv3/glassfish/domains/domain1
 * domainsDir = getServerParentDir() == C:/glassfishv3/glassfish/domains
 * grandparent-dir is meaningless
 * 
 * Instance
 * instanceDir = getServerDir() == C:/glassfishv3/glassfish/nodeagents/mymachine/instance1
 * agentDir = getServerParentDir() == C:/glassfishv3/glassfish/nodeagents/mymachine
 * agentsDir = getServerGrandParentDir() == C:/glassfishv3/glassfish/nodeagents
 *
 * Currently in all cases the name of the serverDir is the name of the server --
 * by our definition.
 *
 * THIS CLASS IS GUARANTEED THREAD SAFE
 *
 * Contract:  Caller is supposed to NOT call methods on an instance of this class.
 * It's "advanced java" to be able to do that anyway.
 * I don't allow half-baked data out.  It's all or none.  The "valid" flag
 * is checked and if invalid -- all methods return null.  They don't throw an Exception
 * because the caller is not supposed to call the methods - it would just annoy
 * the caller.
 *
 * @author Byron Nevins
 * @since 3.1
 * Created: April 19, 2010
 */

public class ServerDirs {
    public ServerDirs(File leaf) throws IOException {
        if(leaf == null)
            throw new IllegalArgumentException(strings.get("ServerDirs.nullArg", "ServerDirs.ServerDirs()"));

        if(!leaf.isDirectory())
            throw new IOException(strings.get("ServerDirs.badDir", leaf));

        serverDir = SmartFile.sanitize(leaf);
        serverName = serverDir.getName();

        // note that serverDir has been "smart-filed" so we don't have to worry
        // about getParentFile() which has issues with relative paths...
        parentDir = serverDir.getParentFile();

        if(parentDir == null || !parentDir.isDirectory())
            throw new IOException(strings.get("ServerDirs.badParentDir", serverDir));

        // grandparent dir is optional.  It can be null for DAS for instance...
        grandParentDir = parentDir.getParentFile();
        valid = true;
    }

    public final String getServerName() {
        if(!valid)
            return null;

        return serverName;
    }

    public final File getServerDir() {
        if(!valid)
            return null;
        return serverDir;
    }

    public final File getServerParentDir() {
        if(!valid)
            return null;
        return parentDir;
    }

    public final File getServerGrandParentDir() {
        if(!valid)
            return null;
        return grandParentDir;
    }
    ///////////////////////////////////////////////////////////////////////////
    ///////////           All Private Below           /////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    private final String serverName;
    private final File serverDir;
    private final File parentDir;
    private final File grandParentDir;
    private boolean valid;
    // Can be shared among classes in the package
    static final LocalStringsImpl strings = new LocalStringsImpl(ServerDirs.class);
}
