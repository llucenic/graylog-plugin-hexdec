package sk.digitalorchestra.graylog.plugins.hexdec;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;

import java.util.Optional;

/**
 * This is the plugin. Your class should implement one of the existing plugin
 * interfaces. (i.e. AlarmCallback, MessageInput, MessageOutput)
 */
public class Dec2HexFunction extends AbstractFunction<String> {

    public static final String NAME = "dec2hex";
    private static final String PARAM = "longval";
    private static final String PARAM_LEN = "len";

    private final ParameterDescriptor<Long, Long> valueParam = ParameterDescriptor
    		.integer(PARAM)
    		.description("A number, negative or positive.")
    		.build();

    private final ParameterDescriptor<Long, Long> lenParam = ParameterDescriptor
    		.integer(PARAM_LEN)
    		.description("Result string length. Result will be padded with leading zeroes to have at least len length. The sign of the parameter value is ignored. Defaults to 1.")
    		.build();

    @Override
    public String evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
    	Long number = valueParam.required(functionArgs, evaluationContext);
    	Optional<Long> numLength = lenParam.optional(functionArgs, evaluationContext);

        if (number == null) return null;
                
        return String.format("%0" + String.valueOf(Math.abs(numLength.orElse(1L))) + "x", number);
    }
    
   	@Override
   	public FunctionDescriptor<String> descriptor() {
   	    return FunctionDescriptor.<String>builder()
   	            .name(NAME)
   	            .description("Returns hexadecimal lower case string representation of the given number. No prefix, optionally left padded with zeros.")
   	            .params(valueParam, lenParam)
   	            .returnType(String.class)
   	            .build();
   	}
}
