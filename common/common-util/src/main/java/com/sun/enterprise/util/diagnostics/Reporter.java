/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.enterprise.util.diagnostics;

import java.io.*;
import java.util.*;
//Bug 4677074 begin
import java.util.logging.Logger;
import java.util.logging.Level;
import com.sun.logging.LogDomains;
//Bug 4677074 end

/** Wrapper class for ReporterImpl(s)
 ** Will create & track ReporterImpl's if desired
 ** provides an easy-to-type selection of static methods for calling the default reporter
 **/
public class Reporter implements IReporterEnum
{
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////     Configurating Stuff  //////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
//Bug 4677074 begin
    static Logger _logger=LogDomains.getLogger(LogDomains.UTIL_LOGGER);
//Bug 4677074 end

    /** Gain access to the default ReporterImpl object.
     * @return The default ReporterImpl object.
     */    
	public static ReporterImpl get()
	{
		return getDefaultReporter();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /** Change the message severity level that will trigger output
         * @param level The verbosity increases as the level decresses. The value 1000 shutdown the Reporter.
         */        
	public static void setSeverityLevel(int level)
	{
		getDefaultReporter().setSeverityLevel(level);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /** Change the message severity level that will trigger output
         * @param level A textual description of the severity level. Key values include: ALL,NOISY,EVERYTHING,ON",MONDO,YES,TRUE,DUMP,MAX [which turn the message volume up] and NO,OFF,FALSE,QUIET,MIN [which turn the volume down] as well as the sveroty level names.
         * 			return DISABLED;
         */        
	public static void setSeverityLevel(String level)
	{
		getDefaultReporter().setSeverityLevel(level);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /** Determine the current severity level.
         * @return the severity levels integer value.
         */        
	public static int getSeverityLevel()
	{
		return getDefaultReporter().getSeverityLevel();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /** Determine the current severity level.
         * @return the severity levels String value.
         */        
	public static String getSeverityLevelString()
	{
		return getDefaultReporter().getSeverityLevelString();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////     Message Writing      //////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /** Messages output at the lowest severity level.
         * @param o The text of the message will be the output of this object's toString()
         */        
	public static void verbose(Object o)
	{
		getDefaultReporter().verbose(o);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /**
         * @see verbose(Object)
         */        
	public static void info(Object o)
	{
		getDefaultReporter().info(o);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /**
         * @see verbose(Object)
         */        
	public static void warn(Object o)
	{
		getDefaultReporter().warn(o);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /**
         * @see verbose(Object)
         */        
	public static void warning(Object o)
	{
		getDefaultReporter().warning(o);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /**
         * @see verbose(Object)
         */        
	public static void error(Object o)
	{
		getDefaultReporter().error(o);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /**
         * @see verbose(Object)
         */        
	public static void critical(Object o)
	{
		getDefaultReporter().critical(o);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /**
         * @see verbose(Object)
         */        
	public static void crit(Object o)
	{
		getDefaultReporter().crit(o);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /** Dump information via the Reporter about an object
         * @param o The object to dump information about.
         * @param s A string to preceed the object dump
         */        
	public static void dump(Object o, String s)
	{
		getDefaultReporter().dump(o, s);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /** Print a string at "DUMP" severity level
         * @param s The message to report.
         */        
	public static void dump(String s)
	{
		getDefaultReporter().dump(s);
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
        /** Dump an object, with no "extra string" associated with the output.
         * @param o The object to dump
         */        
	public static void dump(Object o)
	{
		getDefaultReporter().dump(o);
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////     ASSERTION STUFF      //////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	// TBD:  Nice to have the insist's print the CallerInfo stuff.
	// lots-o-typing needed!!

        /** Provide an insist mechanism.  The assertion will be checked if the severity level is set to ASSERT or lower.
         * @param s The assertion will fail and cause a message and throw a run-time exception is the
         * argument is null or is zero length.
         */        
	public static void insist(String s)
	{
		getDefaultReporter().insist(s);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /** Provide an insist mechanism.  The assertion will be checked if the severity level is set to ASSERT or lower.
         * @param checkme The assertion will fail and cause a message and throw a run-time exception is the
         * argument is null or is zero length.
         * @param s Additional information to add to the assertion's text message.
         */        
	public static void insist(String checkme, String s)
	{
		getDefaultReporter().insist(checkme, s);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String)
         */        
	public static void insist(boolean b)
	{
		getDefaultReporter().insist(b);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String,String)
         */        
	public static void insist(boolean b, String s)
	{
		getDefaultReporter().insist(b, s);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String)
         */        
	public static void insist(Object o)
	{
		getDefaultReporter().insist(o);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String,String)
         */        
	public static void insist(Object o, String s)
	{
		getDefaultReporter().insist(o, s);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String)
         */        
	public static void insist(double z)
	{
		getDefaultReporter().insist(z);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String,String)
         */        
	public static void insist(double z, String s)
	{
		getDefaultReporter().insist(z, s);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String)
         */        
	public static void insist(long l)
	{
		getDefaultReporter().insist(l);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * @see insist(String,String)
         */        
	public static void insist(long l, String s)
	{
		getDefaultReporter().insist(l, s);
	}

  
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////       PRIVATE STUFF      //////////////////////////////////////////////////
	///////////////////////////                          //////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	protected Reporter()
	{
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	private static void debug(String s)
	{
		if(doDebug)
//Bug 4677074			System.err.println(s);
//Bug 4677074 begin
			_logger.log(Level.WARNING,s);
//Bug 4677074 end
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	private static ReporterImpl getDefaultReporter()
	{
		if(defaultReporter == null)
		{
//Bug 4677074 			System.err.println("Internal Error in Reporter -- couldn't find default reporter!");//NOI18N
//Bug 4677074 begin
			_logger.log(Level.WARNING,"iplanet_util.internal_error");
//Bug 4677074 end
			defaultReporter = new ReporterImpl();
		}

		return defaultReporter;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	private static			ReporterImpl	defaultReporter	= null;
	private static final	boolean			doDebug			= false;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	static
	{
		CallerInfo.addToGlobalIgnore(new Reporter());
		
		defaultReporter = new ReporterImpl();

		if(defaultReporter == null)
		{
//Bug 4677074			System.err.println("Internal Error in Reporter -- couldn't make default reporter!");//NOI18N
//Bug 4677074 begin
			_logger.log(Level.WARNING,"iplanet_util.internal_error");
		}
	}
}

