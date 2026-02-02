package same;
public enum SourceEnum {ITEM {@Overridepublic void process(TargetEnum target) {// Member inner classclass Processor {void handle(TargetEnum t) {// Local inner class in sourceclass Validator {boolean check() {return t.value != null;}}if (!new Validator().check()) {throw new NullPointerException("Target value is null");}}}
// Expression statementnew Processor().handle(target);
// EmptyStatement with target object fieldtarget.value = "processed"; ;
// Variable callSystem.out.println(target.value);}};
public abstract void process(TargetEnum target);}
package same;
protected enum TargetEnum {TARGET_ITEM {@Overridevoid compute() {// Local inner class in targetclass Calculator {int sum(int a, int b) {return a + b;}}int result = new Calculator().sum(2, 3);value = String.valueOf(result);}};
String value;
abstract void compute();}