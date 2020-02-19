package com.adobe.tam.tools.core.replication;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationContentFilter;
import com.day.cq.replication.ReplicationContentFilterFactory;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(name="Content Filter", service=ReplicationContentFilterFactory.class,immediate=true, configurationPid="com.adobe.tam.tools.core.replication")
@Designate(ocd = SampleReplicationServiceConfig.class)

public class SampleReplicationContentFilterFactory implements ReplicationContentFilterFactory {
	private static final Logger log = LoggerFactory.getLogger(SampleReplicationContentFilterFactory.class);
	private SampleReplicationContentFilter internalFilter; // = new SampleReplicationContentFilter();
	
	
	@Activate
	@Modified
	//protected void activate(ComponentContext context) {
	protected void activate(final SampleReplicationServiceConfig config ) {
		log.info("Activated: SampleReplicationContentFilterFactory");
		internalFilter = new SampleReplicationContentFilter();
		internalFilter.setConfig(config);
	}
	
	@Override
	public ReplicationContentFilter createFilter(ReplicationAction action) {
		ReplicationContentFilter filter = null;
		
		if (ReplicationActionType.ACTIVATE.equals(action.getType())) {
			filter = internalFilter;			
		}
		log.info("Filter activated: SampleReplicationContentFilterFactory");
		return filter;
		
		
	}
}

