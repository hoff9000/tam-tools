package com.adobe.tam.tools.core.replication;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "TAM Tools - Sample Replication Filter Config", description = "Service Configuration")
public @interface SampleReplicationServiceConfig {
/*
  @AttributeDefinition(name = "Property Name", description = "A string value that is an exact match to a property name to be filtered out of replication", type=AttributeType.STRING)
  String configValue() default "privateData";
*/
 
  
  @AttributeDefinition(name = "Property Name", description = "A string value that is an exact match to a property name to be filtered out of replication", type=AttributeType.STRING)
  String[] getStringValues();
/*
  @AttributeDefinition(name = "NumberValue", description = "Number values", type=AttributeType.INTEGER)
  int getNumberValue();
  */
}
