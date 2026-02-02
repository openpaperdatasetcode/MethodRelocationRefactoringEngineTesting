package samepkg;
import java.lang.reflect.Method;
@interface SourceAnnotation {class InnerClass1 {}class InnerClass2 {}
protected int varargsMethod(String... params) throws Exception {new TargetAnnotation.InnerInterface() {};int var = TargetAnnotation.targetField;do {if (params.length > 0) {break;}} while (false);
switch (var) {case 1:break;default:break;}
Method method = SourceAnnotation.class.getMethod("varargsMethod", String[].class);return (int) method.invoke(null, (Object) params);}}
public @interface TargetAnnotation implements InnerInterface {int targetField = 0;
void methodWithLocal() {class LocalInner {}new LocalInner();}}
interface InnerInterface {}