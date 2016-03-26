package org.strotmann.db;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.db4o.ObjectServer;
import com.db4o.ext.ExtObjectContainer;

public class Db4oFactory {
	public ExtObjectContainer getDb(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		ObjectServer server = (ObjectServer)context.getAttribute("db4oServer");
		return (ExtObjectContainer)server.openClient();
	}
}
