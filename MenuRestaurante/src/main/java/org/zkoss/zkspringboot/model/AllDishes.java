package org.zkoss.zkspringboot.model;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkspringboot.entity.Plato;
import org.zkoss.zkspringboot.service.serviceImpl.PlatoServiceImpl;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AllDishes extends SelectorComposer<Component>{
	
	@WireVariable
	private PlatoServiceImpl platoServiceImpl;
	
	public static final String           FORMATION_VIEW_TYPE_MODE = "recordMode";
    public static final String           ARGUMENTS_OBJECT         = "allmyvalues";
    public static final String           SELECTED_RECORD          = "selectedRecord";
			
	private List<Plato> platoList;
	private             Plato        selectedRecord;
	private DishesView dishesView;
	private HashMap<String, Object> map = new HashMap<>();
	
	@Init
    public void init() {
        	            
        platoList = platoServiceImpl.findAll();
    }
	
	

	public DishesView getDishesView() {
		return dishesView;
	}

	public void setDishesView(DishesView dishesView) {
		this.dishesView = dishesView;
	}



	public List<Plato> getPlatoList() {
		return platoList;
	}

	public void setPlatoList(List<Plato> platoList) {
		this.platoList = platoList;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @Command
    public void onDeletePlato(@BindingParam("userRecord") Plato plato) {
        int OkCancel;
        this.selectedRecord = plato;

        String str = String.format("Va a borrar el plato", plato.getNombre());
        OkCancel = Messagebox.show(str,
                "¿Esta segura/o?",
                Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION);
        if (OkCancel == Messagebox.CANCEL)
            return;

        str = String.format("Se borrará el plato", plato.getNombre());
        Messagebox.show(str, "Borrado", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, (EventListener) event -> {
                    if ((Integer) event.getData() == Messagebox.OK) {

                    	platoServiceImpl.deleteFormation(plato);
                        platoList.remove(selectedRecord);
                        BindUtils.postNotifyChange(null, null,
                                AllDishes.this, "dataSet");

                    }
                });
    }
	
	@Command
    public void onEditPlato(@BindingParam("userRecord") Plato plato) {

       // final HashMap<String, Object> map = new HashMap<>();
        map.put(SELECTED_RECORD, plato);
        map.put(FORMATION_VIEW_TYPE_MODE, PlatoViewType.EDIT.value);
        Sessions.getCurrent().setAttribute(ARGUMENTS_OBJECT, map);
        
        Executions.getCurrent().sendRedirect("/secure/editPlato");
       
    }
	
	@Command
    public void returnAt() {
        Executions.getCurrent().sendRedirect("/secure/main");
    }
	
	
}
