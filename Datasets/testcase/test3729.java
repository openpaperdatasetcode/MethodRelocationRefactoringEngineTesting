import java.util.Objects;
protected abstract class SourceClass extends ParentSource {@RefactorAnno@AnotherAnnopublic final abstract <T> TargetClass<T> abstractMethod(TargetClass<T> target);
public static class StaticNested {public <T> void staticHelper(TargetClass<T> target, SourceClass source) {new Runnable() {@Overridepublic void run() {if (target == null) {throw new NullPointerException("Target cannot be null");}String data = target.staticNested.getData() == null? "default_data": target.staticNested.getData();target.staticNested.setData(data);
source.variableCall(target);super.parentStaticMethod();}}.run();}}
protected <T> void variableCall(TargetClass<T> target) {try {target.staticNested.updateData(SourceClass.this.getClass().getSimpleName());} catch (Exception e) {}}}
class ParentSource {protected static void parentStaticMethod() {}}
class TargetClass<T> {public StaticNested staticNested = new StaticNested();
public static class StaticNested {private String data;
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public void updateData(String suffix) {this.data += "_" + suffix;}}}
class SourceConcrete extends SourceClass {@Overridepublic <T> TargetClass<T> abstractMethod(TargetClass<T> target) {StaticNested.staticHelper(target, this);return target;}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorAnno {}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface AnotherAnno {}