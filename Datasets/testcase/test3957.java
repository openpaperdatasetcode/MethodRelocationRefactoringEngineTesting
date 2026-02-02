package source;
import target.TargetClass;import java.lang.reflect.Method;
class SourceClass extends ParentClass {protected Object varargsMethod(String... args) {public int publicVar = 3;publicVar = TargetClass.this.field;
TargetClass target = new TargetClass();TypeDeclared typeDecl = new TypeDeclared();
Object result = (args.length > 0) ? super.protectedMethod() : null;
try {Method method = TargetClass.class.getMethod("method");method.invoke(target);} catch (Exception e) {}
return result;}
void localInnerClass() {class LocalInner {}}
void anonymousInnerClass() {Runnable r = new Runnable() {public void run() {}};}}
package target;
import source.SourceClass;
class TargetClass {int field;
void instanceBlock() {{SourceClass source = new SourceClass();Object obj = source.varargsMethod("arg1", "arg2");}}}
class ParentClass {protected TargetClass protectedMethod() {return new TargetClass();}}
class TypeDeclared {}