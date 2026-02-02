package test;
class TargetClass {public static class StaticNested {private String prop;
public void setProp(String prop) {this.prop = prop;}
public String getProp() {return prop;}}
private StaticNested nested = new StaticNested();
public StaticNested getNested() {return nested;}}
class OthersClass {private static void configure(TargetClass.StaticNested nested) {nested.setProp("configured").getProp().toString();}}
private class SourceClass {private TargetClass target;
public SourceClass() {this.target = new TargetClass();OthersClass.configure(target.getNested());}
final TargetClass process() {class NestedProcessor {private void overrideMethod() {target.getNested().setProp("overridden");}}
// Property assignment with overriding methodNestedProcessor processor = new NestedProcessor();processor.overrideMethod();
// Variable call and access instance methodString value = target.getNested().getProp();if (value == null) {throw new IllegalArgumentException("Value cannot be null");}
return this.target;}}