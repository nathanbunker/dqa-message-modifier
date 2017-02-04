package org.immregistries.dqa.message_modifier.transform.function;

import java.util.HashMap;
import java.util.Map;

import org.immregistries.dqa.message_modifier.transform.function.general.CallFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.CleanFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.ClearFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertAfterFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertBeforeFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertFirstFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertLastFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.MapFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.TruncFunction;

public class FunctionFactory {

	public static final String CONTEXT_GENERAL = "general";

	private static final Map<String, Map<String, Class<? extends CallFunction>>> contextMap = new HashMap<>();
	private static final Map<String, Class<? extends CallFunction>> generalFunctionMap = new HashMap<>();

	static {
		contextMap.put(CONTEXT_GENERAL, generalFunctionMap);
		generalFunctionMap.put("map", MapFunction.class);
		generalFunctionMap.put("trunc", TruncFunction.class);
		generalFunctionMap.put("clear", ClearFunction.class);
		generalFunctionMap.put("clean", CleanFunction.class);
		generalFunctionMap.put("insertbefore", InsertBeforeFunction.class);
		generalFunctionMap.put("insertafter", InsertAfterFunction.class);
		generalFunctionMap.put("insterfirst", InsertFirstFunction.class);
		generalFunctionMap.put("insterlast", InsertLastFunction.class);
	}

	public CallFunction createFunction(String name)throws InstantiationException, IllegalAccessException  {
		return createFunction(CONTEXT_GENERAL, name);
	}

	public CallFunction createFunction(String context, String name) throws InstantiationException, IllegalAccessException  {
		Map<String, Class<? extends CallFunction>> functionMap = contextMap.get(context);
		if (functionMap == null) {
			throw new IllegalArgumentException("Unrecognized context '" + context + "'");
		}
		Class<? extends CallFunction> functionClass = functionMap.get(name);
		if (functionClass == null) {
			throw new IllegalArgumentException("Unrecognized function '" + name + "'");
		}
		return functionClass.newInstance();
	}
}