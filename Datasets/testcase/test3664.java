package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;import java.util.function.Consumer;
public class SourceClass {public class InnerSource {public void processTargetField(TargetClass target) {target.field = "processed";}}
public static class StaticNested {public static TargetClass getTargetInstance() {return new TargetClass();}}
public Object publicNormalMethod(TargetClass target, String... varargs) throws IOException {if (target == null) {throw new IOException("TargetClass cannot be null");}
new InnerSource().processTargetField(target);
List<String> varargsList = Arrays.asList(varargs);Consumer<String> action = target::processVararg;varargsList.forEach(action);
try {Method method = TargetClass.class.getMethod("processVararg", String.class);method.invoke(target, varargsList.get(0));} catch (Exception e) {throw new IOException("Reflection invocation failed", e);}
SubTargetClass subTarget = new SubTargetClass();TargetClass result = subTarget.callStaticSuperMethod(target);
return result;}
@AnnotationWithTargetValue(target = TargetClass.class)public static class AnnotatedHelper {}
@interface AnnotationWithTargetValue {Class<TargetClass> target();}}
public class TargetClass {public String field;
public void processVararg(String arg) {this.field += "_" + arg;}
public TargetClass getInstance() {return new TargetClass() {@Overridepublic void processVararg(String arg) {super.processVararg(arg.toUpperCase());}};}}
public class SubTargetClass extends TargetClass {public TargetClass callStaticSuperMethod(TargetClass target) {TargetClass staticInstance = TargetClass.staticFactoryMethod();staticInstance.processVararg(super.field);return staticInstance;}
public static TargetClass staticFactoryMethod() {return new TargetClass();}}