/*
 * #%L
 * TAM TOOLS
 * %%
 * Copyright (C) 2020 Adobe
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package com.adobe.tam.tools.core.replication;

import com.day.cq.replication.ReplicationContentFilter;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//@Component
public class SampleReplicationContentFilter implements ReplicationContentFilter  {
    private static final Logger log = LoggerFactory.getLogger(SampleReplicationContentFilter.class);

    private List<String> filteredPaths = new CopyOnWriteArrayList<String>();
    
    public SampleReplicationServiceConfig config;

    
    @Override
    public boolean accepts(final Node node) {
        try {
            if (node.hasProperty("authorOnly")) {
                // Example: Reject nodes that have a property "authorOnly = true"
                final boolean authorOnly = node.getProperty("authorOnly").getBoolean();

                if (!authorOnly) {
                    // Maintain the filteredPaths list whenever accepts returns false
                    filteredPaths.add(node.getPath());
                    return false;
                }
            }
        } catch (RepositoryException e) {
            log.error("Repository exception occurred. Do not accept this Node. {}", e);
            return false;
        }

        // Default behavior is to accept
        return true;
    }

    @Override
    public boolean accepts(final Property property) {
		boolean shouldReplicate = true;

		try {
			if (ArrayUtils.contains(config.getStringValues(), property.getName())) {
				log.info("Found filtered property '{}' at path{} ",property.getName() ,property.getPath());
				shouldReplicate = false;
			}
		}catch(RepositoryException repoExc){
			log.error("Repository exception occurred while getting property",repoExc);
		}

		return shouldReplicate;
    	
    }
    
    

    @Override
    public boolean allowsDescent(final Node node) {
        // Optionally include descendents ..

        // This example does not allow descent if the node itself is not allowed
        return this.accepts(node);
    }

    @Override
    public List<String> getFilteredPaths() {
        return this.getFilteredPaths();
    }


    //Setter for configuration
	public void setConfig(SampleReplicationServiceConfig config) {
		this.config=config;	
	}   
	
}
