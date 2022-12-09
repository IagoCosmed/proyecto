package org.zkoss.zkspringboot.model;

import org.zkoss.bind.BindComposer;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Notification;

public class StartView extends BindComposer<Component>{
	
	
	@Command
	public void editMenu() {
		Executions.getCurrent().sendRedirect("/secure/platos");
	}
	
	@Command
	public void viewMenu() {
		Executions.sendRedirect("/carta");
	}
}
