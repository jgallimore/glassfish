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

package com.sun.appserv.ha.spi;

/**
 * A class that holds additional metadata than a SimpleMetaData
 * 
 * @see BatchBackingStore
 */
public final class BatchMetadata extends SimpleMetadata {

    private String appId;

    private String id;

    /**
     * Construct a Metadata object
     * 
     * @param version
     *            The version of the data. A freshly created state has a version ==
     *            0
     * @param lastAccessTime
     *            the last access time of the state. This must be used in
     *            conjunction with getMaxInactiveInterval to determine if the
     *            state is idle enough to be removed.
     * @param maxInactiveInterval
     *            the maximum time that this state can be idle in the store
     *            before it can be removed.
     * @param data
     *            The data
     * @param extraData
     *            Some more data
     */
    public BatchMetadata(String appId, String id, long version,
            long lastAccessTime, long maxInactiveInterval, byte[] data,
            String extraData) {
        super(version, lastAccessTime, maxInactiveInterval, data, extraData);
        this.appId = appId;
        this.id = id;
    }

    /**
     * Return the application id
     * 
     * @return the application id
     * @see BackingStoreFactory
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Return the id of the metaData
     * 
     * @return the id of the metaData
     */
    public String getId() {
        return id;
    }

}
