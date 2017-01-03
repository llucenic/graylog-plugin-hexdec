package sk.digitalorchestra.graylog.plugins.hexdec;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;

/**
 * This is the plugin. Your class should implement one of the existing plugin
 * interfaces. (i.e. AlarmCallback, MessageInput, MessageOutput)
 */
public class Dec2HexFunction extends AbstractFunction<String> {

    public static final String NAME = "dec2hex";
    private static final String PARAM = "long";

    @Override
    public String evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
    	Long number = valueParam.required(functionArgs, evaluationContext);

        if (number == null) return null;
                
        return Long.toHexString(number);
    }
    
    private final ParameterDescriptor<Long, Long> valueParam = ParameterDescriptor
    		.integer(PARAM)
    		.description("A number, negative or positive.")
    		.build();

   	@Override
   	public FunctionDescriptor<String> descriptor() {
   	    return FunctionDescriptor.<String>builder()
   	            .name(NAME)
   	            .description("Returns hexadecimal lower case string representation of the given number. No prefix or leading zeros.")
   	            .params(valueParam)
   	            .returnType(String.class)
   	            .build();
   	}
}
