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
public class Hex2DecFunction extends AbstractFunction<Long> {

    public static final String NAME = "hex2dec";
    private static final String PARAM = "hexstring";

    @Override
    public Long evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
    	String hexString = valueParam.required(functionArgs, evaluationContext);

        if (hexString == null) return 0L;
        
        hexString = hexString.trim().replaceAll("[_ ]", "");
        if (hexString.length() == 0) return 0L;
        
        char sign = hexString.charAt(0);
        hexString = hexString.replaceAll("^([+-])?(0[xX])?0*", "");
        if (/*sign == '+' ||*/ sign == '-') hexString = sign + hexString;
        
        try {
        	return Long.parseLong(hexString, 16);
        }
        catch (NumberFormatException nfe) {}
        
        return 0L;
    }
    
    private final ParameterDescriptor<String, String> valueParam = ParameterDescriptor
    		.string(PARAM)
    		.description("String containing hexadecimal digits. It may contain also spaces and underscores or optional 0x or 0X prefix, which are all ignored when converting.")
    		.build();

   	@Override
   	public FunctionDescriptor<Long> descriptor() {
   	    return FunctionDescriptor.<Long>builder()
   	            .name(NAME)
   	            .description("Returns decimal representation of a string containing hexadecimal digits. It effectively converts hexadecimal string notation to numeric representation. Example: hex2dec(\"000085\") == 133")
   	            .params(valueParam)
   	            .returnType(Long.class)
   	            .build();
   	}
}
