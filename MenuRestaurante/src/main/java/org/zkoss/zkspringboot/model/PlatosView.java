package org.zkoss.zkspringboot.model;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkspringboot.service.serviceImpl.PlatoServiceImpl;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PlatosView extends SelectorComposer<Component>{
	
	@WireVariable
    private PlatoServiceImpl platoService;
	
	

}
