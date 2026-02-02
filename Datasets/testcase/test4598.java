package test;
import java.lang.reflect.Type;import com.google.inject.TypeLiteral;
class Outer {private class Source {class Inner {class InnerRec {Target.Inner targetParam;
Target.Inner getTarget() {variableCall(targetParam);targetParam.instanceMethod();return targetParam;}
private void instanceMethodChain() {Source source = new Source();source.new Inner().new InnerRec().getTarget();}
{instanceMethodChain();}
public TypeLiteral<Target> getTypeLiteral() {return new TypeLiteral<Target>() {};}
private void variableCall(Target.Inner param) {Target.Inner inner = param;}}}}}
class Target extends SuperClass {class Inner {void instanceMethod() {}}}
class SuperClass {}