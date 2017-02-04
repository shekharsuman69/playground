package com.xxx.register;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.xxx.services.GeoCodeService;

/**
 * InterfaceRegister is the registry of all service end points. Any new service
 * class that gets added to the project and needs to be exposed as REST end
 * point must be registered here. It extends jax-rs Application base class and
 * overrides getClasses() method. The getClasses() method holds the set of
 * service classes that are exposed as end points.
 *
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 */


public class InterfaceRegister extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(GeoCodeService.class);
		return classes;
	}

}
