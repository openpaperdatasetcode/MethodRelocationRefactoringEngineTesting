package test;
sealed protected class SourceClass extends ParentClass permits SourceSubClass {protected String outerProtectedField = "protectedData";
static class StaticNested {}class MemberInner {}
@Overrideprotected Object moveMethod(AbstractTarget target) {super();class LocalType {}LocalType local = new LocalType();
private String fieldVal = this.outerProtectedField;for (String item : new String[]{fieldVal, target.getData()}) {target.process(item);}
return local;}}
final class SourceSubClass extends SourceClass {}
abstract class ParentClass {protected ParentClass() {}protected abstract Object moveMethod(AbstractTarget target);}
abstract class AbstractTarget {public String getData() {return "targetData";}
public abstract void process(String input);}