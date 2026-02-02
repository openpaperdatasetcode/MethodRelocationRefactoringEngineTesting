package samepkg;
import java.io.IOException;
@interface SourceAnnotation {class InnerClass {public String callMethod(String param) {return param.toUpperCase();}}
@Deprecatedpublic Object instanceMethod(PrivateTargetAnnotation targetParam) throws IOException {class LocalTypeDeclaration {}LocalTypeDeclaration localType = new LocalTypeDeclaration();
protected int var1 = targetParam.targetField;protected int var2 = targetParam.targetField;
System.out.println(var1 + var2);PrivateTargetAnnotation varCall = targetParam;
InnerClass inner = new InnerClass();String result = inner.callMethod("annotation-attr");
return varCall;}
// Overload existspublic Object instanceMethod() {return null;}
{Runnable r = new Runnable() {@Overridepublic void run() {}};}}
private @interface PrivateTargetAnnotation {int targetField = 0;
void methodWithLocal() {class TargetLocalInner {}new TargetLocalInner();}}