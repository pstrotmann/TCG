package org.strotmann.db;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.db4o.Db4o;
import com.db4o.ObjectServer;

public class Db4oServletContextListener
implements ServletContextListener {
	public static final String KEY_DB4O_FILE_NAME = "db4oFileName";
	public static final String KEY_DB4O_SERVER = "db4oServer";
	private ObjectServer server=null;
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context=event.getServletContext();
		String dbPath="/vol/"+context.getInitParameter(KEY_DB4O_FILE_NAME);
		server=Db4o.openServer(dbPath,0);		
		context.setAttribute(KEY_DB4O_SERVER,server);
		context.log("db4o startup on "+dbPath);
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();
		context.removeAttribute(KEY_DB4O_SERVER);
		close();
		
		context.log("db4o shutdown");
	}
	
	private void close() {
		if(server!=null) {
		  server.close();
		}
		server=null;
	}
}

