package org.zkoss.zkspringboot.model;

import java.util.HashMap;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zkspringboot.service.serviceImpl.PlatoServiceImpl;
import org.zkoss.zul.Window;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DishesView extends SelectorComposer<Component>{

	
		@WireVariable
		private PlatoServiceImpl platoServiceImpl;
		@Wire
		private Window window;
		
		public static final String           ARGUMENTS_OBJECT         = "allmyvalues";
		public static final String           SELECTED_RECORD          = "selectedRecord";
		public static final String           FORMATION_VIEW_TYPE_MODE = "recordMode";
		
		private List<Plato> platoFileList;
		private PlatoViewType    recordMode;
		private Plato         	  selectedRecord;
		private boolean           makeAsReadOnly;
		private boolean    	   	visible;
	    private boolean			 notNew  ;
		private AllDishes 		allDishes;
		
		@SuppressWarnings("unchecked")
		@Init
		public void init(@ContextParam(ContextType.VIEW) Component view) {
			Selectors.wireComponents(view, this, false);       
			final HashMap<String, Object> map = (HashMap<String, Object>) Sessions.getCurrent().getAttribute(ARGUMENTS_OBJECT);
			this.selectedRecord = (Plato) map.get(SELECTED_RECORD);
			this.recordMode = PlatoViewType.getForValue((Integer) map.get(FORMATION_VIEW_TYPE_MODE));
	    }

	    
	    
//		@SuppressWarnings("unchecked")
//	    @AfterCompose
//	    public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
//
//	        Plato plato;
//	        Selectors.wireComponents(view, this, false);
//
//	        final HashMap<String, Object> map = (HashMap<String, Object>) Sessions.getCurrent().getAttribute(
//	                AllDishes.ARGUMENTS_OBJECT);
//	        this.recordMode = PlatoViewType.getForValue((Integer) map.get(AllDishes.FORMATION_VIEW_TYPE_MODE));
//	        plato = (Plato) map.get("selectedRecord");
//
//	        switch (recordMode) {
//	            case EDIT:
//	            	setVisible(true);
//	            	setNotNew(true);
//	                this.selectedRecord = plato;
//	                break;
//	            case NEW:
//	            	setNotNew(false);
//	                this.selectedRecord = new Plato();
//	                break;
//	            case READ:
//	            	setVisible(false);
//	            	setNotNew(true);
//	                setMakeAsReadOnly(true);
//	                this.selectedRecord = plato;
//	                break;
//	        }
//	    }
		
		
	public PlatoViewType getRecordMode() {
			return recordMode;
		}


		public void setRecordMode(PlatoViewType recordMode) {
			this.recordMode = recordMode;
		}


		public Plato getSelectedRecord() {
			return selectedRecord;
		}


		public void setSelectedRecord(Plato selectedRecord) {
			this.selectedRecord = selectedRecord;
		}


		public boolean isMakeAsReadOnly() {
			return makeAsReadOnly;
		}


		public void setMakeAsReadOnly(boolean makeAsReadOnly) {
			this.makeAsReadOnly = makeAsReadOnly;
		}


		public boolean isVisible() {
			return visible;
		}


		public void setVisible(boolean visible) {
			this.visible = visible;
		}


		public boolean isNotNew() {
			return notNew;
		}


		public void setNotNew(boolean notNew) {
			this.notNew = notNew;
		}


	public List<Plato> getPlatoFileList() {
			return platoFileList;
		}

		public void setPlatoFileList(List<Plato> platoFileList) {
			this.platoFileList = platoFileList;
		}

		
	

	public AllDishes getAllDishes() {
			return allDishes;
		}


		public void setAllDishes(AllDishes allDishes) {
			this.allDishes = allDishes;
		}


	@Command
    public void addPlatos() {
		
		platoFileList = platoServiceImpl.findAll();
//    	final HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("value1", this.platoFileList );
//        Executions.getCurrent().sendRedirect("/secure/platos_modal");
       
    }
	
	@GlobalCommand
    @NotifyChange("requisitoFileList")
    public void refreshvalues(@BindingParam("returnvalue1") List<Plato> values)
    {
        this.platoFileList = values;
    }
	
	@Command
    public void cancel() {
        Executions.getCurrent().sendRedirect("/secure/AllDishes");
    }
}
