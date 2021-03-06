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
package com.axway.ats.core.dbaccess;

public class MysqlColumnDescription extends ColumnDescription {

    public MysqlColumnDescription( String name,
                                   String type ) {
        super( name, type );
    }

    // for information about all data types in MySQL
    // http://dev.mysql.com/doc/refman/5.7/en/data-types.html

    @Override
    public boolean isTypeNumeric() {

        String normalizedType = type.toLowerCase();

        return normalizedType.contains( "int" ) || normalizedType.startsWith( "numeric" )
               || normalizedType.startsWith( "decimal" ) || normalizedType.startsWith( "float" )
               || normalizedType.startsWith( "double" ) || normalizedType.startsWith( "bit" )
               || normalizedType.startsWith( "real" );
    }

    @Override
    public boolean isTypeBinary() {
        String normalizedType = type.toLowerCase();
        return normalizedType.contains( "blob" ) || normalizedType.contains( "binary" );
    }
}
