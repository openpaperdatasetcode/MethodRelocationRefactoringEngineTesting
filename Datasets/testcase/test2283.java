package source;
import target.ParentClass;import target.TargetClass;import java.util.List;
strictfp class SourceClass {static class StaticNested {}
{new Runnable() {};}
class SourceInner {protected TargetClass instanceMethod(TargetClass target) {class TypeDecl {}super();
int i = 0;do {variableCall = target.field;target.method3();i++;} while (i < 3);
String result = new ParentClass().abstractMethod();
return target;}
protected TargetClass instanceMethod(List<? extends TargetClass> list) {return null;}
String variableCall;}}
package target;
abstract class ParentClass {abstract String abstractMethod();}
private class TargetClass extends ParentClass {String field;static class TargetStaticNested {}
void method3() {}
@OverrideString abstractMethod() {return "";}}