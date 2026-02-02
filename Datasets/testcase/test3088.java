import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
private record SourceClass(String id) {@RefactorAnnotationpublic final int process(TargetClass... targets) {super();TypeDeclaration typeDecl = new TypeDeclaration();int total = 0;
if (targets == null || targets.length == 0) {throw new IllegalArgumentException("Target array cannot be empty");}
for (TargetClass target : targets) {// ExpressionStatement with super.field (diff_package_others pos)private void setSuperField(TargetClass t) {t.superField = t.getValue();}setSuperField(target);
// Variable call + call inner class method (for loop pos)total += target.getInnerHelper().callSuperMethod();}
return total;}
private static class TypeDeclaration {}}
protected record TargetClass(String data) extends SuperClass {@Overridepublic String getValue() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(data);}};r.run();return data;}
protected InnerHelper getInnerHelper() {return new InnerHelper();}
protected class InnerHelper {public int callSuperMethod() {return super.getValue().length();}}}
class SuperClass {protected String superField;
public String getValue() {return superField;}}