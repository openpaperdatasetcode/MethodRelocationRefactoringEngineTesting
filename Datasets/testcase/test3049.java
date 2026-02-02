package test;
import java.lang.reflect.Method;import java.sql.SQLException;import java.util.List;
non-sealed record TargetClass(String targetField) {TargetClass {Runnable r = new Runnable() {public void run() {}};}}
record SourceClass(int sourceField) implements Runnable {public SourceClass {Object o = new Object() {};class LocalInner {}}
public void run() {}
public record SourceInnerRec() {public void methodToMove() throws SQLException {class TypeDecl {}TargetClass target = new TargetClass("");Object var = target.targetField();List rawList = null;rawList.add(var);try {Method method = SourceInnerRec.class.getMethod("methodToMove");method.invoke(this);} catch (Exception e) {throw new SQLException(e);}}}
strictfp class InnerClass {@Overridepublic Object call() {return this.methodToMove();}
private Object methodToMove() {return new SourceInnerRec().methodToMove();}}
void example() {Runnable r = () -> new InnerClass().call();}}