package sk.digitalorchestra.graylog.plugins.hexdec;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;

import org.graylog.plugins.pipelineprocessor.ast.functions.Function;

import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

/**
 * Extend the PluginModule abstract class here to add you plugin to the system.
 */
public class HexDecFunctionsModule extends PluginModule {
	/**
	 * Returns all configuration beans required by this plugin.
	 *
	 * Implementing this method is optional. The default method returns an empty
	 * {@link Set}.
	 */
	@Override
	public Set<? extends PluginConfigBean> getConfigBeans() {
		return Collections.emptySet();
	}

	/*
	 * Register your plugin types here.
	 *
	 * Examples:
	 *
	 * addMessageInput(Class<? extends MessageInput>); addMessageFilter(Class<?
	 * extends MessageFilter>); addMessageOutput(Class<? extends
	 * MessageOutput>); addPeriodical(Class<? extends Periodical>);
	 * addAlarmCallback(Class<? extends AlarmCallback>); addInitializer(Class<?
	 * extends Service>); addRestResource(Class<? extends PluginRestResource>);
	 *
	 *
	 * Add all configuration beans returned by getConfigBeans():
	 *
	 * addConfigBeans();
	 */
	@Override
	protected void configure() {
		addMessageProcessorFunction(Hex2DecFunction.NAME, Hex2DecFunction.class);
		addMessageProcessorFunction(Dec2HexFunction.NAME, Dec2HexFunction.class);
	}

	protected void addMessageProcessorFunction(String name, Class<? extends Function<?>> functionClass) {
		addMessageProcessorFunction(binder(), name, functionClass);
	}

	public static MapBinder<String, Function<?>> processorFunctionBinder(Binder binder) {
		return MapBinder.newMapBinder(binder, TypeLiteral.get(String.class), new TypeLiteral<Function<?>>() {
		});
	}

	public static void addMessageProcessorFunction(Binder binder, String name,
			Class<? extends Function<?>> functionClass) {
		processorFunctionBinder(binder).addBinding(name).to(functionClass);
	}
}
