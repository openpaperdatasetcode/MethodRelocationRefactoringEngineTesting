// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import java.util.ArrayList;import java.util.List;
class SourceClass {// First static nested classpublic static class FirstStaticNested {}
// Second static nested classpublic static class SecondStaticNested {}
// Member inner class with abstract methodpublic class SourceInner {public abstract Object abstractMethod(TargetClass target);}
// Static code blockstatic {// Instance method in inner class with method chainingTargetClass target = new TargetClass();TargetClass.Inner inner = target.new Inner();int result = inner.getValue().increment().get();System.out.println("Static block result: " + result);}
public SourceInner createInner() {return new SourceInner() {@Overridepublic Object abstractMethod(TargetClass target) {// Constructor invocationTargetClass.Inner targetInner = target.new Inner();
// Expression statementtargetInner.setValue(10);
// Abstract InfixExpressionabstract class InfixEvaluator {int evaluate() {return targetInner.getValue() + 5;}}int infixResult = new InfixEvaluator() {}.evaluate();
// Variable callList<String> data = new ArrayList<>();data.add(targetInner.getInfo());
// Private LabeledStatement with this.field (diff package)class LabeledProcessor {private void process() {processing: {if (targetInner.getValue() < 0) {break processing;}data.add("Value: " + targetInner.this.field);}}}new LabeledProcessor().process();
return data;}};}}
// Target package: com.targetpackage com.target;
strictfp class TargetClass {int field = 20;
// Member inner classpublic class Inner {private int value;private String info = "inner_info";
public Inner setValue(int val) {this.value = val;return this;}
public int getValue() {return value;}
public Inner increment() {this.value++;return this;}
public int get() {return value;}
public String getInfo() {return info;}}}