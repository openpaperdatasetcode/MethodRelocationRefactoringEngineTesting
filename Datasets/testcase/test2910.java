import java.util.Objects;
interface ParentInterface<T> {int methodToMove(TargetClass<T> target) throws NullPointerException;int methodToMove(TargetClass<T> target, int param) throws NullPointerException;}
private class SourceClass<T> implements ParentInterface<T> {private String outerPrivateField = "privateValue";private static final int STATIC_FIELD = 10;
@Overridepublic int methodToMove(TargetClass<T> target) throws NullPointerException {Objects.requireNonNull(target);
int result = 0;switch (target.getStaticField()) {case 5:SourceConstructor c1 = new SourceConstructor(STATIC_FIELD);SourceConstructor c2 = new SourceConstructor(outerPrivateField);result = c1.getValue() + c2.getValue();break;default:break;}
loop:for (int i = 0; i < 3; i++) {if (i == 1) {continue loop;}this.useTargetInner(target);}
return result;}
@Overridepublic int methodToMove(TargetClass<T> target, int param) throws NullPointerException {return methodToMove(target) + param;}
private void useTargetInner(TargetClass<T> target) {TargetClass.StaticNested<T> nested = new TargetClass.StaticNested<>();nested.process(target.getField());}
public static class SourceConstructor {private int value;private String strValue;
public SourceConstructor(int value) {this.value = value;}
public SourceConstructor(String strValue) {this.strValue = strValue;this.value = strValue.length();}
public int getValue() {return value;}}}
class TargetClass<T> implements Runnable {private T field;private static final int STATIC_FIELD = 5;
public static class StaticNested {
public void process(U data) {
System.out.println(data);
}
}
public T getField() {return field;}
public int getStaticField() {return STATIC_FIELD;}
@Overridepublic void run() {}}
class ParentCaller {public static <T> void callMethod(TargetClass<T> target) {SourceClass<T> source = new SourceClass<>();try {SourceClass.methodToMove(target);} catch (NullPointerException e) {throw new RuntimeException("Call failed", e);}}}