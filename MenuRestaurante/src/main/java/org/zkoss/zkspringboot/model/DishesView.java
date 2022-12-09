package org.zkoss.zkspringboot.model;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zul.Window;

public class DishesView extends SelectorComposer<Component>{

		private List<Plato> platoFileList;
	
		
		
		
	public List<Plato> getPlatoFileList() {
			return platoFileList;
		}

		public void setPlatoFileList(List<Plato> platoFileList) {
			this.platoFileList = platoFileList;
		}


		@AfterCompose
	    public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

	        Selectors.wireComponents(view, this, false);
		}

	@Command
    public void addPlatos() {
		
		
    	final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("value1", this.platoFileList );
        Executions.getCurrent().sendRedirect("/secure/platos_modal");
       
    }
	
	@GlobalCommand
    @NotifyChange("requisitoFileList")
    public void refreshvalues(@BindingParam("returnvalue1") List<Plato> values)
    {
        this.platoFileList = values;
    }
	
	@Command
    public void cancel() {
        Executions.getCurrent().sendRedirect("/secure/main");
    }
}
