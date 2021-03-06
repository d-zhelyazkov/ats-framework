/*
 * Copyright 2017 Axway Software
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.axway.ats.core.dbaccess.cassandra;

import java.sql.Driver;
import java.util.Map;

import javax.sql.DataSource;

import com.axway.ats.common.dbaccess.CassandraKeys;
import com.axway.ats.common.dbaccess.DbKeys;
import com.axway.ats.core.dbaccess.DbConnection;

/**
 * Connection descriptor for Cassandra databases 
 */
public class DbConnCassandra extends DbConnection {

    private boolean            allowFiltering;

    /**
     * Default DB port
     */
    private static final int   DEFAULT_PORT    = 9042;

    public static final String DATABASE_TYPE = "CASSANDRA";

    /**
     * Constructor
     * 
     * @param host host
     * @param db database name
     * @param user user name
     * @param password user password
     * @param customProperties map of custom properties
     */
    public DbConnCassandra( String host, String db, String user, String password,
                            Map<String, Object> customProperties ) {

        super( DATABASE_TYPE, host, db, user, password, customProperties );
    }

    public int getPort() {

        return this.port;
    }

    public boolean isAllowFiltering() {

        return this.allowFiltering;
    }

    @Override
    protected void initializeCustomProperties( Map<String, Object> customProperties ) {

        this.port = DEFAULT_PORT;

        if( customProperties != null && !customProperties.isEmpty() ) {
            //read the port if such is set
            Object portValue = customProperties.get( DbKeys.PORT_KEY );
            if( portValue != null ) {
                this.port = ( Integer ) portValue;
            }

            Object allowFilteringValue = customProperties.get( CassandraKeys.ALLOW_FILTERING );
            if( allowFilteringValue != null ) {
                this.allowFiltering = ( Boolean ) allowFilteringValue;
            }
        }
    }

    @Override
    public DataSource getDataSource() {

        return null;
    }

    @Override
    public Class<? extends Driver> getDriverClass() {

        return null;
    }

    @Override
    public String getURL() {

        return ":cassandra:" + host + ":" + port + "/" + db;
    }

    @Override
    public String getDescription() {

        StringBuilder description = new StringBuilder( "Cassandra connection to " );
        description.append( host );
        description.append( ":" ).append( port );
        description.append( "/" ).append( db );

        return description.toString();
    }

    @Override
    public void disconnect() {

    }
}
