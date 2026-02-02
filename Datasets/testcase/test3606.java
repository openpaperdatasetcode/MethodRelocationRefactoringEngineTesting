package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
interface SourceInterface {class InnerSource {public List<String> instanceMethod(TargetInterface.InnerTarget<String> param) {List<String> result = new ArrayList<>();
SourceNested nested = new SourceNested();result.add(nested.getValue());
switch (param.getType()) {case "CONSTRUCTOR":Supplier<Object> constructorRef = TargetInterface.InnerTarget::new;result.add(constructorRef.get().toString());break;case "METHOD":param.process(result);break;}
try {Method method = param.getClass().getMethod("getValue");result.add(method.invoke(param).toString());} catch (Exception e) {e.printStackTrace();}
return result;}}
static class SourceNested {public String getValue() {return "nested";}}}
protected interface TargetInterface {class InnerTarget<T> {private T value;
public InnerTarget() {}
public InnerTarget(T value) {this.value = value;}
public String getType() {return "CONSTRUCTOR";}
public T getValue() {return value;}
public void process(List<String> list) {list.add(value.toString());}}}
