package org.zkoss.zkspringboot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zkspringboot.service.serviceImpl.PlatoServiceImpl;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SelectingDishesView extends SelectorComposer<Component>{
	
	@WireVariable
    private PlatoServiceImpl platoService;
	
	 @Wire
	   private Listbox left = new Listbox();
	 @Wire
	   private Listbox right = new Listbox();
	 
	   private List<Plato> platoList;
	   private ListModelList<Plato> leftListModel ; 
	   private ListModelList<Plato> rightListModel ; 
	   
	   
	   @Init
       public void init(@ContextParam(ContextType.VIEW) Component view,
              @ExecutionArgParam("value1") List<Plato> v1) {
          Selectors.wireComponents(view, this, false);
           Selectors.wireEventListeners(left, this);
           rightListModel = new ListModelList<Plato>();
           if(v1!=null) {
               rightListModel = (ListModelList<Plato>) v1;
               right.setMultiple(true);
           }
          if(createListModel() !=null) {
               leftListModel = createListModel();
               left.setMultiple(true);
          }
       }
	   
	   @Command
	   public void addProjects() {
	       Events.postEvent(new ChooseEvent(left, chooseOne()));
	   }
	   
	   /**
	    * Listener that removes the projects.
	    */
	   @Command
	   public void removeProjects() {
	       Events.postEvent(new ChooseEvent(right, unchooseOne()));
	   }
	   
	   private Set<Plato> chooseOne() {
	       Set<Plato> set = leftListModel.getSelection();
	       
	       if (set.isEmpty()) {
	           Clients.showNotification("Seleccione un plato a añadir", "info", null, null, 3000, true);
	       }else {
	           
	       rightListModel.addAll(set);
	       leftListModel.removeAll(set);
	           }
	       return set;
	       }
	   
	   private Set<Plato> unchooseOne() {
	       Set<Plato> set = rightListModel.getSelection();
	       if (set.isEmpty()) {
	           Clients.showNotification("Seleccione un plato a retirar", "info", null, null, 3000, true);
	       }else {
	       leftListModel.addAll(set);
	       rightListModel.removeAll(set);
	           }
	       return set;
	       }
	   
	   
	   private ListModelList<Plato> createListModel() {
	       ListModelList<Plato> listModelList = new ListModelList<Plato>();
	       
	       if(!rightListModel.isEmpty()) {
	           List<String> nameList = new ArrayList<>();
	           for (Plato requisito : rightListModel) {
	               nameList.add(requisito.getNombre());
	           }
	           listModelList.addAll(platoService.getPlatoWhereTitleIsNotLikeAnyWord(nameList));
	           }
//	  TOFIX     else {
//	               listModelList.addAll(platoService.listOfPlato());
//	           }
	           listModelList.setMultiple(true);
	       
	       return listModelList;
	       }
	   
	   
	@Command
    public void back() {
    	final HashMap<String, Object> map = new HashMap<String, Object>();
        Executions.getCurrent().sendRedirect("/secure/platos");
    }
	
	@Command
    public void save() {
    	final HashMap<String, Object> map = new HashMap<String, Object>();
       // map.put("value1", this.platoFileList );
        Executions.getCurrent().sendRedirect("/secure/platos");
    }
	
	public class ChooseEvent extends Event {
	       
	       /** The Constant serialVersionUID. */
	       private static final long serialVersionUID = -7334906383953342976L;
	       
	       /**
	        * Instantiates a new choose event.
	        *
	        * @param target the target
	        * @param data the data
	        */
	       public ChooseEvent(Component target, Set<Plato> data) {
	       super("onChoose", target, data);
	           }
	       }
}
