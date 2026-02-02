package test;
import java.util.ArrayList;import java.util.Collection;import java.lang.reflect.Method;
public class SourceClass {class MemberInner {}
@MyAnnotationprotected TargetClass<String> varargsMethod(TargetClass<String> targetParam, Object... params) {class LocalInner {}
int i = 0;while (i < params.length) {targetParam.process(params[i]);i++;}
Collection<Object> coll = new ArrayList<>();coll.add(superTypeMethod(targetParam, "test"));
targetParam.doAction();
try {Method method = TargetClass.class.getMethod("process", Object.class);} catch (NoSuchMethodException e) {e.printStackTrace();}
return SourceClass.this.createTargetInstance();}
private TargetClass<String> createTargetInstance() {return new TargetClass<>();}
private Object superTypeMethod(Collection<String> superTypeRef, String arg) {return superTypeRef.size() + arg;}}
strictfp class TargetClass<T> {void process(Object obj) {}
void doAction() {}}
@interface MyAnnotation {}
