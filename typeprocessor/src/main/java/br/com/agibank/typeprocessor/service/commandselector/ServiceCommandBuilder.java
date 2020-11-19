package br.com.agibank.typeprocessor.service.commandselector;

import br.com.agibank.typeprocessor.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ServiceCommandBuilder {

	private Map<String, ServiceCommandAbstract> commands;

	@Autowired
	public ServiceCommandBuilder(Set<ServiceCommandAbstract> commandSet) {
		createCommand(commandSet);
	}

	private void createCommand(Set<ServiceCommandAbstract> commandSet) {
		commands = new HashMap<String, ServiceCommandAbstract>();
		commandSet.forEach(c ->commands.put(c.getName(), c));
	}

	public void execute(String className, Entity elem, String key){
		commands.get(className).handle(elem, key);
	}
}